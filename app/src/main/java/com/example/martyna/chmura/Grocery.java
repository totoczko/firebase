package com.example.martyna.chmura;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martyna on 2018-01-11.
 */

public class Grocery implements Parcelable {

    public String id;
    public String name;
    public String price;
    public String quantity;

    public Grocery() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Grocery(String id, String name, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    protected Grocery(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        quantity = in.readString();
    }

    public static final Creator<Grocery> CREATOR = new Creator<Grocery>() {
        @Override
        public Grocery createFromParcel(Parcel in) {
            return new Grocery(in);
        }

        @Override
        public Grocery[] newArray(int size) {
            return new Grocery[size];
        }
    };

    @Override
    public String toString() {
        return this.name + ": ilość: " + this.price + ", cena: " + this.quantity + "zł";
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(quantity);
    }
}