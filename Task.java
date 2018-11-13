package com.example.a1.timon.tasks_recycler;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id = 0;
    public String price = "";
    public String title = "";
    public String time = "";
    public String category;
    public String description = "";
    public String wayOfPaying = "";
    public int imageViewInt ;
    public Uri imageViewUri;


    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };



    protected Task(Parcel in) {
        price = in.readString();
        title = in.readString();
        time = in.readString();
        wayOfPaying = in.readString();
        imageViewInt = in.readInt();
        imageViewUri = in.readParcelable(Uri.class.getClassLoader());
    }


    public Task() {
    }

    public Task(String price, String title, String time, int imageViewInt) {
        this.price = price;
        this.title = title;
        this.time = time;
        this.wayOfPaying = wayOfPaying;
        this.imageViewInt = imageViewInt;
        this.imageViewUri = imageViewUri;
    }

    public static Creator<Task> getCREATOR() {
        return CREATOR;
    }

    public Task(String price, String title, String time, String wayOfPaying, int imageViewInt, Uri imageViewUri) {
        this.price = price;
        this.title = title;
        this.time = time;
        this.wayOfPaying = wayOfPaying;
        this.imageViewInt = imageViewInt;
        this.imageViewUri = imageViewUri;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWayOfPaying() {
        return wayOfPaying;
    }

    public void setWayOfPaying(String wayOfPaying) {
        this.wayOfPaying = wayOfPaying;
    }

    public int getImageViewInt() {
        return imageViewInt;
    }

    public void setImageViewInt(int imageViewInt) {
        this.imageViewInt = imageViewInt;
    }

    public Uri getImageViewUri() {
        return imageViewUri;
    }

    public void setImageViewUri(Uri imageViewUri) {
        this.imageViewUri = imageViewUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(title);
        dest.writeString(time);
        dest.writeString(wayOfPaying);
        dest.writeInt(imageViewInt);
        dest.writeParcelable(imageViewUri, flags);
    }
}
