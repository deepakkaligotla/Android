package `in`.kaligotla.fragmentsdemo1

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.fragmentsdemo1.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var firstFragment: FirstFragment
    private lateinit var secondFragment: SecondFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        firstFragment = supportFragmentManager.findFragmentById(R.id.firstFragment) as FirstFragment
        secondFragment = supportFragmentManager.findFragmentById(R.id.secondFragment) as SecondFragment
        firstFragment.onNameSetListener = MyOnNameSetListener()
        secondFragment.onCitySetListener = MyOnCitySetListener()
    }

    fun sendDataToFirstFragment(text: String) {
        firstFragment.view?.findViewById<TextView>(R.id.textCity)?.text = text
    }

    fun sendDataToSecondFragment(text: String) {
        secondFragment.view?.findViewById<TextView>(R.id.txtName)?.text = text
    }

    inner class MyOnNameSetListener: FirstFragment.OnNameSetListener {
        override fun onNameSet(text: String) {
            secondFragment.view?.findViewById<TextView>(R.id.txtName)?.text = text
        }
    }

    inner class MyOnCitySetListener: SecondFragment.OnCitySetListener {
        override fun onCitySet(text: String) {
            firstFragment.view?.findViewById<TextView>(R.id.textCity)?.text = text
        }
    }
}