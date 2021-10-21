package com.example.pigfarm;

public class firebasemodel {
    private String  Place, Origin, Dob, Tag, id;

    public firebasemodel() {
    }

    public firebasemodel( String Place, String Origin, String Dob, String Tag, String id) {
        //this.Age = Age;
        this.Place = Place;
        this.Origin = Origin;
        this.Dob = Dob;
        this.Tag = Tag;
        this.id=id;
    }

//    public  String getAge() { return Age; }
//    public void setAge(String Age) {
//        this.Age = Age;
//    }

    public  String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
        this.Place = Place;
    }

    public  String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public  String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

    public  String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public  String getid() { return id; }
    public void setid(String id) {
        this.id = id;
    }

}

