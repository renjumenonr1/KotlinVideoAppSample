package com.renjumenon.videoapp.models

import java.io.Serializable;
/**
 * Created by renju.menon on 06,October,2018
 */
data class VideoContent(val picture_id: String,
                        val likes: Int,
                        var videos: Videos,
                        val favorites: Int,
                        val tags: String,
                        val downloads: Int,
                        val duration: Int,
                        val userImageURL: String,
                        val type: String,
                        val user: String) : Serializable