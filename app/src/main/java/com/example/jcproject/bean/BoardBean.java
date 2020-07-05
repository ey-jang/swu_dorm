package com.example.jcproject.bean;

import android.net.Uri;

import java.io.Serializable;

public class BoardBean implements Serializable {
    public String id; // 게시글 고유 ID
    public String studentId; //게시글 소유자 학번
    public String title; //제목
    public String contents; //내용
    public String key; // 키값
    public String imgUri; //이미지가 업로드된 풀경로
    public String imgName;  //이미지 파일 이름

    public BoardBean(){
        //디폴트 생성자
    }
}
