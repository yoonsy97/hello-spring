package hello.hellospring.controller;

import hello.hellospring.domain.Board;
import hello.hellospring.domain.Reply;
import hello.hellospring.service.BoardService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ReplyService replyService;

    private static final Logger logger= LoggerFactory.getLogger(BoardController.class);

    @Autowired BoardController(BoardService boardService, MemberService memberService, ReplyService replyService){
        this.boardService=boardService;
        this.memberService=memberService;
        this.replyService=replyService;
    }

    @GetMapping("/boardList")
    public String list(Model model, Pageable pageable){



        List<Board> boards=boardService.findBoards();
        List<Member> writers= memberService.findWriter(boards);
        for(Board i:boards){
            logger.info("userid"+i.getWriter().getUserid());


        }



        model.addAttribute("boards",boards);
        model.addAttribute("writers",writers);
        return "boards/boardList";
    }



    @GetMapping("/view")
    public String viewBoard(Model model,Long bno){

        boardService.getBoard(bno).ifPresent(board-> {
                    model.addAttribute("reply",replyService.findRepliesByBno(board));
                    model.addAttribute("view",board); }
        );


        return "boards/view";
    }

    @GetMapping("/board/write")
    public String write(){


        return "boards/write";
    }

    @PostMapping("/board/write")
    public String writeboard(HttpServletRequest request, BoardForm boardForm){

        Long a= Long.valueOf(1);


        HttpSession session= request.getSession(false);
        if(session==null){
            return "home";
        }
        else {
            Member member=(Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            Board board=Board.builder()
                    .title(boardForm.getTitle())
                    .content(boardForm.getContent())
                    .writer(member)
                    .build();

            boardService.write(board);

        }

        return "redirect:/";


    }



}
