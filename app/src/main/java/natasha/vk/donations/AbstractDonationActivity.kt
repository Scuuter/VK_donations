package natasha.vk.donations

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import java.io.IOException

abstract class AbstractDonationActivity : AppCompatActivity() {
    var imageUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_donation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val moneySpinner: Spinner? = findViewById(R.id.money_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.money_sources,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            moneySpinner?.adapter = adapter
        }

        val authorSpinner: Spinner? = findViewById(R.id.author_spinner)

        val buttonNext = findViewById<Button>(R.id.next)

        val name = findViewById<TextInputEditText>(R.id.example_name)

        val sum = findViewById<TextInputEditText>(R.id.example_sum)

        val aim = findViewById<TextInputEditText>(R.id.example_aim)

        val descr = findViewById<TextInputEditText>(R.id.example_aim)

        buttonNext?.setOnClickListener {
            val intent = Intent(this, TargetDonationExtraActivity::class.java)
            intent.putExtra("name", name?.text)
            intent.putExtra("sum", sum?.text)
            intent.putExtra("aim", aim?.text)
            intent.putExtra("descr", descr?.text)
            intent.putExtra("moneyId", moneySpinner?.selectedItemId)
            intent.putExtra("author", authorSpinner?.selectedItem?.toString())

            intent.putExtra("imageUri", imageUri)
            startActivity(intent)
        }

        val uploadImage: Button = findViewById(R.id.upload_image)
        uploadImage.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        imageReturnedIntent: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        val uploadImage: Button = findViewById(R.id.upload_image)
        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                val imageUri: Uri = imageReturnedIntent?.data ?: return
                uploadImage.background = getDrawableFromUri(imageUri)
                this.imageUri = imageUri
            }
        }
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

    companion object {
        private const val GALLERY_REQUEST = 1
    }
}