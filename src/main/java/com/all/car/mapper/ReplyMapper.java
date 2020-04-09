package com.all.car.mapper;


import com.all.car.model.Criteria;
import com.all.car.model.ReplyModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {

    List<ReplyModel> list(@Param("cri") Criteria cri, @Param("boardId") int boardId);

    int insert(ReplyModel replyModel);

    int update(ReplyModel replyModel);

    int delete(int replyId);


}