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
        return boardMapper.boardCount();
    }

    @Override
    public List<Board> boardList() {
        return boardMapper.findAll();
    }

    @Override
    public Board findById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    @Override
    @Transactional
    public Long add(Board board) {
        boardMapper.save(board);
        return board.getBoardId();
    }

    @Override
    @Transactional
    public Long update(Board board) {
        boardMapper.update(board);
        return board.getBoardId();
    }

    @Override
    public void deleteById(Long boardId) {
        boardMapper.delete(boardId);
    }
}
