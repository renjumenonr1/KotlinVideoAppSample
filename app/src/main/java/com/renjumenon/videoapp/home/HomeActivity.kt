package com.renjumenon.videoapp.home

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.renjumenon.videoapp.R
import com.renjumenon.videoapp.utils.ActivityUtils

import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener {


    @Inject
    lateinit var homePresenter: HomePresenter
    lateinit var homeFragment: HomeFragment

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeFragment = supportFragmentManager.findFragmentById(R.id.frame_layout_content)
                as HomeFragment? ?: HomeFragment.newInstance().also {
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, it, R.id.frame_layout_content)
        }

        homeFragment = HomeFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_layout_content, homeFragment)
        transaction.commit()

        /*
        * thanks to the lazy statement, we can specify the value of the graph in the definition of the property,
        * thus get read-only access to that property. The code defined by the property won’t be executed until component.inject (this) is done,
        * so that by that time this already exists and can be created securely way.*/
        val component: HomeComponent by lazy {
            DaggerHomeComponent
                    .builder()
                    .homeModule(HomeModule(homeFragment))
                    .build()
        }

        component.inject(this)
    }


}
