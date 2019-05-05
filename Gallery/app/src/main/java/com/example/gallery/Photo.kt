package com.example.gallery

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable

data class Photo(val name: Int, var rating: Double, val descrption: String, val tag: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(name)
        parcel.writeDouble(rating)
        parcel.writeString(descrption)
        parcel.writeString(tag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}
