package `in`.kaligotla.fragmentsdemo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import `in`.kaligotla.fragmentsdemo1.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var fragmentSecondBinding: FragmentSecondBinding
    interface OnCitySetListener {
        fun onCitySet(text: String)
    }
    lateinit var onCitySetListener: OnCitySetListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSecondBinding = FragmentSecondBinding.inflate(inflater)
        fragmentSecondBinding.edtCity.addTextChangedListener {

            //Way 3
//            onCitySetListener.onCitySet(
//                fragmentSecondBinding.edtCity.text.toString()
//            )

            //Way 2
//            (requireActivity() as MainActivity).sendDataToFirstFragment(it.toString())

            //Way 1
//            var firstFragmentView =
//                (parentFragmentManager.findFragmentById(R.id.firstFragment) as FirstFragment).view
//            val textViewCity = firstFragmentView?.findViewById<TextView>(R.id.textCity)
//            textViewCity?.text = it.toString()
        }
        return fragmentSecondBinding.root
    }
}