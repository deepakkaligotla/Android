package `in`.kaligotla.dialogdemo2

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.Toast

class CustomDialog(context: Context): Dialog(context) {
    private lateinit var btnYes: Button
    private lateinit var btnNo: Button

    init {
        setContentView(R.layout.custom_dialog)
        btnYes = findViewById(R.id.btnYes)
        btnNo = findViewById(R.id.btnNo)
        initListeners()
    }

    private fun initListeners() {
        btnYes.setOnClickListener(MyDialogButtonClickListener())
        btnNo.setOnClickListener(MyDialogButtonClickListener())
    }

    inner class MyDialogButtonClickListener(): View.OnClickListener {
        override fun onClick(v: View?) {
            if(v?.id == R.id.btnYes) {
                Toast.makeText(context, "Yes Clicked", Toast.LENGTH_LONG).show()
                dismiss()
            }
            if(v?.id == R.id.btnNo) {
                Toast.makeText(context, "No Clicked", Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }
}