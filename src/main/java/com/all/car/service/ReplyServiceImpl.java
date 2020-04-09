package com.all.car.service;

import com.all.car.mapper.ReplyMapper;
import com.all.car.model.Criteria;
import com.all.car.model.ReplyModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private ReplyMapper replyMapper;

    @Override
    public List<ReplyModel> list(Criteria cri, int boardId) {
        return replyMapper.list(cri, boardId);
    }

    @Override
    public int insert(ReplyModel replyModel) {
        return replyMapper.insert(replyModel);
    }

    @Override
    public int update(ReplyModel replyModel) {
        return replyMapper.update(replyModel);
    }

    @Override
    public int delete(int replyId) {
        return replyMapper.delete(replyId);
    }
}
