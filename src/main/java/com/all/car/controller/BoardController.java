package com.all.car.controller;

import com.all.car.model.BoardModel;
import com.all.car.model.Criteria;
import com.all.car.model.PageModel;
import com.all.car.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "board/write";
    }

    //등록처리
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(BoardModel boardModel, RedirectAttributes redirectAttributes) {
        boardService.boardInsert(boardModel);
        redirectAttributes.addFlashAttribute("msg", "regSuccess");
        return "redirect:/board/list";
    }

    //목록
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Criteria cri) {
//        System.out.println(boardService.boardList());
        model.addAttribute("board", boardService.boardList(cri));
        int total = boardService.totalCnt(cri);
        System.out.println(new PageModel(cri, total));
        model.addAttribute("pageMaker", new PageModel(cri, total));
        return "/board/list";
    }

    //조회
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam("boardId") int boardId, Model model, @ModelAttribute("cri") Criteria cri) {
        System.out.println(boardService.boardRead(boardId));
        model.addAttribute("board", boardService.boardRead(boardId));
        return "/board/get";
    }

    //수정 페이지이동
    //                        <a href="/board/modify"><button type="button">수정</button></a>
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(@RequestParam("boardId") int boardId, Model model, @ModelAttribute("cri") Criteria cri) {
        model.addAttribute("board", boardService.boardRead(boardId));
        return "/board/modify";
    }

    //수정 처리
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(BoardModel boardModel, RedirectAttributes redirectAttributes, @ModelAttribute("cri") Criteria cri) {
//        System.out.println(boardModel);
//        boardService.boardMod(boardModel);
//        redirectAttributes.addFlashAttribute("msg", " regSuccess");
        if (boardService.boardMod(boardModel) == 1) {
            redirectAttributes.addFlashAttribute("result", "success");
        }
        redirectAttributes.addAttribute("pageNum", cri.getPageNum());
        redirectAttributes.addAttribute("amount", cri.getAmount());
        redirectAttributes.addAttribute("type", cri.getType());
        redirectAttributes.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("boardId") int boardId, RedirectAttributes redirectAttributes, @ModelAttribute("cri") Criteria cri) {

        if (boardService.boardDel(boardId) == 1) {
            redirectAttributes.addFlashAttribute("result", "success");
        }
        redirectAttributes.addAttribute("pageNum", cri.getPageNum());
        redirectAttributes.addAttribute("amount", cri.getAmount());
        redirectAttributes.addAttribute("type", cri.getType());
        redirectAttributes.addAttribute("keyword", cri.getKeyword());
        return "redirect:/board/list";
    }

}
