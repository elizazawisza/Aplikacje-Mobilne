package com.example.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main2.*
import android.app.Activity
import android.content.Intent
import android.widget.RatingBar.OnRatingBarChangeListener


class PhotoInformationFragment : Fragment() {
    var MyPhotoRating = 0.0
    var ImageTag = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                if (fromUser) {
                    MyPhotoRating = rating.toDouble()
                }else{
                    MyPhotoRating = ratingBar.rating.toDouble()
                }
            }
        if (activity?.intent != null) {
            val extra = activity!!.intent.getStringExtra("data")
            if(extra!= null){
                display(extra)
            }
            if(activity!!.intent.getStringExtra("tag") != null){
                ImageTag = activity!!.intent.getStringExtra("tag")
            }
            ratingBar.rating = activity!!.intent.getDoubleExtra("rating", 1.0).toFloat()
        }
        buttonBack.setOnClickListener{ clickBack(it) }
    }

    fun display(s : String) {
        try{
            val description = s.toInt()
            textView.setText(description)
        }catch(e: NumberFormatException){
            textView.text = s
        }
    }

    fun clickBack(view: View){
        val myintent = Intent()
        myintent.putExtra("rating", MyPhotoRating)
        myintent.putExtra("tag", ImageTag)
        activity?.setResult(Activity.RESULT_OK, myintent)
        activity?.finish()
    }
}
