package com.renjumenon.videoapp.api

import com.renjumenon.videoapp.BuildConfig
import com.renjumenon.videoapp.models.VideoList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by renju.menon on 06,October,2018
 */
interface VideoApi {
    @GET("videos/?key=" + BuildConfig.API_KEY)
    fun getVideos(): Call<VideoList>
}