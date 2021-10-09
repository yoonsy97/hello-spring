package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;

    public JpaBoardRepository(EntityManager em){
        this.em=em;
    }

    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Optional<Board> findByUserId(Long userid) {
        List<Board> result = em.createQuery("select m from Board m where m.writer=: userid", Board.class)
                .setParameter("userid", userid)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Board> findByBno(Long bno) {
        List<Board> result=em.createQuery("select b from Board b where b.bno=:bno", Board.class)
                .setParameter("bno",bno)
                .getResultList();
        return result.stream().findAny();

        //Board board=em.find(Board.class,bno);
        //return Optional.ofNullable(board);
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }
}
