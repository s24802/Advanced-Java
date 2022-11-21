package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.calculations.ICalculate;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneralCalculator implements ICalculate {
    private String fieldName;
    private Function<Person, Number> getNumber;

    public GeneralCalculator(String fieldName, Function<Person, Number> getNumber) {

        this.fieldName = fieldName;
        this.getNumber = getNumber;
    }

    @Override
    public double calculate(FunctionsParameters functionsParameters, List<Person> list) {

        var stats = list.stream().map(getNumber).collect(Collectors.summarizingDouble(n->n.doubleValue()));
//        switch (functionsParameters.getFunction()) {
//            case AVERAGE : return stats.getAverage();
//            case SUM : return stats.getSum();
//            case MAX : return stats.getMax();
//            case MIN : return stats.getMin();
//        }
        return functionsParameters.getFunction().calculate(stats);
    }
    public String getFieldName() {
        return fieldName;
    }
}
