package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 set = " + set);
        System.out.println("MyContainerInitV2 servletContext = " + servletContext);


        for (Class<?> appInitClass : set) {
            try {
                AppInit appInit = (AppInit) appInitClass
                        .getDeclaredConstructor()
                        .newInstance(); // new AppInitV1Servlet(); 과 동일
                appInit.onStartUp(servletContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
