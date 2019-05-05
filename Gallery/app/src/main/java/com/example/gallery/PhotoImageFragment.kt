package com.example.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_photo_image.*

class PhotoImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity?.intent != null) {
            val extra2 = activity!!.intent.getByteArrayExtra("imageName")
            if(extra2 != null){
                val bmp = BitmapFactory.decodeByteArray(extra2, 0, extra2.size)
                showImage(bmp)
            }
        }
    }

    fun showImage(s : Bitmap) {
        imageView11.setImageBitmap(s)
    }

}