package es.javiercarrasco.formregistro.utils

import android.content.Context
import es.javiercarrasco.formregistro.FormRegistro
import java.io.OutputStreamWriter

fun writeFile(context: Context, content: FormRegistro): Boolean {
    var result = false
    try {
        val file = OutputStreamWriter(
            context.openFileOutput("formRegistro.csv", Context.MODE_APPEND)
        )
        file.write(content.toString() + (if (content.vip)";VIP" else "") + "\n")
        file.flush()
        file.close()
        result = true
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}