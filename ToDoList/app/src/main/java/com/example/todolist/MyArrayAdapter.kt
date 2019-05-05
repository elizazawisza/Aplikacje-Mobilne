package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class MyArrayAdapter(context: Context, val data: ArrayList<Item>)   :
    ArrayAdapter<Item>(context, R.layout.item_layout, data) {

    private val inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {

            view = inflater.inflate(R.layout.item_layout, parent, false)

            holder = ViewHolder()
            holder.itemIconImageView = view.findViewById(R.id.itemImage) as ImageView
            holder.titleTextView = view.findViewById(R.id.item_title) as TextView
            holder.dateTextView = view.findViewById(R.id.item_time) as TextView
            holder.priorityTextView = view.findViewById(R.id.itemPriority) as TextView
            holder.itemDoneCheckBox = view.findViewById(R.id.done) as CheckBox

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val dateTextView = holder.dateTextView
        val priorityTextView = holder.priorityTextView
        val itemIconImageView = holder.itemIconImageView
        val itemDoneCheckBox = holder.itemDoneCheckBox

        titleTextView.text = data[position].name
        dateTextView.text = data[position].date
        priorityTextView.text = data[position].priority
        when (data[position].icon) {
            "books" -> {
                itemIconImageView.setImageResource(R.drawable.books)
            }
            "calendar" -> {
                itemIconImageView.setImageResource(R.drawable.calendar)
            }
            "capsules" -> {
                itemIconImageView.setImageResource(R.drawable.capsules)
            }
            "cart" -> {
                itemIconImageView.setImageResource(R.drawable.cart)
            }
            "coding" -> {
                itemIconImageView.setImageResource(R.drawable.coding)
            }
            "film_reel" -> {
                itemIconImageView.setImageResource(R.drawable.film_reel)
            }
            "friendship" -> {
                itemIconImageView.setImageResource(R.drawable.friendship)
            }
            "phone" -> {
                itemIconImageView.setImageResource(R.drawable.phone)
            }
        }

        itemDoneCheckBox.setOnClickListener(){
            data.removeAt(position)
            itemDoneCheckBox.toggle()
            notifyDataSetChanged()
        }



        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var dateTextView: TextView
        lateinit var priorityTextView: TextView
        lateinit var itemIconImageView: ImageView
        lateinit var itemDoneCheckBox: CheckBox

    }



}
