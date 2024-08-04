package com.example.jforcetest.UI.activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jforcetest.models.Attendance




import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.jforcetest.databinding.ItemAttendanceBinding


class AttendanceAdapter : ListAdapter<Attendance, AttendanceAdapter.AttendanceViewHolder>(AttendanceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding = ItemAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttendanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val attendance = getItem(position)
        holder.bind(attendance)
    }

    class AttendanceViewHolder(private val binding: ItemAttendanceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attendance: Attendance) {
            binding.textViewDate.text = attendance.date
            binding.textViewSignIn.text = attendance.signInTime ?: "Not Signed In"
            binding.textViewSignOut.text = attendance.signOutTime ?: "Not Signed Out"
        }
    }
}

class AttendanceDiffCallback : DiffUtil.ItemCallback<Attendance>() {
    override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
        return oldItem == newItem
    }
}




//class AttendanceAdapter(private val itemList: List<Attendance>) :
//    RecyclerView.Adapter<AttendanceAdapter.ItemViewHolder>() {
//
//    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
//        val textViewSignIn: TextView = itemView.findViewById(R.id.textViewSignIn)
//        val textViewSignOut: TextView = itemView.findViewById(R.id.textViewSignOut)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_attendance_records, parent, false)
//        return ItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        val item = itemList[position]
//        holder.textViewDate.text = item.currentDate
//        holder.textViewSignIn.text = "Sign In - ${item.signInDate}"
//        holder.textViewSignOut.text = "Sign Out - ${item.signOutDate}"
//    }
//
//    override fun getItemCount(): Int {
//        return itemList.size
//    }
//}

