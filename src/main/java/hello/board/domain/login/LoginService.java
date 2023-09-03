package hello.board.domain.login;

import hello.board.domain.member.Member;
import hello.board.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if(member.get().getPassword().equals(password))
            return member.get();
        else
            return null;
    }
}
