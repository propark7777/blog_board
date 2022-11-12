package com.example.blog_board.controller;

import com.example.blog_board.domain.Board;
import com.example.blog_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/hello")
    public String Hello(){
        return "/board/hello";
    }

    @GetMapping("/test")
    public String test(Model model){
        int boardCount = boardService.boardCount();
        List<Board> boardList = boardService.boardList();

        model.addAttribute("cnt", boardCount);
        model.addAttribute("test", boardList);

        return "/board/hello";
    }

    @GetMapping
    public String main(Model model){
        List<Board> boards = boardService.boardList();
        model.addAttribute("boards", boards);

        return "/board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model){
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);

        return "/board/board";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title, @RequestParam String content,
                      @RequestParam String name, RedirectAttributes redirectAttributes){
        Board board = new Board(title,content,name);
        Long boardId = boardService.add(board);
        log.info("BoardController - add - boardId = {}", boardId);

        redirectAttributes.addAttribute("boardId", boardId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model){
        Board findBoard = boardService.findById(boardId);
        model.addAttribute("board", findBoard);

        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, @RequestParam String title,
                           @RequestParam String content, @RequestParam String name)
    {
        Board board = boardService.findById(boardId);
        board.setTitle(title);
        board.setContent(content);
        board.setName(name);
        boardService.update(board);

        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId){
        boardService.deleteById(boardId);
        return "redirect:/boards";
    }

    @GetMapping("/add")
    public String add(){
        return "/board/addForm";
    }


}
