package com.all.car.mapper;

import com.all.car.model.BoardModel;

import java.util.List;

public interface BoardMapper {
    List<BoardModel> boardList();

    int boardInsert(BoardModel boardModel);

    BoardModel boardRead(int boardId);

    int boardMod(BoardModel boardModel);

    int boardDel(int boardId);
}
