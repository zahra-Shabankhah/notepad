package gaur.himanshu.august.notebook.repository

import android.content.Context
import gaur.himanshu.august.notebook.model.NoteBook
import gaur.himanshu.august.notebook.room.NoteBookDatabase

class NotebookRepo {
    suspend fun insert(context: Context, noteBook: NoteBook){
        NoteBookDatabase.get(context).getNotebookDao().insert(noteBook)
    }
    suspend fun delete(context: Context, noteBook: NoteBook){
        NoteBookDatabase.get(context).getNotebookDao().delete(noteBook)
    }
    suspend fun getAllNotebooks(context: Context):List<NoteBook>{
      return  NoteBookDatabase.get(context).getNotebookDao().getAllNotebook()
    }
}