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
import java.io.IOException

abstract class AbstractDonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_donation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val moneySpinner: Spinner = findViewById(R.id.money_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.money_sources,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            moneySpinner.adapter = adapter
        }

        val buttonNext = findViewById<Button>(R.id.next)
        buttonNext?.setOnClickListener { startActivity(Intent(this, TargetDonationExtraActivity::class.java)) }

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
        var drawable: Drawable? = null
        val uploadImage: Button = findViewById(R.id.upload_image)
        when (requestCode) {
            GALLERY_REQUEST -> if (resultCode == Activity.RESULT_OK) {
                val imageUri: Uri = imageReturnedIntent?.data ?: return
                try {
                    val inputStream = contentResolver.openInputStream(imageUri)
                    drawable = Drawable.createFromStream(inputStream, imageUri.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                uploadImage.background = drawable
            }
        }
    }

    companion object {
        private const val GALLERY_REQUEST = 1
    }
}