package com.all.car.controller;

import com.all.car.model.Criteria;
import com.all.car.model.ReplyModel;
import com.all.car.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping(value = "/replyList/{boardId}/{page}")
    public ResponseEntity<List<ReplyModel>> list(@PathVariable("boardId") int boardId, @PathVariable("page") int page) {
//        System.out.println(replyService.list());
        Criteria cri = new Criteria(page, 10);
        return new ResponseEntity<>(replyService.list(cri, boardId), HttpStatus.OK);
    }

    @PostMapping(value = "/replyInsert")
    public ResponseEntity<Integer> insert(ReplyModel replyModel) {
        return new ResponseEntity<>(replyService.insert(replyModel), HttpStatus.OK);
    }

    @PostMapping(value = "/replyUpdate")
    public ResponseEntity<Integer> update(ReplyModel replyModel) {
        return new ResponseEntity<>(replyService.update(replyModel), HttpStatus.OK);
    }

    @PostMapping(value = "/replyDelete/{replyId}")
    public ResponseEntity<Integer> delete(@PathVariable int replyId) {
        System.out.println(replyId);
        return new ResponseEntity<>(replyService.delete(replyId), HttpStatus.OK);
    }
}
