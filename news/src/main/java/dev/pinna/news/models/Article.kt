package dev.pinna.news.models

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
data class Article (
    @PrimaryKey val title:String,
    @ColumnInfo(name= "description") val description:String,
    @ColumnInfo(name= "urlToImage") val urlToImage:String,
    @ColumnInfo(name= "url") val url:String,
    @ColumnInfo(name= "favorite") var favorite:Boolean = false
)