package kg.itrun.picsum.api

import com.google.gson.annotations.SerializedName

data class ImageListItem(
        @SerializedName("id") val id : String,
        @SerializedName("author") val author : String,
        @SerializedName("width") val width : Int,
        @SerializedName("height") val height : Int,
        @SerializedName("url") val url : String,
        @SerializedName("download_url") val download_url : String
)

