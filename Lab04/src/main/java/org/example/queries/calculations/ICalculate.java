package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.search.FunctionsParameters;

import java.util.List;

public interface ICalculate {
    double calculate(FunctionsParameters functionsParameters, List<Person> list);

    String getFieldName();
}
