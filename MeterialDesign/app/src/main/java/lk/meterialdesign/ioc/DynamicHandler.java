package lk.meterialdesign.ioc;


import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by uiprj on 6/29/16.
 */
public class DynamicHandler implements InvocationHandler {

    private WeakReference<Object> handlerRef;

    private final HashMap<String,Method> methodMap = new HashMap<>(1);

    public DynamicHandler(Object handler){
        handlerRef = new WeakReference<>(handler);
    }

    public void addMethod(String name,Method method){
        methodMap.put(name,method);
    }

    public Object getHandler(){
        return handlerRef.get();
    }

    public void setHandler(Object handler){
        handlerRef = new WeakReference<>(handler);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object handler = handlerRef.get();
        if(handler != null){
            String methodName = method.getName();
            method = methodMap.get(methodName);
            if(method !=null){
                method.invoke(handler,args);
            }
        }


        return null;
    }
}
