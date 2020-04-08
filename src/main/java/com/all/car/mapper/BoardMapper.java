package com.all.car.mapper;

import com.all.car.model.BoardModel;
import com.all.car.model.Criteria;
import com.all.car.model.PageModel;

import java.util.List;

public interface BoardMapper {
    List<BoardModel> boardList(Criteria cri);

    int boardInsert(BoardModel boardModel);

    BoardModel boardRead(int boardId);

    int boardMod(BoardModel boardModel);

    int boardDel(int boardId);

    int totalCnt(Criteria cri);
}
