package hello.hellospring.controller;

import hello.hellospring.domain.Board;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;


    @Autowired BoardController(BoardService boardService,MemberService memberService){
        this.boardService=boardService;
        this.memberService=memberService;

    }

    @GetMapping("/boardList")
    public String list(Model model){
        List<Board> boards=boardService.findBoards();
        List<Member> writers= memberService.findWriter(boards);

        model.addAttribute("boards",boards);
        model.addAttribute("writers",writers);
        return "boards/boardList";
    }



    @GetMapping("/view")
    public String viewBoard(Model model,Long bno){

        boardService.getBoard(bno).ifPresent(o->model.addAttribute("view",o));

        return "boards/view";
    }

    @GetMapping("/board/write")
    public String write(){


        return "boards/write";
    }

    @PostMapping("/board/write")
    public String writeboard(HttpServletRequest request, BoardForm boardForm){

        Long a= Long.valueOf(1);
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());

        HttpSession session= request.getSession(false);
        if(session==null){
            return "home";
        }
        else {
            Member member=(Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            board.setWriter(member.getId());

        }
        boardService.write(board);



        return "redirect:/";
    }



}
