package hello.board.web.member;

import hello.board.domain.member.Member;
import hello.board.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute Member member){

        return "members/addMembersForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Member member){
        log.info("memberId={}, memberName={}, password={}", member.getLoginId(),
                member.getName(), member.getPassword());

        memberRepository.save(member);
        return "redirect:/";
    }
}
