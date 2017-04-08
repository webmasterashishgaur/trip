package com.ixitrip.chandan.ixigotrip.background;

import android.bluetooth.le.ScanRecord;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chandan on 4/8/2017.
 */

public class SearchList implements Parcelable {
   public String cityName;
    public String countryName;
    String description;
    String howToReach;
    String cityId;
    String xid;
    String keyImageUrl;
    String whyToVisit;
    String latitude;
    String longitude;
    String minimumPrice;
    String name;
    String url;
    String stateName;
    String shortDescription;
    String id;
    String address;

    public SearchList(String address,String cityName,String countryName,String cityId,
                        String xid,String id,String stateName)
    {
        super();
        this.address = address;
        this.cityName = cityName;
        this.countryName = countryName;
        this.cityId = cityId;
        this.xid = xid;
        this.id = id;
        this.stateName = stateName;
    }

    private SearchList(Parcel in) {
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
    public static final Parcelable.Creator<SearchList> CREATOR = new Parcelable.Creator<SearchList>() {
        @Override
        public SearchList createFromParcel(Parcel in) {
            return new SearchList(in);
        }

        @Override
        public SearchList[] newArray(int size) {
            return new SearchList[size];
        }
    };
}
