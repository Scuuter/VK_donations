package natasha.vk.donations

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.IOException

class PostingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posting)

        val bundle = intent.extras
        val author = bundle?.getString("author")

        val toolbar = findViewById<Toolbar>(R.id.posting_toolbar)
        setSupportActionBar(toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = author?.substringBefore(" ")
        supportActionBar?.title = title.text

        supportActionBar?.setHomeAsUpIndicator(R.drawable.dismiss)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val uri: Uri? = bundle?.getParcelable("imageUri")

        val image = findViewById<View>(R.id.post_image)
        uri?. let { image.background = getDrawableFromUri(uri) }
    }

    fun getDrawableFromUri(uri: Uri): Drawable? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            Drawable.createFromStream(inputStream, uri.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}