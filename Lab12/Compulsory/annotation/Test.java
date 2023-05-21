package annotation;

import java.lang.annotation.*;

/**
 *
 * @author Vlad Adriana
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test { }
