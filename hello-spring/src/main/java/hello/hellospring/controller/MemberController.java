package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



//멤버컨테이너는 스프링컨테이너 가 뜰때 생성된다.->생성자 호출
@Controller
public class MemberController {

    //스프링컨테이너에 생성자로 등록
    private final MemverService memverService;

    @Autowired  //멤버 서비스를 스프링 스프링 컨테이너에 있는 멤버서비스를 가져다가 연결 - 멤버서비스와 연결(디펜던시 인젝션)
    public MemberController(MemverService memverService) {
        this.memverService = memverService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member =new Member();
        member.setName(form.getName());

        System.out.println("member" + member.getName());

        memverService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memverService.findMembers();
        model.addAttribute("members",members); //모델이 담아서 넘긴다
        return "/members/memberList";
    }
}
