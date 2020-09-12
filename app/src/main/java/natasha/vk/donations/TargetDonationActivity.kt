package natasha.vk.donations

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class TargetDonationActivity : AbstractDonationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val title = toolbar.findViewById<TextView>(R.id.activity_title)
        title?.text = getString(R.string.target_donation_title)
        supportActionBar?.title = title.text
    }
}