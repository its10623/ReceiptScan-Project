package com.smoothsm.cameraapp.data.network.api

import com.smoothsm.cameraapp.data.network.model.ReceiptResponse
import com.smoothsm.cameraapp.data.network.model.ScanApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ReceiptApi {
    @GET("receipts/{id}")
    suspend fun getReceipt(
        @Path("id") id: Long,
    ): Response<ReceiptResponse>

    @Multipart
    @POST("receipt/scan")
    suspend fun scanReceipt(
        @Part file: MultipartBody.Part,
    ): Response<ScanApiResponse>
}
