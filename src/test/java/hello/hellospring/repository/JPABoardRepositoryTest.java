package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.*;
public class JPABoardRepositoryTest {


    @Autowired JpaBoardRepository jpaBoardRepository;

    @Test
    public void save() {
        Long a= Long.valueOf(1);
        Board board = new Board();
        board.setContent("happy");
        board.setWriter(a);
        Board savedBoard=jpaBoardRepository.save(board);
        assertThat(savedBoard).isNotNull();

    }
}
