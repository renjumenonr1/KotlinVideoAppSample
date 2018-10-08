package com.renjumenon.videoapp.api

import com.renjumenon.videoapp.BuildConfig
import com.renjumenon.videoapp.models.VideoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by renju.menon on 06,October,2018
 */
class VideoRetriever {
    private val mService: VideoApi
    init {
       /* val retrofit = Retrofit.Builder().
                baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()*/
        mService = NetworkHandler.getRetrofit().create(VideoApi::class.java)
    }

    fun getVideo(callBack: Callback<VideoList>) {
        val videos = mService.getVideos()
        videos.enqueue(callBack)
    }
}