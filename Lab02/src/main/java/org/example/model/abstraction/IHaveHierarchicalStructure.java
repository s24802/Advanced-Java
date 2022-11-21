package org.example.model.abstraction;

import java.util.List;

public interface IHaveHierarchicalStructure<TItem>{
    List<TItem> getChildren();
    TItem getParent();
    void setParent(TItem parent);
    int getId();
    Integer getParentId();
}
