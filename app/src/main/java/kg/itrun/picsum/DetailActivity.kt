package kg.itrun.picsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alexvasilkov.gestures.views.GestureImageView
import com.bumptech.glide.Glide
import kg.itrun.picsum.api.ImageListItem
import kg.itrun.picsum.api.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val id = intent.getStringExtra("id") ?: "0"
        NetworkHelper.getService()
                .getDetail(id)
                .enqueue(object : Callback<ImageListItem> {
                    override fun onFailure(call: Call<ImageListItem>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<ImageListItem>, response: Response<ImageListItem>) {
                       if (response.isSuccessful && response.body() != null) {
                           showImageInfo(response.body()!!)
                       }
                    }

                })

    }

    private fun showImageInfo(item: ImageListItem) {
        val author = findViewById<TextView>(R.id.detail_author)
        val width = findViewById<TextView>(R.id.detail_width)
        val heigth = findViewById<TextView>(R.id.detail_height)
        val image = findViewById<GestureImageView>(R.id.detail_image)

        author.text = item.author
        width.text = "${item.width}px"
        heigth.text = "${item.height}px"

        val imageUrl = item.download_url
        Glide.with(this).load(imageUrl).into(image)
    }
}