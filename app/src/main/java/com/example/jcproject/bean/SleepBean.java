package com.example.jcproject.bean;

import java.io.Serializable;


public class SleepBean implements Serializable {

    public String id;    //고유 아이디(기본키)
    public String hak;  //신청자 학번
    public String room; //ㅣ신청자 호실
    public String start;   //외박날
    public String name; //보호자 이름
    public String num;  //보호자 번호
    public String place;    //외박 장소
    public String today;    //현재 날짜


    public SleepBean() {

    }
}
