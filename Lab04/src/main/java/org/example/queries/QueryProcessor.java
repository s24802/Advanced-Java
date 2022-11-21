package org.example.queries;

import org.example.filters.IFilterPeople;
import org.example.model.Person;
import org.example.queries.calculations.ICalculate;
import org.example.queries.cutters.ICutToPage;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {

    List<ICalculate> calculates = new ArrayList<>();
    List<IFilterPeople> filters = new ArrayList<>();

    ICutToPage pageCutter;

    public Results GetResults(SearchParameters parameters, List<Person> data) {
       Results results = new Results();
       results.setItems(data);

        filterResults(parameters, results);

        calculateResluts(parameters, results);

        cutResults(parameters, results);

        return results;
    }

    private void cutResults(SearchParameters parameters, Results results) {
        results.setPages(results.getItems().size()% parameters.getPage().getSize());
        results.setCurrentPage(parameters.getPage().getPageNumber());
        results.setItems(pageCutter.cut(parameters.getPage(), results.getItems()));
    }

    private void calculateResluts(SearchParameters parameters, Results results) {
        parameters.getFunctions().forEach(funcParams->
                makeCalculations(results, funcParams)

    );
    }

    private void makeCalculations(Results results, FunctionsParameters funcParams) {
        calculates.stream()
               .filter(c->c.getFieldName().equals(funcParams.getFieldName()))
               .map(c->
                       new FunctionResult(funcParams.getFunction(), funcParams.getFieldName(), c.calculate(funcParams, results.getItems())))
               .forEach(fresult -> results.getFunctionResults().add(fresult));
    }

    private void filterResults(SearchParameters parameters, Results results) {
        filters.forEach(f -> f.setSearchParameters(parameters));
        filters.stream()
                .filter(f ->f.canFilter())
                .forEach(f-> results.setItems(f.filter(results.getItems())));
    }

    public QueryProcessor addFilter(IFilterPeople filter) {
        this.filters.add(filter);
        return this;
    }

    public QueryProcessor addCalculation(ICalculate calculator) {
        this.calculates.add(calculator);
        return this;
    }

    public void addPageCutter(ICutToPage pageCutter) {
        this.pageCutter = pageCutter;
    }
}
