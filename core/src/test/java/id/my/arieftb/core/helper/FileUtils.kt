package id.my.arieftb.meowvie.helper

object FileUtils {
    fun readTestResourceFile(fileName: String?): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }
}