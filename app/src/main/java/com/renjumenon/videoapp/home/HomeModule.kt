package com.renjumenon.videoapp.home

import com.renjumenon.videoapp.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by renju.menon on 06,October,2018
 */
@Module
class HomeModule(val view: HomeContract.View) {
    @Provides
    @FragmentScope
    fun provideHomeContractView() = view
}