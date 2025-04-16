package in.kaligotla.menudemo2.view;

import static in.kaligotla.menudemo2.view.MainActivity.showIcons;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import in.kaligotla.menudemo2.model.Note;
import in.kaligotla.menudemo2.viewModel.NoteViewModel;
import in.kaligotla.menudemo2.R;
import in.kaligotla.menudemo2.databinding.FragmentNotesBinding;

public class NotesFragment extends Fragment {
    private FragmentNotesBinding binding;
    private NoteViewModel noteViewModel;
    private ArrayAdapter<Note> adapter;
    private final ArrayList<Note> noteList = new ArrayList<>();
    private boolean showingArchived = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, noteList);
        binding.noteListView.setAdapter(adapter);
        registerForContextMenu(binding.noteListView);
        loadNotes();
    }

    public void loadNotes() {
        if (showingArchived) {
            noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                noteList.clear();
                noteList.addAll(notes);
                adapter.notifyDataSetChanged();
            });
        } else {
            noteViewModel.getAllActiveNotes().observe(getViewLifecycleOwner(), notes -> {
                noteList.clear();
                noteList.addAll(notes);
                adapter.notifyDataSetChanged();
            });
        }
    }

    public void toggleArchived() {
        showingArchived = !showingArchived;
        loadNotes();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        showIcons(menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if (info == null || info.position < 0 || info.position >= noteList.size()) return;
        Note selectedNote = noteList.get(info.position);
        MenuItem archiveItem = menu.findItem(R.id.context_archive);
        MenuItem detailsItem = menu.findItem(R.id.context_details);
        if (archiveItem != null) {
            archiveItem.setOnMenuItemClickListener(item -> {
                archiveNote(selectedNote);
                return true;
            });
        }
        if (detailsItem != null) {
            detailsItem.setOnMenuItemClickListener(item -> {
                showNoteDetails(selectedNote);
                return true;
            });
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info == null || info.position < 0 || info.position >= noteList.size()) return false;
        Note selectedNote = noteList.get(info.position);
        int id = item.getItemId();
        if (id == R.id.context_edit) {
            editNote(info.position);
            return true;
        } else if (id == R.id.context_share) {
            shareNote(selectedNote.text);
            return true;
        } else if (id == R.id.context_delete) {
            noteViewModel.delete(selectedNote);
            return true;
        } else if (id == R.id.context_archive) {
            archiveNote(selectedNote);
            return true;
        } else if (id == R.id.context_details) {
            showNoteDetails(selectedNote);
            return true;
        }
        return false;
    }
    private void editNote(int position) {
        Note currentNote = noteList.get(position);
        EditText input = new EditText(requireContext());
        input.setText(currentNote.text);
        new AlertDialog.Builder(requireContext())
                .setTitle("Edit Note")
                .setView(input)
                .setPositiveButton("Update", (dialog, which) -> {
                    currentNote.text = input.getText().toString();
                    noteViewModel.update(currentNote);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void shareNote(String noteText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, noteText);
        startActivity(Intent.createChooser(intent, "Share Note Via"));
    }

    private void archiveNote(Note note) {
        note.isArchived = !note.isArchived;
        noteViewModel.update(note);
        loadNotes();
        Toast.makeText(requireContext(), note.text + " is " + (note.isArchived ? "Archived" : "UnArchived"), Toast.LENGTH_SHORT).show();
    }

    private void showNoteDetails(Note note) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Note Details")
                .setMessage("Note:\n" + note.text)
                .setPositiveButton("OK", null)
                .show();
    }
}