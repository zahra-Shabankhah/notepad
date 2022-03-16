package gaur.himanshu.august.notebook.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gaur.himanshu.august.notebook.R
import gaur.himanshu.august.notebook.adapters.ClickHandler
import gaur.himanshu.august.notebook.adapters.MyAdapter
import gaur.himanshu.august.notebook.model.NoteBook
import gaur.himanshu.august.notebook.viewmodel.NoteBookViewModel


class MainFragment : Fragment(),ClickHandler{


    lateinit var navController: NavController

    var adapter = MyAdapter(this)

    lateinit var viewModels: NoteBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModels = ViewModelProvider(this)[NoteBookViewModel::class.java]

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        val button = view.findViewById<FloatingActionButton>(R.id.add_notebook)
        button.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addFragment)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        context?.let { viewModels.getAllNotebooks(it) }
        viewModels.list.observe(viewLifecycleOwner, {
            adapter.setContentList(it)
            recycler.also { recycler ->
                recycler.adapter = adapter
            }
        })


    }

    override fun handleLongClick(noteBook: NoteBook) {
       AlertDialog.Builder(requireContext()).setMessage("Are You Sure?").setPositiveButton("Ok"){_,_ ->
           context?.let { viewModels.delete(it,noteBook) }
       }
           .setNegativeButton("Cancel"){_,_ ->}.show()
    }

    override fun handleClick(noteBook: NoteBook) {
        val bundle= bundleOf("note_book" to noteBook)
        navController.navigate(R.id.action_mainFragment_to_editFragment,bundle)
    }


}