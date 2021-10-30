package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);
    List<Board> findByUserId(Member writer);
    Optional<Board> findByBno(Long bno);

    List<Board> findAll();
  //  Page<Board> findAll(Pageable pageable);
}
