package com.renjumenon.videoapp.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by renju.menon on 06,October,2018
 */
object ActivityUtils {
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}