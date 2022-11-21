package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface IClassFacade {
    List<IMethodFacade> getPublicDeclaredMethods();

    List<IMethodFacade> getPublicGetters();
    
    List<IMethodFacade> getPublicSetters();

    List<Field> getFieldsForPublicProperties();
}
