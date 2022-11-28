package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.customer.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	mappings.put("/", new ForwardController("/index.jsp"));
    	mappings.put("/main", new ForwardController("/main.jsp"));
        mappings.put("/customer/login/form", new ForwardController("/customer/loginForm.jsp"));
        mappings.put("/customer/login", new LoginController());
        mappings.put("/customer/logout", new LogoutController());
        mappings.put("/customer/delete", new DeleteCustomerController());
        mappings.put("/customer/update", new UpdateCustomerController());
        mappings.put("/customer/idCheck", new IdCheckController());
        mappings.put("/customer/myPage", new MyPageController());
        mappings.put("/customer/view", new ViewCustomerController());
        mappings.put("/customer/register", new RegisterCustomerController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}