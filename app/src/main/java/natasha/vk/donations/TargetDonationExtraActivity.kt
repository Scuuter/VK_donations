package natasha.vk.donations

import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.calendar.view.*
import java.util.*


class TargetDonationExtraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.target_donation_extra)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.target_donation_extra_title)
        supportActionBar?.title = title.text

        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.authors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val buttonCreateCollection = findViewById<Button>(R.id.create_collection)
        val textViewExpirationDate = findViewById<TextView>(R.id.expiration_date)
        val textViewDateText = findViewById<TextView>(R.id.dateText)

        val collectionDone = findViewById<RadioButton>(R.id.collection_done)
        collectionDone.setOnClickListener {
            buttonCreateCollection.alpha = 1.0f
            buttonCreateCollection.isClickable = true
            textViewExpirationDate.visibility = View.GONE
            textViewDateText.text = "Выберите дату"
            textViewDateText.visibility = View.GONE
        }

        val concreteDate = findViewById<RadioButton>(R.id.concrete_date)
        concreteDate.setOnClickListener {
            buttonCreateCollection.alpha = 0.4f
            buttonCreateCollection.isClickable = false
            textViewExpirationDate.visibility = View.VISIBLE
            textViewDateText.visibility = View.VISIBLE
        }

        textViewDateText.setOnClickListener {
            //Set spinner/calendar date picker layout
            val spinnerDatePicker = layoutInflater.inflate(R.layout.calendar, null)
            spinnerDatePicker.datePicker.minDate = Calendar.getInstance().timeInMillis - 1

            // On click listener for dialog buttons
            val dialogClickListener = DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        textViewDateText.text =
                            spinnerDatePicker.datePicker.dayOfMonth.toString() + "/" + (spinnerDatePicker.datePicker.month + 1) + "/" + spinnerDatePicker.datePicker.year
                        buttonCreateCollection.alpha = 1.0f
                        buttonCreateCollection.isClickable = true
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {

                    }
                }
            }


            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.expiration_date))
                .setView(spinnerDatePicker)
            builder.setPositiveButton(
                Html.fromHtml("<font color='#000000'>Готово</font>"),
                dialogClickListener
            )
            builder.setNegativeButton(
                Html.fromHtml("<font color='#000000'>Отмена</font>"),
                dialogClickListener
            )
            builder
                .create()
                .show()
        }
    }

}