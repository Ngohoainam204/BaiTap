package com.example.callblocker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BlockedNumbersAdapter(
    private var blockedNumbers: MutableList<String>,
    private val onDelete: (String) -> Unit
) : RecyclerView.Adapter<BlockedNumbersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPhoneNumber: TextView = view.findViewById(R.id.txtPhoneNumber)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blocked_number, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phoneNumber = blockedNumbers[position]
        holder.txtPhoneNumber.text = phoneNumber

        holder.btnDelete.setOnClickListener {
            onDelete(phoneNumber)
        }
    }

    override fun getItemCount(): Int = blockedNumbers.size

    fun updateList(newList: List<String>) {
        blockedNumbers.clear()
        blockedNumbers.addAll(newList)
        notifyDataSetChanged()
    }
}
