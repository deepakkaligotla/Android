package in.kaligotla.customdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button customDialogBtn1, customDialogBtn2, customDialogBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        customDialogBtn1 = findViewById(R.id.customDialogBtn1);
        customDialogBtn2 = findViewById(R.id.customDialogBtn2);
        customDialogBtn3 = findViewById(R.id.customDialogBtn3);
    }

    private void initListeners() {
        customDialogBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog loginDialog = new LoginDialog(MainActivity.this);
                loginDialog.setOnLoginDialogClickListener(new LoginDialogInterfaceClickListener());
                loginDialog.show();
            }
        });

        customDialogBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog loginDialog = new LoginDialog(MainActivity.this);
                loginDialog.show();
            }
        });

        customDialogBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.login_dialog);
                dialog.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Ok Clicked", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Cancel Clicked", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });
    }

    class LoginDialogInterfaceClickListener implements LoginDialog.OnLoginDialogClickListener {
        @Override
        public void onSuccess() {
            Log.e("tag", "Ok from On Success");
        }
        @Override
        public void onFailure() {
            Log.e("tag", "Cancel from On Failure");
        }
    }
}