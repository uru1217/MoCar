package com.all.car.service;

import com.all.car.mapper.BoardMapper;
import com.all.car.model.BoardModel;
import com.all.car.model.Criteria;
import com.all.car.model.PageModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;

//    @Autowired
//    public void setBoardMapper(BoardMapper mapper) {
//        this.mepper = mapper;
//    }

    @Override
    public List<BoardModel> boardList(Criteria cri) {
        return boardMapper.boardList(cri);
    }

    @Transactional
    @Override
    public int boardInsert(BoardModel boardModel) {
        return boardMapper.boardInsert(boardModel);
    }

    @Override
    public BoardModel boardRead(int boardId) {
        return boardMapper.boardRead(boardId);
    }

    @Override
    public int boardMod(BoardModel boardModel) {
        return boardMapper.boardMod(boardModel);
    }

    @Override
    public int boardDel(int boardId) {
        return boardMapper.boardDel(boardId);
    }

    @Override
    public int totalCnt(Criteria cri) {
        return boardMapper.totalCnt(cri);
    }
}
