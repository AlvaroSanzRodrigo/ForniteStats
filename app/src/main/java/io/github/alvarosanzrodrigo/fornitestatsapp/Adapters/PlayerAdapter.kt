package io.github.alvarosanzrodrigo.fornitestatsapp.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.alvarosanzrodrigo.fornitestatsapp.Models.Stat
import io.github.alvarosanzrodrigo.fornitestatsapp.R


class PlayerAdapter(var items: ArrayList<Stat>) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder>() {
    var mCallBack: OnClickedItemListener? = null

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var titleTextView = view.findViewById<TextView>(R.id.player_rv_item_title)
        private var valueTextView = view.findViewById<TextView>(R.id.player_rv_item_value)
        private var rankTextView = view.findViewById<TextView>(R.id.player_rv_item_rank)

        fun setValues(item: Stat){
            titleTextView.text = item.label
            valueTextView.text = item.displayValue
            rankTextView.text = item.rank.toString()
        }
    }


    //Interface! i's used when you click on a item
    interface OnClickedItemListener {
        fun onItemSelected(stat: Stat)
    }

    // Create new views (invoked by the grade_rv_item manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.player_rv_item, parent, false).apply {

        } as View

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the grade_rv_item manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setValues(items[position])
    }

    // Return the size of your dataset (invoked by the grade_rv_item manager)
    override fun getItemCount() = items.size
}