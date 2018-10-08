package com.renjumenon.videoapp.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.renjumenon.videoapp.R
import com.renjumenon.videoapp.models.VideoContent
import com.renjumenon.videoapp.models.VideoList
import com.renjumenon.videoapp.videoplayer.VideoPlayer
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment(), OnItemClickListener, HomeContract.View {

    var mListener: OnFragmentInteractionListener? = null
    var mVideoList: List<VideoContent>? = null
    var mMainAdapter: HomeVideoAdapter? = null
    var collapsingToolbar: CollapsingToolbarLayout? = null
    var mHomePresenter: HomeContract.Presenter? = null
    var appBarLayout: AppBarLayout? = null

    lateinit var mRecyclerView: RecyclerView
    lateinit var mToolbar: Toolbar
    lateinit var mLayoutManager: GridLayoutManager

    //Constants in Kotlin
    companion object {
        val TAG = HomeActivity::class.simpleName
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)
        initView(view)
        return view
    }

    fun initView(view: View) {
        collapsingToolbar = view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        appBarLayout = view.findViewById<AppBarLayout>(R.id.appbar)
        mToolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity)?.setSupportActionBar(mToolbar)
        initCollapsingToolbar()
        mRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        mLayoutManager = GridLayoutManager(activity, 1)
        mRecyclerView.setLayoutManager(mLayoutManager)
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        Glide.with(this).load(R.drawable.cover).into(view.findViewById<ImageView>(R.id.backdrop))
    }

    private fun initCollapsingToolbar() {
        collapsingToolbar?.title = " "
        appBarLayout?.setExpanded(true)

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout?.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar?.title = getString(R.string.app_name)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar?.title = " "
                    isShow = false
                }
            }
        })
    }

    override fun setPresenter(presenter: HomeContract.Presenter) {
        mHomePresenter = presenter
    }

    override fun onResume() {
        super.onResume()
        mHomePresenter?.getVideoList()
    }

    override fun showGetVideoList(response: Response<VideoList>?) {
        this@HomeFragment.mVideoList = response?.body()?.hits
        this@HomeFragment.mVideoList?.let {
            mMainAdapter = HomeVideoAdapter(this@HomeFragment.mVideoList!!, this@HomeFragment)
            mRecyclerView.adapter = mMainAdapter
        }
    }

    override fun showGetVideoOnFailure(call: Call<VideoList>?, t: Throwable?) {
        Log.e(TAG, "Failure:" + t)
        Log.e(TAG, "Failure:" + call?.request()?.url())
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onViewClicked(v: View, position: Int) {
        val intent = Intent(activity, VideoPlayer::class.java)
        intent.putExtra(VideoPlayer.VIDEO_CONTENT, mMainAdapter?.getVideo(position)?.videos?.large?.url)
        startActivity(intent)
    }
}

