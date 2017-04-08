package com.ixitrip.chandan.ixigotrip.background;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by chandan on 4/8/2017.
 */

public class RouteList  implements Parcelable {
    public ArrayList<String> arrayList;
    public ArrayList<ArrayList<String>> route;
    public String cityName;
    public String countryName;
    String description;
    String howToReach;
    public String cityId;
    public String xid;
    String keyImageUrl;
    String whyToVisit;
    String latitude;
    String longitude;
    String minimumPrice;
    String name;
    String url;
    public String stateName;
    String shortDescription;
    public String id;
    public String address;

    public RouteList(ArrayList<String> arrayList,ArrayList<ArrayList<String>> route)
    {
        super();
        this.route = route;
        this.arrayList = arrayList;
        this.countryName = countryName;
        this.cityId = cityId;
        this.xid = xid;
        this.id = id;
        this.stateName = stateName;
    }

    private RouteList(Parcel in) {
        super();
        cityName=in.readString();
        countryName = in.readString();
        cityId= in.readString();
        xid = in.readString();
        id= in.readString();
        stateName = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString( cityName);
        dest.writeString(countryName);
        dest.writeString(cityId);
        dest.writeString(xid);
        dest.writeString(id);
        dest.writeString(stateName);


    }
    public static final Parcelable.Creator<RouteList> CREATOR = new Parcelable.Creator<RouteList>() {
        @Override
        public RouteList createFromParcel(Parcel in) {
            return new RouteList(in);
        }

        @Override
        public RouteList[] newArray(int size) {
            return new RouteList[size];
        }
    };
}
