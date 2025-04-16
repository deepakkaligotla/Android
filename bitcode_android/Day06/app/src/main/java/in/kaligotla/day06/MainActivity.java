package in.kaligotla.day06;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final int HISTORY_MENU = 1,
    SETTINGS_MENU = 2,
    TRANSLATE_MENU = 3,
    DISPLAY_MENU =4,
    BLUETOOTH_MENU =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
         Menu is of 2 types
            * options Menu
            * context Menu

            GroupID-i1, ItemID-i2, order, title arguments for Menu obj
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toast.makeText(this, "onCreateOptionsMenu Called", Toast.LENGTH_SHORT).show();
        MenuItem historyMenuItem = menu.add(1, HISTORY_MENU, 1, "History");
        SubMenu settingsMenu = menu.addSubMenu(2, SETTINGS_MENU, 2, "Settings");
        settingsMenu.add(2, DISPLAY_MENU, 3, "Display");
        settingsMenu.add(2, BLUETOOTH_MENU, 4, "Bluetooth");
        MenuItem translateMenuItem = menu.add(3, TRANSLATE_MENU, 5, "Translate");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case HISTORY_MENU:
                Toast.makeText(this, "HistoryMenu Clicked", Toast.LENGTH_SHORT).show();
                break;
            case SETTINGS_MENU:
                Toast.makeText(this, "SettingsMenu Opened", Toast.LENGTH_SHORT).show();
                break;
            case DISPLAY_MENU:
                Toast.makeText(this, "DisplayMenu Clicked", Toast.LENGTH_SHORT).show();
                break;
            case BLUETOOTH_MENU:
                Toast.makeText(this, "BluetoothMenu Clicked", Toast.LENGTH_SHORT).show();
                break;
            case TRANSLATE_MENU:
                Toast.makeText(this, "TranslateMenu Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Default Case", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Toast.makeText(this, "onPrepareOptionsMenu Called", Toast.LENGTH_SHORT).show();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        Toast.makeText(this, "onOptionsMenuClosed Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
        Toast.makeText(this, "closeOptionsMenu Called", Toast.LENGTH_SHORT).show();
    }
}