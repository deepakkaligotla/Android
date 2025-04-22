package in.kaligotla.menudemo2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "text")
    public String text;
    public boolean isArchived;
    public Note(String text) {
        this.text = text;
        this.isArchived = false;
    }
    @Override
    public String toString() {
        return text;
    }
}
