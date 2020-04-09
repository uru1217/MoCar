package com.all.car.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplyModel {
    private int replyId;
    private int boardId;

    private String replyContent;
    private String replyer;
    private Timestamp replyDate;
    private Timestamp replyUpdate;
}
