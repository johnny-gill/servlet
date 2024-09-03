package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        System.out.println("AppInitV3SpringMvc.onStartup");

        /**
         * 스프링MVC가 서블릿 컨테이너 초기화 과정을 제공해준다.
         * 결국, 어플리케이션 초기화만 하면 된다!!!
         *
         * spring-web 라이브러리/META-INF/services/jakarta.servlet.ServletContainerInitializer
         * -> org.springframework.web.SpringServletContainerInitializer 가 등록되어 있음
         */

        // 1. 스프링 컨테이너 생성 및 스프링 설정 등록
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 2. 디스패처 서블릿 생성 및 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);
        
        // 3. 디스패처 서블릿을 서블릿 컨테이너에 등록
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcher);
        servlet.addMapping("/");
    }
}
