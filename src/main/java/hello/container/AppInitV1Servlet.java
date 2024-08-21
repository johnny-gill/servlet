package hello.container;

import hello.servlet.HelloServlet2;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class AppInitV1Servlet implements AppInit {

    @Override
    public void onStartUp(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartUp");

        ServletRegistration.Dynamic helloServlet2 = servletContext.addServlet("helloServlet2", new HelloServlet2());
        helloServlet2.addMapping("/hello-servlet2");
    }
}
