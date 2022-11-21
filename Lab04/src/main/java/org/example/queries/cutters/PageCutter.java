package org.example.queries.cutters;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public class PageCutter implements ICutToPage {
    @Override
    public List<Person> cut(Page page, List<Person> list) {
        return list.stream()
                .skip((page.getPageNumber() - 1) * page.getSize()).limit(page.getSize()).toList();
    }
}
