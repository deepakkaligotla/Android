package `in`.kaligotla.fragmentsdemo1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import `in`.kaligotla.fragmentsdemo1.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var fragmentFirstBinding: FragmentFirstBinding
    interface OnNameSetListener {
        fun onNameSet(text: String)
    }
    lateinit var onNameSetListener: OnNameSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFirstBinding = FragmentFirstBinding.inflate(inflater)
        fragmentFirstBinding.edtName.addTextChangedListener {

//            Way 3
            onNameSetListener.onNameSet(
                fragmentFirstBinding.edtName.text.toString()
            )

//            Way 2
            (requireActivity() as MainActivity).sendDataToSecondFragment(it.toString())

//            Way 1
            val secondFragment = parentFragmentManager.findFragmentById(R.id.secondFragment) as SecondFragment
            Log.e("tag", "${secondFragment.view}")
            val textView = secondFragment.view?.findViewById<TextView>(R.id.txtName)
            textView?.text = it?.toString()
        }
        return fragmentFirstBinding.root
    }
}