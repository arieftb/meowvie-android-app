package id.my.arieftb.core.helper

import com.google.gson.Gson
import id.my.arieftb.meowvie.helper.FileUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object TestHelper {
    fun <R : Any?> createDummyResponse(
        responseFileName: String?,
        classOf: Class<out R>
    ): Response<R> {
        val dummyResponse = if (responseFileName != null) {
            Gson().fromJson<R>(FileUtils.readTestResourceFile(responseFileName), classOf)
        } else {
            null
        }

        println("MeowViewTag : response -> ${Gson().toJson(dummyResponse)}")
        return Response.success(dummyResponse)
    }

    fun <R : Any?> createDummyResponse(
        responseFileName: String?, statusCode: Int = 500,
        classOf: Class<out R>
    ): Response<R> {

        val dummyResponse = if (responseFileName != null) {
            Gson().fromJson<R>(FileUtils.readTestResourceFile(responseFileName), classOf)
        } else {
            null
        }

        if (responseFileName.isNullOrEmpty()) {
            return Response.error(
                statusCode,
                "Test Server Error".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        }

        //Init Response
        return Response.error(
            statusCode, Gson().toJson(dummyResponse)
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}