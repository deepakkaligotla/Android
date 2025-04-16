package `in`.kaligotla.lifecycle

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import `in`.kaligotla.lifecycle.databinding.FragmentCommonViewBinding

class CommonViewFragment : Fragment() {
    private lateinit var fragmentCommonViewBinding: FragmentCommonViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCommonViewBinding = FragmentCommonViewBinding.inflate(inflater, container, false)
        return fragmentCommonViewBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCommonViewBinding.tvTaskInfo.text = getTaskInfo(view.context)
        initListeners(view.context)
    }

    private fun initListeners(context: Context) {
        fragmentCommonViewBinding.btnOpenSameActivity.setOnClickListener {
            startActivity(Intent(context, StandardActivity::class.java))
        }
        fragmentCommonViewBinding.btnOpenSecondActivity.setOnClickListener {
            startActivity(Intent(context, SingleTopActivity::class.java))
        }
        fragmentCommonViewBinding.btnOpenThirdActivity.setOnClickListener {
            val intent = Intent(context, SingleTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        fragmentCommonViewBinding.btnOpenFourthActivity.setOnClickListener {
            startActivity(Intent(context, SingleInstanceActivity::class.java))
        }
        fragmentCommonViewBinding.btnOpenfifthActivity.setOnClickListener {
            startActivity(Intent(context, SingleInstancePerTaskActivity::class.java))
        }
    }
}