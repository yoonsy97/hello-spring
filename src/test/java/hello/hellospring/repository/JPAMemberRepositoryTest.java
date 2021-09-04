package hello.hellospring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import hello.hellospring.domain.Member;


import static org.assertj.core.api.Assertions.*;



public class JPAMemberRepositoryTest {

    @Autowired JpaMemberRepository repository;


    @Test
    public void save(){
        Member member=new Member();
        member.setUserid("test1");
        member.setUserpassword("1234");
        member.setLevel(1);

        repository.save(member);

        Member result=repository.findByUserId(member.getUserid()).get();
        assertThat(result).isEqualTo(member);

    }



}
