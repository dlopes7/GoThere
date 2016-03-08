package com.gothere.gothere.business;

/**
 * Created by david.lopes on 08/03/2016.
 */

public class BusinessActivity {
    String name;
    String place;
    int photoId;

    public BusinessActivity(String name, String place, int photoId){
        this.name = name;
        this.place = place;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
