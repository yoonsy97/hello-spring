package hello.hellospring.controller;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Member;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;

//스프링이 멤버컨트롤러를 생성해서 통에 넣어놓는다
@Controller
public class MemberController {
    //new를 사용하게 되면 다른 컨테이너들이 사용할때 계속 new를 하게됨
    //하나만 생성해서 공용으로 쓰면됨
    private final MemberService memberService;



    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService=memberService;

    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){


        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        Member member= Member.builder()
                .userid(form.getUserId())
                .userpassword(passwordEncoder.encode(form.getUserpassword()))
                .level(form.getUserlevel()).build();

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @RequestMapping(value = "/member/view",method= RequestMethod.GET)
    public String writtenBoards(HttpServletRequest request, Model model){

        HttpSession session =request.getSession(false);

        if(session ==null) return "home";

        Member loginMember=(Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);


        List<Board> writtenBoards= memberService.findWrittenBoards(loginMember);


        model.addAttribute("boards",writtenBoards);

        return "members/view";

    }

}
