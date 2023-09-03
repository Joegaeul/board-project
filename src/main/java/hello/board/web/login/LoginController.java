package hello.board.web.login;

import hello.board.domain.login.LoginService;
import hello.board.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")
    public String home(@ModelAttribute("loginForm") LoginForm form){
        return "/home";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("loginForm") LoginForm form){
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            return "/";
        }

        return "redirect:/boards";
    }
}
