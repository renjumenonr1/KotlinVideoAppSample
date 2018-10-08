package com.renjumenon.videoapp.base

/**
 * Created by renju.menon on 06,October,2018
 */
interface BaseContract {
    interface View<in T : Presenter> {
        fun setPresenter(presenter: T)
    }

    interface Presenter {
        fun subscribe()

        fun unSubscribe()
    }
}