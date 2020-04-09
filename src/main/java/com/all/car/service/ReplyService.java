package com.all.car.service;

import com.all.car.model.Criteria;
import com.all.car.model.ReplyModel;

import java.util.List;

public interface ReplyService {
    List<ReplyModel> list(Criteria cri,int boardId);

    int insert(ReplyModel replyModel);

    int update(ReplyModel replyModel);

    int delete(int replyId);
}
