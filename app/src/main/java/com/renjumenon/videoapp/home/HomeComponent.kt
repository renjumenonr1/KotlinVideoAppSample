package com.renjumenon.videoapp.home

import com.renjumenon.videoapp.scope.FragmentScope
import dagger.Component

/**
 * Created by renju.menon on 06,October,2018
 */
@FragmentScope
@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}