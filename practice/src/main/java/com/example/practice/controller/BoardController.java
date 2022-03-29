package com.example.practice.controller;

import com.example.practice.entity.Board;
import com.example.practice.repository.BoardRepository;
import com.example.practice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWrite(){
        return "BoardWrite";
    }

    @GetMapping("/board/boardWriteDo")
    public String boardWriteDO(Board board){

        boardService.write(board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList());
        return "BoardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model,Integer id){
        model.addAttribute("board",boardService.boardView(id));
        return "BoardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(Model model,@PathVariable("id") Integer id){

        model.addAttribute("board",boardService.boardView(id));
        return "boardModify";
    }

    @GetMapping("/board/update/{id}")
    public String boardModify(@PathVariable("id") Integer id,Board board){

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);
        return "redirect:/board/list";
    }
}



