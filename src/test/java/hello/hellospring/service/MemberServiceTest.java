package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
public class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 전체회원(){


        List<Member> result=memberService.findMembers();


        assertThat(result).isNotNull();
    }

}
