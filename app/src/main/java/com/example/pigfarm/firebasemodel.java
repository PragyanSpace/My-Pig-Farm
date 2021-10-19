package com.example.pigfarm;

public class firebasemodel {
    private String Age, Place, Origin, Dob, Tag;

    public firebasemodel() {
    }

    public firebasemodel(String Age, String Place, String Origin, String Dob, String Tag) {
        this.Age = Age;
        this.Place = Place;
        this.Origin = Origin;
        this.Dob = Dob;
        this.Tag = Tag;
    }

    public  String getAge() { return Age; }
    public void setAge(String age) {
        this.Age = Age;
    }

    public  String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        this.Place = Place;
    }

    public  String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        this.Origin = Origin;
    }

    public  String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        this.Dob = Dob;
    }

    public  String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        this.Tag = Tag;
    }
}

