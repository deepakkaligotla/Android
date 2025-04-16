package in.kaligotla.menudemo2.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import in.kaligotla.menudemo2.model.Note;
import in.kaligotla.menudemo2.viewModel.NoteViewModel;
import in.kaligotla.menudemo2.R;
import in.kaligotla.menudemo2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NoteViewModel noteViewModel;
    NotesFragment notesFragment;
    boolean showingArchived = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        notesFragment = (NotesFragment) getSupportFragmentManager().findFragmentById(R.id.notesFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            showAddNoteDialog();
            return true;
        } else if (id == R.id.action_toggle_archived) {
            showingArchived = !showingArchived;
            item.setTitle(showingArchived ? "Hide Archived" : "Show Archived");
            if (notesFragment != null) notesFragment.toggleArchived();
            return true;
        } else if (id == R.id.action_sync) {
            syncNotesWithCloud();
            return true;
        } else if (id == R.id.action_export) {
            exportNotes();
            return true;
        } else if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddNoteDialog() {
        EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("New Note")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String text = input.getText().toString();
                    if (!text.isEmpty()) {
                        noteViewModel.insert(new Note(text));
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void exportNotes() {
        Toast.makeText(this, "Exporting notes...", Toast.LENGTH_SHORT).show();
    }

    private void openSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private void syncNotesWithCloud() {
        Toast.makeText(this, "Notes synced with cloud", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("RestrictedApi")
    public static void showIcons(Menu menu) {
        try {
            @SuppressLint("PrivateApi")
            Class<?> menuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
            if (menuBuilderClass.isInstance(menu)) {
                @SuppressLint("DiscouragedPrivateApi")
                java.lang.reflect.Method method = menuBuilderClass.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
                method.setAccessible(true);
                method.invoke(menu, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}