package in.kaligotla.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class LoginDialog extends Dialog {
    Button btnOk, btnCancel;

    public interface OnLoginDialogClickListener {
        void onSuccess();
        void onFailure();
    }

    public OnLoginDialogClickListener onLoginDialogClickListener;

    public void setOnLoginDialogClickListener(OnLoginDialogClickListener onLoginDialogClickListener1) {
        this.onLoginDialogClickListener = onLoginDialogClickListener1;
    }

    public LoginDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.login_dialog);
        initViews();
        initListeners();
    }

    private void initViews() {
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void initListeners() {
        btnOk.setOnClickListener(new BtnOkClicked());
        btnCancel.setOnClickListener(new BtnCancelClicked());
    }

    class BtnOkClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "Ok Clicked", Toast.LENGTH_LONG).show();
            onLoginDialogClickListener.onSuccess();
        }
    }

    class BtnCancelClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "Cancel Clicked", Toast.LENGTH_LONG).show();
            onLoginDialogClickListener.onFailure();
        }
    }
}
