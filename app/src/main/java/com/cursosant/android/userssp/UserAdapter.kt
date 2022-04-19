package com.cursosant.android.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursosant.android.userssp.databinding.ItemUserAltBinding

class UserAdapter(private val users: List<User>, private val listener: IOnClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        //val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alt, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        val humanPosition = position + 1

        with(holder){
            setListener(user, humanPosition) //seteo Listener
//          binding.tvOrder.text = user.id.toString()
            binding.tvOrder.text = (humanPosition).toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = users.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //val binding = ItemUserBinding.bind(view)
        val binding = ItemUserAltBinding.bind(view)

        //seteo Listener
        fun setListener(user: User, position: Int){
            binding.root.setOnClickListener { listener.onClick(user, position)}
        }
    }
}