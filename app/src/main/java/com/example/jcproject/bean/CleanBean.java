package com.example.jcproject.bean;

import java.io.Serializable;

public class CleanBean implements Serializable {

    public String id; // 게시글 고유 ID
    public String studentId; //게시글 소유자 학번
    public String title; //제목
    public String contents; //내용
    public String key; // 키값
    public String imgUri; //이미지가 업로드된 풀경로
    public String imgName;  //이미지 파일 이름

    public CleanBean(){
        //디폴트 생성자
    }

}
