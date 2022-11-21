package org.example.filters;

import org.example.filters.IFilterPeople;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class ByAgeToFilter implements IFilterPeople {
    SearchParameters searchParameters;
    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return searchParameters.getAgeTo() != 0;
    }

    @Override
    public List<Person> filter(List<Person> list) {
        return list.stream().filter(person -> person.getAge() <= searchParameters.getAgeTo()).toList();
    }
}
