package pkg;

import java.util.Set;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import org.reflections.*;
import org.reflections.util.*;
import org.reflections.scanners.*;

public class JavaMain {

    public static void javamain() {
        System.out.println("------------- Java Main -----------------");
        Reflections reflections = new Reflections(new ConfigurationBuilder()
           .setUrls(ClasspathHelper.forPackage("pkg"))
           .setScanners(new MethodAnnotationsScanner())
        );

        Set<Method> methods =
            reflections.getMethodsAnnotatedWith(MyAnnotation.class);

        System.out.println(methods);

        for (Iterator<Method> it = methods.iterator(); it.hasNext(); ) {
            Method method = it.next();
            System.out.println("******");
            System.out.println(method);
            Annotation[] annotations = method.getDeclaredAnnotations();
            System.out.println(annotations.length);
            // System.out.println(annotations[0]);
        }

        System.out.println("------------------ End Java Main ------------------");
    }

}

