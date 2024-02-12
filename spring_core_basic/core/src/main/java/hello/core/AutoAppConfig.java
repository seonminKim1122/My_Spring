package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 컴포넌트 스캔 기능을 사용하겠다
        // 베이스 패키지를 지정하지 않으면 지금 클래스가 속한 패키지 포함 하위를 다 탐색
        basePackages = "hello.core", // 베이스 패키지의(포함) 하위 패키지만 컴포넌트 스캔 대상 -> 지정하지 않으면 모든 코드를 다 뒤져야해서 어플리케이션 띄우는데 느림
        excludeFilters = @ComponentScan.Filter(
                // 기존 AppConfig 가 스프링 컨테이너에 등록되면 설정 정보 충돌이 있을 수 있어 Configuration 은 배제
                type = FilterType.ANNOTATION, classes = Configuration.class
        )
)
public class AutoAppConfig {

        @Bean(name = "memoryMemberRepository") // 수동 빈이 자동 빈을 오버라이딩 하게 됨 -> 스프링부트에서는 수동 빈 vs 자동 빈도 Exception 터짐
        MemberRepository memberRepository() {
                return new MemoryMemberRepository();
        }
}
