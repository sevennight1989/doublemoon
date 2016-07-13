package lk.meterialdesign.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by uiprj on 6/29/16.
 */
public class ViewInjectUtils {

    public static void init(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);
    }

    private static void injectContentView(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, contentLayoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectViews(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            ViewInject viewInjectAnnotation = field.getAnnotation(ViewInject.class);
            if (viewInjectAnnotation != null) {
                int viewId = viewInjectAnnotation.value();
                if (viewId != -1) {
                    try {
                        Method method = clazz.getMethod("findViewById", int.class);
                        Object resView = method.invoke(activity, method);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                if (eventBaseAnnotation != null) {
                    String listenerSetter = eventBaseAnnotation.listenerSetter();
                    Class<?> listenerType = eventBaseAnnotation.listenerType();
                    String methodName = eventBaseAnnotation.methodName();
                    try {
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) aMethod.invoke(annotation, null);
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(listenerType
                        .getClassLoader(), new Class<?>[]{listenerType}, handler);
                        for (int viewid : viewIds) {
                            View view = activity.findViewById(viewid);
                            Method setEventListenerMethod = view.getClass().getMethod
                            (listenerSetter, listenerType);
                            setEventListenerMethod.invoke(listener,view);
                        }


                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

}
