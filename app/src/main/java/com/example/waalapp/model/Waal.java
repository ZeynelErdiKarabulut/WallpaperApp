package com.example.waalapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Waal implements Parcelable {
    @SerializedName("id")
    public int id;

    @SerializedName("imagePath")
    public String imagePath;

    @SerializedName("category")
    public String category;

    protected Waal(Parcel in) {
        id = in.readInt();
        imagePath = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagePath);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Waal> CREATOR = new Creator<Waal>() {
        @Override
        public Waal createFromParcel(Parcel in) {
            return new Waal(in);
        }

        @Override
        public Waal[] newArray(int size) {
            return new Waal[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
