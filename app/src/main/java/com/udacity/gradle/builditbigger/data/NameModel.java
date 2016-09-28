package com.udacity.gradle.builditbigger.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public final class NameModel implements Parcelable {

    public final long id;
    public final String name;

    public NameModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected NameModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NameModel> CREATOR = new Creator<NameModel>() {
        @Override
        public NameModel createFromParcel(Parcel in) {
            return new NameModel(in);
        }

        @Override
        public NameModel[] newArray(int size) {
            return new NameModel[size];
        }
    };

    public static String[] namesToStringArray(List<NameModel> models) {

        final String[] namesArray = new String[models.size()];

        for(int i = 0; i < models.size(); i++) {
            namesArray[i] = models.get(i).name;
        }

        return namesArray;

    }

}
