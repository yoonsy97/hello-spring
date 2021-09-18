package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hello.hellospring.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session=request.getSession(false);

        if(session==null){
            return "home";
        }

        Member loginMember=(Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        if(loginMember==null)
        {
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";

    }

}
