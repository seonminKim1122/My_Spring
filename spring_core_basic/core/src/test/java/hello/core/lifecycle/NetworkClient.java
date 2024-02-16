package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }
    
    // 인터페이스로 초기화, 소멸 콜백
    // 단점 : 스프링에 의존적. 그래서 지금은 거의 사용하지 않는 방법임

    @PostConstruct
    public void init() throws Exception {
        // 의존관계 주입이 끝나면 스프링이 이 메서드 자동으로 호출(초기화 콜백)
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        // 소멸 전 콜백(스프링이 내려가기 전에 모든 빈을 삭제하게 되는데 이 직전 시점에 호출)
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
