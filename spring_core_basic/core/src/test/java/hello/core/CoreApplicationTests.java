package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {AutoAppConfig.class}) // 통합 테스트 시 ApplicationContext 를 만들 때 쓸 Configuration 정보를 지정할 수 있다!
class CoreApplicationTests {

	@Test
	void contextLoads() { // @SpringBootTest 는 어플리케이션을 동작시키는데 이때 @ComponentScan 이 포함되어 있다. 여기엔 excludeFilter 가 적용되어 있지 않아 AutoConfig 와 AppConfig 가 만든 빈이 전부 다 스프링 컨테이너에 등록이 되서 테스트가 실패!!!
	}

}
