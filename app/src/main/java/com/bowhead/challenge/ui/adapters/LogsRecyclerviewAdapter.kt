package com.bowhead.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bowhead.challenge.R
import com.bowhead.challenge.models.LogItem

interface OnItemClickListener{
    fun OnLogClick()
}

class LogsRecyclerviewAdapter(private var logList:ArrayList<LogItem>):RecyclerView.Adapter<LogsRecyclerviewAdapter.LogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.log_item,parent,false)
        return LogsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {

        holder.setData(logList[position])
    }
    public fun addAll(added:List<LogItem>){
        logList.clear()
        logList.addAll(added)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    inner class LogsViewHolder:RecyclerView.ViewHolder{
        private lateinit var feeling:TextView
        private lateinit var sleepQuality:TextView
        constructor(itemView: View):super(itemView){
            feeling=itemView.findViewById<TextView>(R.id.logItem_feeling)
            sleepQuality=itemView.findViewById<TextView>(R.id.logItem_sleepQuality)
        }

        fun setData(logItem: LogItem){
            feeling.text=logItem.feeling
            sleepQuality.text=logItem.sleepQuality
        }
    }
}