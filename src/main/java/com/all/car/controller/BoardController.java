package com.all.car.controller;

import com.all.car.model.BoardModel;
import com.all.car.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    //동록
    @RequestMapping(value="/write", method = RequestMethod.GET)
    public String write() {
        return "board/write";
    }

    //등록처리
    @RequestMapping(value="/write", method = RequestMethod.POST)
    public String write(BoardModel boardModel, RedirectAttributes redirectAttributes) {
        System.out.println(boardModel);
        boardService.boardInsert(boardModel);
        redirectAttributes.addFlashAttribute("msg","regSuccess");
        return "redirect:/board/list";
    }

    //목록
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model) {
        System.out.println(boardService.boardList());
        model.addAttribute("board", boardService.boardList());
        return "/board/list";
    }

    //조회
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam("boardId") int boardId, Model model) {
        model.addAttribute("board", boardService.boardRead(boardId));
        return "/board/get";
    }

    //수정 페이지이동
    @RequestMapping(value = "/modify/{boardId}", method = RequestMethod.GET)
    public String modify(@PathVariable int boardId, Model model) {
        model.addAttribute("board",boardService.boardRead(boardId));
        return "/board/modify";
    }

    //수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(BoardModel boardModel, RedirectAttributes redirectAttributes) {
        boardService.boardMod(boardModel);
        redirectAttributes.addFlashAttribute("msg", " regSuccess");
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public String remove(@RequestParam("boardId") int boardId, RedirectAttributes redirectAttributes) {
        boardService.boardDel(boardId);
        redirectAttributes.addFlashAttribute("msg","delSuccess");
        return "redirect:/board/list";
    }

}
