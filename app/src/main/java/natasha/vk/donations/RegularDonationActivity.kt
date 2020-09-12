package natasha.vk.donations

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText

class RegularDonationActivity : AbstractDonationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.regular_donation_title)
        supportActionBar?.title = title.text

        val authorSpinner: Spinner? = findViewById(R.id.author_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.authors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            authorSpinner?.adapter = adapter
        }

        val authorLayout: LinearLayout? = findViewById(R.id.author_layout)
        authorLayout?.visibility = View.VISIBLE

        val fieldSum = findViewById<TextView>(R.id.field_sum)
        fieldSum?.text = getString(R.string.field_regular_sum)

        val sum = findViewById<TextInputEditText>(R.id.example_sum)
        sum?.hint = getString(R.string.example_regular_sum)

        val aim = findViewById<TextInputEditText>(R.id.example_aim)
        aim?.hint = getString(R.string.example_regular_aim)

        val moneySpinner: Spinner? = findViewById(R.id.money_spinner)


        val buttonNext = findViewById<Button>(R.id.next)

        val name = findViewById<TextInputEditText>(R.id.example_name)


        val descr = findViewById<TextInputEditText>(R.id.example_aim)

        buttonNext?.setOnClickListener {
            val intent = Intent(this, PostingActivity::class.java)
            intent.putExtra("name", name?.text)
            intent.putExtra("sum", sum?.text)
            intent.putExtra("aim", aim?.text)
            intent.putExtra("descr", descr?.text)
            intent.putExtra("moneyId", moneySpinner?.selectedItemId)
            intent.putExtra("author", authorSpinner?.selectedItem?.toString())

            intent.putExtra("imageUri", imageUri)
            startActivity(intent)
        }
    }
}