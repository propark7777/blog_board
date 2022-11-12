package com.example.blog_board.service;

import com.example.blog_board.domain.Board;
import com.example.blog_board.mapper.BoardMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    
    @Override
    public int boardCount() {
        return 0;
    }

    @Override
    public List<Board> boardList() {
        return null;
    }

    @Override
    public Board findById(Long boardId) {
        return null;
    }

    @Override
    public Long add(Board board) {
        return null;
    }

    @Override
    public Long update(Board board) {
        return null;
    }

    @Override
    public void deleteById(Long boardId) {

    }
}
