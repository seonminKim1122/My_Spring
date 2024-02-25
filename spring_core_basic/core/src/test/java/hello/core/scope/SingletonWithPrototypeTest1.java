package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean1.addCount();
        prototypeBean2.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void SingletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int result1 = clientBean1.logic();
        assertThat(result1).isEqualTo(1);
        
        // 싱글톤이랑 프로토타입을 같이 썼더니 그냥 둘 다 싱글톤 쓰는 것과 다를 게 없이 동작
        // why? 싱글톤 빈에서 참조하는 프로토타입 빈은 싱글톤 빈의 생성시점에 이미 주입이 끝났기 때문에 프로토타입 빈을 다시 요청할 일이 없음
        int result2 = clientBean2.logic();
        assertThat(result2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {

        private final PrototypeBean prototypeBean;

//        @Autowired - 생략 가능 + 아예 생성자도 @RequiredArgsConstructor 로 대체 가능
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy // 프로토타입 빈이라 호출될 일 없음
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
        
        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

    }
}
/*
싱글톤 빈이 프로토타입 빈에 의존하고 있으면?
-> 의존관계 주입 시점에 프로토타입 빈이 생성되고 싱글톤 빈에 주입 후 싱글톤 빈도 생성 완료
-> 이렇게 되면 싱글톤빈에서 프로토타입 빈을 이용한 작업을 여러 번 요청해도 동일한 프로토타입을 사용하게 되므로
결국 싱글톤과 다를 게 없어진다.??
 */