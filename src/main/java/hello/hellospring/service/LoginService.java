package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import hello.hellospring.domain.Member;

import java.util.Optional;

@Service
public class LoginService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public LoginService (MemberRepository memberRepository,BCryptPasswordEncoder passwordEncoder){ //외부에서 넣어주도록
        this.memberRepository=memberRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public Member login(String loginId, String password){

        Optional<Member> result=memberRepository.findByUserId(loginId);

        if(result==null||!passwordEncoder.matches(password,result.map(Member::getUserpassword).orElse(""))) return null;
        else {
                return result.orElse(null);

        }
        //return memberRepository.findByUserId(loginId).filter(m->m.getUserpassword().equals(password)).orElse(null);

    }

}
