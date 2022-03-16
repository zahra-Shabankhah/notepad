package gaur.himanshu.august.notebook.adapters

import gaur.himanshu.august.notebook.model.NoteBook

interface ClickHandler {

    fun handleLongClick(noteBook: NoteBook)

    fun handleClick(noteBook: NoteBook)

}