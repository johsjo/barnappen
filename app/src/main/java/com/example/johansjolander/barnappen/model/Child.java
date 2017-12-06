package com.example.johansjolander.barnappen.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Child implements Parcelable {
    private final long id;
    private final String name;
    private final int days;

    public Child(long id, String name, int days) {
        this.id = id;
        this.name = name;
        this.days = days;
    }

    protected Child(Parcel in) {
        id = in.readLong();
        name = in.readString();
        days = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(days);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Child> CREATOR = new Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel in) {
            return new Child(in);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };

    public long getId() {return id;}
    public String getName() {return name;}
    public int getDays() {return days;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id == child.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days=" + days +
                '}';
    }


}
