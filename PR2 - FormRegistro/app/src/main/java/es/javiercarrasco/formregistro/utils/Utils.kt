package es.javiercarrasco.formregistro.utils

import android.content.Context
import es.javiercarrasco.formregistro.FormRegistro

fun writeFile(context: Context, content: FormRegistro): Boolean {
    var result = false
    try {
        // data/data/es.javiercarrasco.formregistro/files/formRegistro.csv
        val file = context.openFileOutput("formRegistro.csv", Context.MODE_APPEND)
        file.write(content.toString().toByteArray())
        file.flush()
        file.close()
        result = true
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return result
}