package `in`.kaligotla.fragmentsdemo1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import `in`.kaligotla.fragmentsdemo1.databinding.CounterFragmentBinding

class CounterFragment: Fragment() {
    private lateinit var counterFragmentBinding: CounterFragmentBinding
    private lateinit var displayCount: TextView
    private lateinit var btnIncrement: Button
    private lateinit var btnDecrement: Button
    private var count = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Way 01
        val fragmentView = inflater.inflate(R.layout.counter_fragment, null)
        //Way 02
        counterFragmentBinding = CounterFragmentBinding.inflate(inflater)

        initViews(fragmentView)
        initListeners()

//        return counterFragmentBinding.root
        return fragmentView
    }

    private fun initViews(fragmentView: View) {
        displayCount = fragmentView.findViewById(R.id.displayCount)
        btnIncrement = fragmentView.findViewById(R.id.btnIncreament)
        btnDecrement = fragmentView.findViewById(R.id.btnDecrement)
    }

    private fun initListeners() {
        btnIncrement.setOnClickListener(BtnIncrementClickListener())
        btnDecrement.setOnClickListener {
            if(count > 0) { count-- }
            displayCount.text = "$count"
        }
    }

    inner class BtnIncrementClickListener: View.OnClickListener {
        override fun onClick(v: View?) {
            count++
            displayCount.text = "$count"
        }
    }
}