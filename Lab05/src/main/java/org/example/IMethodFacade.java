package org.example;

import java.lang.reflect.Method;

public interface IMethodFacade {

    boolean isPublic();

    boolean paramsCountEquals(int i);

    boolean startsWith(String start);

    boolean isVoid();

    boolean isSetter();

    boolean isGetter();

    String getFieldName();

    Method GetUnderlyingMethod();
}
