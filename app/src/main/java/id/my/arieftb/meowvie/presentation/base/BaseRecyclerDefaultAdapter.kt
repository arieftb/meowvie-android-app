package id.my.arieftb.meowvie.presentation.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerDefaultAdapter<D, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private val contents = mutableListOf<D>()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = contents.size

    fun addAll(contents: List<D>?) {
        if (!contents.isNullOrEmpty()) {
            this.contents.addAll(contents)
            notifyDataSetChanged()
        }
    }

    fun replaceAll(contents: List<D>?) {
        if (!contents.isNullOrEmpty()) {
            this.contents.clear()
            this.contents.addAll(contents)
            notifyDataSetChanged()
        }
    }

    protected fun getContent(position: Int): D = contents[position]

    protected fun getContents(): List<D> = contents
}