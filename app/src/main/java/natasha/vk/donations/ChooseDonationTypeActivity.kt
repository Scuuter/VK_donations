package natasha.vk.donations

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ChooseDonationTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_donation_type)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.choose_donation_type_title)
        supportActionBar?.title = title.text

        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val button = findViewById<Button>(R.id.target_donation)
        button?.setOnClickListener { startActivity(Intent(this, TargetDonationExtraActivity::class.java)) }

    }
}