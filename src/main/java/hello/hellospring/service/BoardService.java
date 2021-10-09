package hello.hellospring.service;

import hello.hellospring.domain.Board;
import hello.hellospring.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
public class BoardService {

    private final BoardRepository boardRepository;


    public BoardService(BoardRepository boardRepository){

        this.boardRepository=boardRepository;
    }

    /*
    *글쓰기
    **/

    public Long write(Board board){

        boardRepository.save(board);

        return board.getBno();
    }

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    public Optional<Board> getBoard(Long bno){

        return boardRepository.findByBno(bno);
    }
}
