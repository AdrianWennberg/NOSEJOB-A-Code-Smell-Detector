package nosejob;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Showcasing the added functionality for assignment 6
        Inspector inspector = new Inspector();
        System.out.println(inspector.isSingleton(Kelvin.class));
    }
}
