package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 컴포넌트 스캔 기능을 사용하겠다
        excludeFilters = @ComponentScan.Filter(
                // 기존 AppConfig 가 스프링 컨테이너에 등록되면 설정 정보 충돌이 있을 수 있어 Configuration 은 배제
                type = FilterType.ANNOTATION, classes = Configuration.class
        )
)
public class AutoAppConfig {
}
