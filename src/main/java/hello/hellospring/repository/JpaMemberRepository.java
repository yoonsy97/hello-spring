package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em=em;
    }

    @Override
    public Member save(Member member) {

        em.persist(member); //???
        return member;
    }


    @Override
    public Optional<Member> findById(Long id) {
        Member member=em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String id){
        List<Member> result = em.createQuery("select m from Member m where m.userid=: userid", Member.class)
                .setParameter("userid", id)
                .getResultList();
        return result.stream().findAny();




    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public List<Board> findWrittenBoard(Member writer) {
        return em.createQuery("select b from Board b where b.writer=writer").getResultList();
    }


}
