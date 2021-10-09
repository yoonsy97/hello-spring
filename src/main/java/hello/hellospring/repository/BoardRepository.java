package hello.hellospring.repository;

import hello.hellospring.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);
    Optional<Board> findByUserId(Long id);
    Optional<Board> findByBno(Long bno);
    List<Board> findAll();
}
