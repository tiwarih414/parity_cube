package com.paritycube.paritycube_assignmernt.home.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.paritycube.paritycube_assignmernt.R
import com.paritycube.paritycube_assignmernt.Util.Util
import com.paritycube.paritycube_assignmernt.home.model.DealsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_common_adapter.view.*

class RecylerDataAdapter(
    private val context: Context,
    var list: ArrayList<DealsModel.Datum>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private var fRecord: Filter? = null
    var tempList: ArrayList<DealsModel.Datum> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_common_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var data = list[position]

        holder.itemView.tv_title.text = data.title
        holder.itemView.tv_like.text = data.voteCount.toString()
        holder.itemView.tv_comment.text = data.commentsCount.toString()

        holder.itemView.tv_createdat.text = data.createdAt.let { Util.getDate(it!!) }

        if (data.image != null && !TextUtils.isEmpty(data.image)) {
            Picasso.get().load(data.image)
                .placeholder(context.resources.getDrawable(R.drawable.ic_placeholder))
                .into(holder.itemView.img_icon)
        } else {
            holder.itemView.img_icon.setImageResource(R.drawable.ic_placeholder)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Nothing
    }

    override fun getFilter(): Filter {
        if (fRecord == null) {
            fRecord = RecordFilter()
        }
        return fRecord as Filter
    }

    inner class RecordFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val result = FilterResults()

            if (charSequence == null || charSequence.isEmpty()) {
                /*No Need to Filter*/
                list = tempList
                result.values = list
                result.count = list.size

            } else {
                val fRecords: ArrayList<DealsModel.Datum> = ArrayList()
                for (s in list) {
                    val docnum = s.title.toString()
                    if (docnum.contains(charSequence.toString().toLowerCase())) {
                        fRecords.add(s)
                    }
                }

                result.values = fRecords
                result.count = fRecords.size

            }

            return result
        }

        override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
            if (filterResults != null) {
                list = filterResults.values as ArrayList<DealsModel.Datum>
            }
            notifyDataSetChanged()
        }

    }
}