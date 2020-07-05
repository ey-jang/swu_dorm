package com.example.jcproject.bean;

import java.io.Serializable;

public class ToolBean implements Serializable {

    public String id;    //고유 아이디(기본키)
    public String studentId;    //학번
    public String title;   //제목
    public String contents; //내용
    public String category;    //처리 상태
    // public String key;  //키값

    public ToolBean() {

    }
}