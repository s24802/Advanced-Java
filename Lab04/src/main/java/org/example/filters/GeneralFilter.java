package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.function.Predicate;

public class GeneralFilter extends FilterBase {


    private Predicate<SearchParameters> canFilterPredicate;
    private DualPredicate filterPredicate;

    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, DualPredicate filterPredicate) {

        this.canFilterPredicate = canFilterPredicate;
        this.filterPredicate = filterPredicate;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParameters);
    }

    @Override
    protected Predicate<Person> getPersonPredicate() {
        return p->filterPredicate.check(searchParameters, p);
    }
}
