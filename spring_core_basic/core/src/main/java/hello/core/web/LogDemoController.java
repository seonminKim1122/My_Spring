package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // @Scope("request") 이므로 빈이 생성되지 않은 상태라 의존 관계 주입이 불가능하여 Exception 발생!!
//    private final MyLogger myLogger;

    // Provider 를 통해 Dependency Lookup 을 할 수 있게 하여 문제 해결
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestUrl(requestUrl);

        // 왜 로그가 안 찍히지?
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
