package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 상황에 따라 구현체를 변경해야 하는 케이스(보통 인터페이스에 의존) 에는
        // 자바 코드로 직접등록하는 방법을 사용하면 리턴하는 구현체만 변경해주면 되므로
        // 컴포넌트 스캔을 이용하는 방법에 비해 훨씬 편리하다.
        // 컴포넌트 스캔을 쓰면 기존 구현체 클래스에서 @Repository 제거하고 새로운 구현체 클래스에 붙이는 등의 작업 필요
        return new MemoryMemberRepository();
    }
}
