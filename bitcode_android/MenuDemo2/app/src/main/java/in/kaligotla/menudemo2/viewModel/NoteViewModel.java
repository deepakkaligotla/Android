package in.kaligotla.menudemo2.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import in.kaligotla.menudemo2.model.Note;
import in.kaligotla.menudemo2.repo.NoteRepository;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allActiveNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
        allActiveNotes = repository.getAllActiveNotes();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    public LiveData<List<Note>> getAllActiveNotes() {
        return allActiveNotes;
    }
    public void insert(Note note) {
        repository.insert(note);
    }
    public void delete(Note note) {
        repository.delete(note);
    }
    public void update(Note note) {
        repository.update(note);
    }
}
