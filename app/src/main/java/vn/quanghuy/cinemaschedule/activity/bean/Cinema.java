package vn.quanghuy.cinemaschedule.activity.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cinema extends BaseObject {

    private String cinemaName;
    private String address;
    private String ggMap;
    private String phoneNumber;
    private int resource;
    private Context context;

    public Cinema(String cinemaName, String address, String ggMap, String phoneNumber, int resource, Context context) {
        this.cinemaName = cinemaName;
        this.address = address;
        this.ggMap = ggMap;
        this.phoneNumber = phoneNumber;
        this.resource = resource;
        this.context = context;
        setIcon(createBitmapFromUrl(resource));
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    //Create bitmap image from drawable source
    public Bitmap createBitmapFromUrl(int resource) {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                resource);
        return icon;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGgMap() {
        return ggMap;
    }

    public void setGgMap(String ggMap) {
        this.ggMap = ggMap;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaName='" + cinemaName + '\'' +
                ", address='" + address + '\'' +
                ", ggMap='" + ggMap + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
