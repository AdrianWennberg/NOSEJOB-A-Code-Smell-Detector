package nosejob;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Inspector {

    // All methods in this class use the same logic. That is, they fetch data
    // from the provided host using Reflection, convert it to a stream and filter it based on
    // the appropriate predicate. The size of the resulting list will be the correct
    // return value.

    public int getNumPrimitiveMethods(Class host) {
        ArrayList<Method> methods = new ArrayList<>();
        try {
            methods = Arrays.stream(host.getDeclaredMethods())
                    .parallel()
                    .filter(method ->
                            method.getReturnType().isPrimitive()
                    ).collect(Collectors.toCollection(ArrayList::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods.size();
    }

    public int getNumPrimitiveFields(Class host) {
        ArrayList<Field> primitives = new ArrayList<>();
        try {
            primitives = Arrays.stream(host.getDeclaredFields())
                    .parallel()
                    .filter(field ->
                            field.getType().isPrimitive()
                    ).collect(Collectors.toCollection(ArrayList::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return primitives.size();
    }

    public int getNumPrimitiveParameters(Method host) {
        ArrayList<Class<?>> primitiveParams = new ArrayList<>();
        try {
            primitiveParams = Arrays.stream(host.getParameterTypes())
                    .parallel()
                    .filter(Class::isPrimitive
                    ).collect(Collectors.toCollection(ArrayList::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return primitiveParams.size();
    }


    public int getNumPrivateMethod(Class host) {
        ArrayList<Method> methods = new ArrayList<>();
        try {
            methods = Arrays.stream(host.getDeclaredMethods())
                    .parallel()
                    .filter(method ->
                            Modifier.isPrivate(method.getModifiers())
                    ).collect(Collectors.toCollection(ArrayList::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods.size();
    }

    public int getNumPublicMethod(Class host) {
        ArrayList<Method> methods = new ArrayList<>();
        try {
            methods = Arrays.stream(host.getDeclaredMethods())
                    .parallel()
                    .filter(method ->
                            Modifier.isPublic(method.getModifiers())
                    ).collect(Collectors.toCollection(ArrayList::new));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return methods.size();
    }

    /**
     * A singleton class should only have a private constructor(s) and at least one static method
     * for getting instances. These are the two checks performed here
     *
     * @param host
     * @return true iff the class passes the described tests, false otherwise
     */
    public boolean isSingleton(Class<?> host) {
        try {
            ArrayList<Constructor> constructors;
            constructors = Arrays.stream(host.getConstructors())
                    .parallel()
                    .filter(constructor ->
                            Modifier.isPublic(constructor.getModifiers())
                                    || Modifier.isProtected(constructor.getModifiers())
                    ).collect(Collectors.toCollection(ArrayList::new));

            if (!constructors.isEmpty()) {
                return false;
            }

            ArrayList<Method> methods;
            methods = Arrays.stream(host.getDeclaredMethods())
                    .parallel()
                    .filter(method ->
                            Modifier.isStatic(method.getModifiers())
                    ).collect(Collectors.toCollection(ArrayList::new));

            if (methods.isEmpty()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
