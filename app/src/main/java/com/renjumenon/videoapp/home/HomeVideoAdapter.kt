package com.renjumenon.videoapp.home
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.renjumenon.videoapp.models.VideoContent
import com.renjumenon.videoapp.BuildConfig
import com.renjumenon.videoapp.R




/**
 * Created by renju.menon on 06,October,2018
 */
class HomeVideoAdapter(var videoContentList: List<VideoContent>, var clickListener: OnItemClickListener) :
        RecyclerView.Adapter<HomeVideoAdapter.VideoViewHolder>() {

    override fun onBindViewHolder(p0: VideoViewHolder, position: Int) {
        val videoContent = videoContentList[position]
        p0?.mTags?.text = videoContent.tags
        p0?.mLikes?.text = "Likes: " + videoContent.likes.toString()
        p0?.mFavorites?.text = "Favorites: " + videoContent.favorites.toString()
        if (videoContent.picture_id.isNotEmpty()) {
            Glide.with(p0?.mTags?.context)
                    .load(BuildConfig.IMG_BASE_URL + videoContent.picture_id + "_640x360.jpg")
                    .into(p0?.mVideoImg)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VideoViewHolder {
        return VideoViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.video_item, p0, false), clickListener)
    }





    override fun getItemCount(): Int {
        return videoContentList.size
    }

    fun getVideo(adapterPosition: Int): VideoContent {
        return videoContentList[adapterPosition]
    }

    inner class VideoViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var mTags: TextView = itemView.findViewById<TextView>(R.id.tags)
        var mLikes: TextView = itemView.findViewById<TextView>(R.id.likes)
        var mFavorites: TextView = itemView.findViewById<TextView>(R.id.favorites)
        var mVideoImg: ImageView = itemView.findViewById<ImageView>(R.id.video_item)
        var mBtnPlay: ImageView = itemView.findViewById<ImageView>(R.id.iv_button)

        init {
            mBtnPlay.setOnClickListener { v -> listener.onViewClicked(v, adapterPosition) }
        }
    }
}