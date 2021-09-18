package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){this.loginService=loginService;}


    @GetMapping("/login")
    public String loginMember(Member member){

        return "members/loginForm";

    }

    @PostMapping("/login")
    public String login(MemberForm form, Model model, HttpServletRequest request){

        Member result =loginService.login(form.getUserId(),form.getUserpassword());

        if(result==null){
            model.addAttribute("msg","ID나 PW가 틀립니다");
            return "members/loginForm";

        }else{
            HttpSession session=request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER,result);
            return "redirect:/";
        }

    }


}
