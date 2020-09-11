package natasha.vk.donations

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.fundraising_title)
        supportActionBar?.title = title.text

        val button = findViewById<Button>(R.id.create_donation_button)
        button?.setOnClickListener { startActivity(Intent(this, ChooseDonationTypeActivity::class.java)) }
    }
}