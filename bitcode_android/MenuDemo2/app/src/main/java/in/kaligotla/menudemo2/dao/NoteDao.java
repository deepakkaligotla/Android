package in.kaligotla.menudemo2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import in.kaligotla.menudemo2.model.Note;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes WHERE isArchived = 0")
    LiveData<List<Note>> getAllActiveNotes();

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();
}
