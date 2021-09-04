package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService (MemberRepository memberRepository){ //외부에서 넣어주도록
        this.memberRepository=memberRepository;
    }

    /**
    * 회원가입
    */
    public Long join (Member member){
        //옵셔널 안에 감싸서 반환 ->널일경우도 있기 때문에


        validateDuplicatedMember(member);

        memberRepository.save(member);
      return member.getId();

    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByUserId(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
       return memberRepository.findAll();

    }

    public Optional<Member> findOne(String memberId){
        return memberRepository.findByUserId(memberId);
    }
}
