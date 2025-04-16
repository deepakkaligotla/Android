package in.kaligotla.day06;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    ImageView imageView;
    private final int SAVE=1, DELETE = 2, DELETE_ONE = 3, DELETE_ALL = 4, SAVE_AS=5, COPY=6, COPY_LINK=7, DOWNLOAD=8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = findViewById(R.id.imgView);
        registerForContextMenu(imageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Toast.makeText(this, "onCreateContextMenu Called", Toast.LENGTH_SHORT).show();
        menu.add(1, SAVE, 1, "Save");
        SubMenu delete = menu.addSubMenu(2, DELETE, 2, "Delete");
        delete.add(2, DELETE_ONE, 2, "Delete one");
        delete.add(2, DELETE_ALL, 1, "Delete All");
        menu.add(3, SAVE_AS, 2, "Save As");
        menu.add(4, COPY, 3, "Copy");
        menu.add(5, COPY_LINK, 4, "Copy Link");
        menu.add(6, DOWNLOAD, 5, "Download");
    }

    @Override
    public void openContextMenu(View view) {
        super.openContextMenu(view);
        Toast.makeText(this, "openContextMenu Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case SAVE:
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
                break;
            case DELETE:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case DELETE_ONE:
                Toast.makeText(this, "Delete One", Toast.LENGTH_SHORT).show();
                break;
            case DELETE_ALL:
                Toast.makeText(this, "Delete All", Toast.LENGTH_SHORT).show();
                break;
            case SAVE_AS:
                Toast.makeText(this, "Save As", Toast.LENGTH_SHORT).show();
                break;
            case COPY:
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                break;
            case COPY_LINK:
                Toast.makeText(this, "Copy Link", Toast.LENGTH_SHORT).show();
                break;
            case DOWNLOAD:
                Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Default Case", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
        Toast.makeText(this, "onContextMenuClosed Called ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeContextMenu() {
        super.closeContextMenu();
        Toast.makeText(this, "closeContextMenu Called", Toast.LENGTH_SHORT).show();
    }
}