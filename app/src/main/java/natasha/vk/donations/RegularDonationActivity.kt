package natasha.vk.donations

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
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

        val sumExample = findViewById<TextInputEditText>(R.id.example_sum)
        sumExample?.hint = getString(R.string.example_regular_sum)

        val aimExample = findViewById<TextInputEditText>(R.id.example_aim)
        aimExample?.hint = getString(R.string.example_regular_aim)
    }
}