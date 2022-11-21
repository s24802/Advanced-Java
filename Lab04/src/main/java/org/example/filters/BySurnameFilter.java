package org.example.filters;

import org.example.model.Person;

import org.example.queries.search.SearchParameters;

import java.util.function.Predicate;

public class BySurnameFilter extends FilterBase {

    @Override
    public boolean canFilter() {
        return searchParameters.getSurname() != null&&!searchParameters.getSurname().isEmpty();
    }

    @Override
    protected Predicate<Person> getPersonPredicate() {
        return person -> person.getSurname().equalsIgnoreCase(searchParameters.getSurname());
    }
}
