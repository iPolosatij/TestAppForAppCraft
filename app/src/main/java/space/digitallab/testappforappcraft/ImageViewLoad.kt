package space.digitallab.testappforappcraft

import android.icu.number.NumberFormatter.with
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.squareup.picasso.Picasso


class ImageViewLoad : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view)

        var url = intent.getStringExtra("url")
        var image: ImageView = findViewById(R.id.imageView)
        loadImage(image, url)
    }


fun loadImage(im: ImageView, str: String?) {
    Picasso.get()
        .load(str)
        .fit()
        .centerInside()
        .into(im);
}
}

