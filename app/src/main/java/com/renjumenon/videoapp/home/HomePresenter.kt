package com.renjumenon.videoapp.home

import com.renjumenon.videoapp.api.VideoRetriever
import com.renjumenon.videoapp.models.VideoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by renju.menon on 06,October,2018
 */
class HomePresenter @Inject constructor(view: HomeContract.View) : HomeContract.Presenter {

    var homeView: HomeContract.View = view

    @Inject
    fun setupListeners() {
        homeView.setPresenter(this)
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getVideoList() {
        var retriever = VideoRetriever()

        val callBack = object : Callback<VideoList> {
            override fun onFailure(call: Call<VideoList>?, t: Throwable?) {
                homeView.showGetVideoOnFailure(call, t)
            }

            override fun onResponse(call: Call<VideoList>?, response: Response<VideoList>?) {
                response?.isSuccessful.let {
                    homeView.showGetVideoList(response)
                }
            }
        }

        retriever.getVideo(callBack)
    }


}