package com.pankti.googleadsdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pankti.googleadsdemo.databinding.LayoutListItemBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : LayoutListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.layout_list_item, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 10

    class ViewHolder(binding: LayoutListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){}
    }

}