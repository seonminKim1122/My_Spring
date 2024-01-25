package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

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
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
