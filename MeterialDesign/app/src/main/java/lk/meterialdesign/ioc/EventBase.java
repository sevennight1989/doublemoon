package lk.meterialdesign.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by uiprj on 6/29/16.
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface EventBase {
    Class<?> listenerType();

    String listenerSetter();

    String methodName();
}
