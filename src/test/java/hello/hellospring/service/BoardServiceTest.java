package hello.hellospring.service;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardServiceTest {

    @Autowired BoardService boardService;

    @Test
    public void 글쓰기(){
        Board board=new Board();
        board.setContent("happy");

        Long boardId=boardService.write(board);

        assertThat(boardId).isNotNull();

    }


}
