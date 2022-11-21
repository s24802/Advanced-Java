package org.example;

import java.lang.reflect.Method;

public class SimpleMethod implements IMethodFacade{
    Method method;

    public SimpleMethod(Method method) {
        this.method = method;
    }

    @Override
    public boolean isPublic() {
        return method.getModifiers() == 1;
    }

    @Override
    public boolean paramsCountEquals(int i) {
        return method.getParameterCount() == i;
    }

    @Override
    public boolean startsWith(String start) {
        return method.getName().startsWith(start);
    }

    @Override
    public boolean isVoid() {
        return method.getReturnType().equals(Void.TYPE);
    }

    @Override
    public boolean isSetter() {
        return startsWith("set") && paramsCountEquals(1) && isVoid() && isPublic();
    }

    @Override
    public boolean isGetter() {
        return this.isPublic() && !this.isVoid() && (this.startsWith("get") || this.startsWith("is")) && this.paramsCountEquals(0);
    }

    @Override
    public String getFieldName() {
        if (isSetter()) {
            if(method.getParameterTypes()[0].equals(Boolean.TYPE)) {
                return "is" + method.getName().substring(3);
            } else {
                return method.getName().toLowerCase().substring(3);
            }
        } else if (isGetter()) {
            if(method.getReturnType().equals(Boolean.TYPE)) {
                return method.getName();
            } else {
                return method.getName().toLowerCase().substring(3);
            }
        }
        return null;
    }

    @Override
    public Method GetUnderlyingMethod() {
        return method;
    }
}
