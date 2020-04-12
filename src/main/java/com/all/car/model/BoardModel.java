package com.all.car.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardModel {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Timestamp regiDate;
    private Timestamp modiDate;
    private int userId;
}
