package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class ByNameFilter implements IFilterPeople {
    SearchParameters searchParameters;
    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return this.searchParameters.getName() != null && !this.searchParameters.getName().isEmpty();
    }

    @Override
    public List<Person> filter(List<Person> list) {
        return list.stream().filter(person -> person.getName().equals(this.searchParameters.getName())).toList();
    }
}
