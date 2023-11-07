package es.javiercarrasco.formregistro

data class FormRegistro(
    var nombre: String,
    var apellidos: String,
    var correo: String,
    var phone: String,
    var vip: Boolean = false
) {
    override fun toString(): String {
        return "$nombre;$apellidos;$correo;$phone"
    }
}