package com.example.gallery


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_main.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


@Suppress("UsePropertyAccessSyntax")
class GridPhotoFragment : Fragment() {
    var Photos = ArrayList<Photo>()
    val ImageViewsArray = ArrayList<ImageView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            Photos = savedInstanceState.getParcelableArrayList("photos")
        }
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createImageViewsArray()
        if(Photos.size == 0){
            createPhotosArray()
        }
        prepareView()

        imageView.setOnClickListener { onClick(it) }
        imageView2.setOnClickListener { onClick(it) }
        imageView3.setOnClickListener { onClick(it) }
        imageView4.setOnClickListener { onClick(it) }
        imageView5.setOnClickListener { onClick(it) }
        imageView6.setOnClickListener { onClick(it) }
        imageView7.setOnClickListener { onClick(it) }
        imageView8.setOnClickListener { onClick(it) }
        imageView9.setOnClickListener { onClick(it) }
        imageView10.setOnClickListener { onClick(it) }
        imageView12.setOnClickListener { onClick(it) }
        imageView13.setOnClickListener { onClick(it) }
        imageView14.setOnClickListener { onClick(it) }
        imageView15.setOnClickListener { onClick(it) }
        imageView16.setOnClickListener { onClick(it) }
        imageView17.setOnClickListener { onClick(it) }
        imageView18.setOnClickListener { onClick(it) }
        imageView19.setOnClickListener { onClick(it) }
        imageView20.setOnClickListener { onClick(it) }
        imageView21.setOnClickListener { onClick(it) }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("photos", Photos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 222) {
            val s = data?.getDoubleExtra("rating", 0.0)
            val tag = data?.getStringExtra("tag")
            if (s != null && tag != null) {
                setPriority(s, tag)
            }
        }

    }

    fun setPriority(priority: Double, tag: String) {
        for (i in Photos.indices) {
            if (Photos[i].tag == tag) {
                Photos[i].rating = priority
            }
        }
        var sortedList = Photos.sortedWith(compareByDescending({ it.rating }))
        for (i in sortedList.indices) {
            Photos[i] = sortedList[i]
        }
        prepareView()
    }

    fun createPhotosArray() {
        Photos.add(Photo(R.drawable.got1, 0.0, R.string.got1.toString(), "got1"))
        Photos.add(Photo(R.drawable.got2, 0.0, R.string.got2.toString(), "got2"))
        Photos.add(Photo(R.drawable.got3, 0.0, R.string.got3.toString(), "got3"))
        Photos.add(Photo(R.drawable.got4, 0.0, R.string.got4.toString(), "got4"))
        Photos.add(Photo(R.drawable.got5, 0.0, R.string.got5.toString(), "got5"))
        Photos.add(Photo(R.drawable.got6, 0.0, R.string.got6.toString(), "got6"))
        Photos.add(Photo(R.drawable.got7, 0.0, R.string.got7.toString(), "got7"))
        Photos.add(Photo(R.drawable.got8, 0.0, R.string.got8.toString(), "got8"))
        Photos.add(Photo(R.drawable.lotr1, 0.0, R.string.lotr1.toString(), "lord1"))
        Photos.add(Photo(R.drawable.lotr2, 0.0, R.string.lotr2.toString(), "lotr2"))
        Photos.add(Photo(R.drawable.lotr3, 0.0, R.string.lotr3.toString(), "lotr3"))
        Photos.add(Photo(R.drawable.lotr4, 0.0, R.string.lotr4.toString(), "lotr4"))
        Photos.add(Photo(R.drawable.lotr5, 0.0, R.string.lotr5.toString(), "lotr5"))
        Photos.add(Photo(R.drawable.lotr6, 0.0, R.string.lotr6.toString(), "lotr6"))
        Photos.add(Photo(R.drawable.potter1, 0.0, R.string.potter1.toString(), "potter1"))
        Photos.add(Photo(R.drawable.potter2, 0.0, R.string.potter2.toString(), "potter2"))
        Photos.add(Photo(R.drawable.potter3, 0.0, R.string.potter3.toString(), "potter3"))
        Photos.add(Photo(R.drawable.potter4, 0.0, R.string.potter4.toString(), "potter4"))
        Photos.add(Photo(R.drawable.potter5, 0.0, R.string.potter5.toString(), "potter5"))
        Photos.add(Photo(R.drawable.potter6, 0.0, R.string.potter6.toString(), "potter6"))
    }

    fun createImageViewsArray() {
        ImageViewsArray.add(imageView)
        ImageViewsArray.add(imageView2)
        ImageViewsArray.add(imageView3)
        ImageViewsArray.add(imageView4)
        ImageViewsArray.add(imageView5)
        ImageViewsArray.add(imageView6)
        ImageViewsArray.add(imageView7)
        ImageViewsArray.add(imageView8)
        ImageViewsArray.add(imageView9)
        ImageViewsArray.add(imageView10)
        ImageViewsArray.add(imageView12)
        ImageViewsArray.add(imageView13)
        ImageViewsArray.add(imageView14)
        ImageViewsArray.add(imageView15)
        ImageViewsArray.add(imageView16)
        ImageViewsArray.add(imageView17)
        ImageViewsArray.add(imageView18)
        ImageViewsArray.add(imageView19)
        ImageViewsArray.add(imageView20)
        ImageViewsArray.add(imageView21)
    }

    fun prepareView() {
        for (i in ImageViewsArray.indices) {
            ImageViewsArray[i].setImageResource(Photos[i].name)
            ImageViewsArray[i].setTag(Photos[i].tag)
        }
    }

    fun getRating(tag: String): Double {
        for (i in Photos.indices) {
            if (Photos[i].tag == tag) {
                return Photos[i].rating
            }
        }
        return 0.0
    }

    fun getDescription(tag: String):String{
        for (i in Photos.indices) {
            if (Photos[i].tag == tag) {
                return Photos[i].descrption
            }
        }
        return "There is no description for this photo"
    }

    fun onClick(view: View) {
        println(Photos)
        println(view)
        println(view.tag)
        val myimageView = view as ImageView
        val image = (myimageView.drawable as BitmapDrawable).bitmap
        val myimageTag = view.tag.toString()

        val s = getDescription(myimageTag)

        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        val myCurrentRating = getRating(myimageTag)
        var myintent = Intent(activity, Main2Activity::class.java)
        myintent.putExtra("data", s)
        myintent.putExtra("imageName", byteArray)
        myintent.putExtra("tag", myimageTag)
        myintent.putExtra("rating", myCurrentRating)

        startActivityForResult(myintent, 222)
    }

}
