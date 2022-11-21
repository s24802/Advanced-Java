package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SimpleClass implements IClassFacade {
    Class<?> clazz;

    public SimpleClass(Class<Subject> clazz) {
        this.clazz = clazz;
    }


    @Override
    public List<IMethodFacade> getPublicDeclaredMethods() {
        List<IMethodFacade> result = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getModifiers() == 1) {
                result.add(new SimpleMethod(method));
            }
        }
        return result;
    }

    @Override
    public List<IMethodFacade> getPublicGetters() {
        List<IMethodFacade> result = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            IMethodFacade facade = new SimpleMethod(method);
            if (facade.isGetter()) {
                result.add(facade);

            }
        }
        return result;
    }

    @Override
    public List<IMethodFacade> getPublicSetters() {
        List<IMethodFacade> result = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (new SimpleMethod(method).isSetter()) {
                result.add(new SimpleMethod(method));
            }
        }
        return result;
    }

    @Override
    public List<Field> getFieldsForPublicProperties() {
        List<Field> result = new ArrayList<>();
        List<IMethodFacade> getters = getPublicGetters();
        List<IMethodFacade> setters = getPublicSetters();
        for (IMethodFacade getter : getters) {
            for (IMethodFacade setter : setters) {
                if (getter.getFieldName().equals(setter.getFieldName())) {
                    try {
                        result.add(clazz.getDeclaredField(getter.getFieldName()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
