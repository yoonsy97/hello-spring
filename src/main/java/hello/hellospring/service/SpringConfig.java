package hello.hellospring.service;

import hello.hellospring.domain.Reply;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {


    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }


    @Bean
    public ReplyService replyService(){
        return new ReplyService(replyRepository());
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository(),passwordEncoder(),boardRepository(),replyRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);

    }



    @Bean
    public BoardRepository boardRepository(){ return new JpaBoardRepository(em);
    }


    @Bean
    public BoardService boardService(){return new BoardService(boardRepository());}

    @Bean
    public ReplyRepository replyRepository(){ return new JpaReplyRepository(em);}


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
