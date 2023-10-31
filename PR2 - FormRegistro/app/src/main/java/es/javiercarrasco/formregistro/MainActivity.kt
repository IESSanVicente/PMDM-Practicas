package es.javiercarrasco.formregistro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.javiercarrasco.formregistro.databinding.ActivityMainBinding
import es.javiercarrasco.formregistro.utils.writeFile

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // ViewBinding
        setContentView(binding.root)

        // Control para la activación del botón de registro.
        binding.cbPolitica.setOnClickListener {
            binding.btnAceptar.isEnabled = binding.cbPolitica.isChecked
        }

        binding.btnAceptar.setOnClickListener {
            if (binding.tietNombre.text!!.isNotEmpty())
                if (binding.tietApellidos.text!!.isNotEmpty())
                    if (binding.tietCorreo.text!!.isNotEmpty())
                        if ((binding.tietPhone.text!!.isNotEmpty()) && (binding.tietPhone.text!!.length == 9)) {
                            val registro = FormRegistro(
                                binding.tietNombre.text.toString(),
                                binding.tietApellidos.text.toString(),
                                binding.tietCorreo.text.toString(),
                                binding.tietPhone.text.toString()
                            )

                            if (writeFile(this, registro)) { // Registro correcto.
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.txt_registro_ok),
                                    Snackbar.LENGTH_LONG
                                ).show()

                                clearFields()
                            } else { // Registro incorrecto.
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.txt_registro_error),
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        } else binding.tietPhone.error = getString(R.string.txt_error_phone)
                    else binding.tietCorreo.error = getString(R.string.txt_error_correo)
                else binding.tietApellidos.error = getString(R.string.txt_error_apellidos)
            else binding.tietNombre.error = getString(R.string.txt_error_nombre)
        }
    }

    // Método para limpiar los campos del formulario y desactivar el botón de registro.
    private fun clearFields() {
        with(binding) {
            tietNombre.text!!.clear()
            tietApellidos.text!!.clear()
            tietCorreo.text!!.clear()
            tietPhone.text!!.clear()
            cbPolitica.isChecked = false
            btnAceptar.isEnabled = false
        }
    }
}