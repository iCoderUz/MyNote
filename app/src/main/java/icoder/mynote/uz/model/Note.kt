package icoder.mynote.uz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
    /*@field:ColumnInfo(name = "title") */var title: String,
    var description: String,
    var date: String,
    var priority: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}