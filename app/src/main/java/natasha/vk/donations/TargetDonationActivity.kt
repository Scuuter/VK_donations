package natasha.vk.donations

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class TargetDonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.target_donation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.target_donation_title)
        supportActionBar?.title = title.text

        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinner: Spinner = findViewById(R.id.money_sources)
        ArrayAdapter.createFromResource(
            this,
            R.array.money_sources,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val button = findViewById<Button>(R.id.next)
        button?.setOnClickListener { startActivity(Intent(this, TargetDonationExtraActivity::class.java)) }

    }
}