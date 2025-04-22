package in.kaligotla.menudemo2.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

import in.kaligotla.menudemo2.dao.NoteDao;
import in.kaligotla.menudemo2.db.NoteDatabase;
import in.kaligotla.menudemo2.model.Note;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allActiveNotes;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application) {
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDao = db.noteDao();
        allActiveNotes = noteDao.getAllActiveNotes();
        allNotes = noteDao.getAllNotes();
    }
    public void insert(Note note) {
        Executors.newSingleThreadExecutor().execute(() -> noteDao.insert(note));
    }
    public void delete(Note note) {
        Executors.newSingleThreadExecutor().execute(() -> noteDao.delete(note));
    }
    public void update(Note note) {
        Executors.newSingleThreadExecutor().execute(() -> noteDao.update(note));
    }
    public LiveData<List<Note>> getAllActiveNotes() {
        return allActiveNotes;
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
