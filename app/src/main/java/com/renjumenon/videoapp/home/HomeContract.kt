package com.renjumenon.videoapp.home

import com.renjumenon.videoapp.base.BaseContract
import com.renjumenon.videoapp.models.VideoList
import retrofit2.Call
import retrofit2.Response

/**
 * Created by renju.menon on 06,October,2018
 */
class HomeContract {
    interface View : BaseContract.View<Presenter> {
        fun showGetVideoOnFailure(call: Call<VideoList>?, t: Throwable?)

        fun showGetVideoList(response: Response<VideoList>?)
    }

    interface Presenter : BaseContract.Presenter {
        fun getVideoList()
    }
}