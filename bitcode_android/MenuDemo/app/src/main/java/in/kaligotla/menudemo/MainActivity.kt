package `in`.kaligotla.menudemo

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.menudemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        registerForContextMenu(activityMainBinding.textView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 1, 0, "Open Detail")
        menu.add(0, 2, 1, "Share Message")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            1 -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("message", "Hello from MainActivity!")
                startActivity(intent)
                true
            }
            2 -> {
                shareText(activityMainBinding.textView.text.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // === Context Menu ===
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu.setHeaderTitle("Context Menu")
        menu.add(0, 10, 0, "Open Detail")
        menu.add(0, 11, 1, "Share Message")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            10 -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("message", "Hello from MainActivity!" )
                startActivity(intent)
                true
            }
            11 -> {
                shareText(activityMainBinding.textView.text.toString())
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser = Intent.createChooser(intent, "Share via")
        startActivity(chooser)
    }
}