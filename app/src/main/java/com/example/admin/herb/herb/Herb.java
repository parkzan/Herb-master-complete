package com.example.admin.herb.herb;

/**
 * Created by ParkZan on 12/9/2016.
 */

public class Herb {
    public  final  String THname;
    public  final  String ENname;
    public  final  String SCname;
    public  final  String family;
    public  final  String Picture;

    public Herb(String THname, String picture,String ENname,String SCname,String family) {
        this.THname = THname;
        this.Picture = picture;
        this.SCname = SCname;
        this.ENname = ENname;
        this.family = family;
    }
}
