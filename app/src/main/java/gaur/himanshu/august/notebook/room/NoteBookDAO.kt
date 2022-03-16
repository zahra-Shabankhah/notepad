package gaur.himanshu.august.notebook.room

import androidx.room.*
import gaur.himanshu.august.notebook.model.NoteBook

@Dao
interface NoteBookDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteBook: NoteBook)

    @Delete
    suspend fun delete(noteBook: NoteBook)

    @Query("SELECT * FROM NoteBook")
    suspend fun getAllNotebook():List<NoteBook>


}