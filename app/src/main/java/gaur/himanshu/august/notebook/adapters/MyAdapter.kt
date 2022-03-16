package gaur.himanshu.august.notebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gaur.himanshu.august.notebook.R
import gaur.himanshu.august.notebook.model.NoteBook

class MyAdapter(val clickHandler: ClickHandler) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var list = listOf<NoteBook>()

    fun setContentList(list: List<NoteBook>) {
        this.list = list
        notifyDataSetChanged()
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById<TextView>(R.id.title_list_item)
        val desc = view.findViewById<TextView>(R.id.desc_list_item)
        val root=view.findViewById<RelativeLayout>(R.id.root_list_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_notebook, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.desc.text = list[position].desc
        holder.root.setOnLongClickListener {
            clickHandler.handleLongClick(list[position])
            return@setOnLongClickListener true
        }
        holder.root.setOnClickListener {
            clickHandler.handleClick(list[position])
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}