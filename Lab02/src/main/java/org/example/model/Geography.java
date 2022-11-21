package org.example.model;

import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class Geography implements IHaveHierarchicalStructure<Geography> {
    private int id;
    private String name;
    private String type;
    private String code;
    private Integer parentId;

    private Geography parent;

    private List<Geography> children;

    public Geography() {
    }

    @Override
    public List<Geography> getChildren() {
        return children;
    }

    @Override
    public Geography getParent() {
        return parent;
    }

    @Override
    public void setParent(Geography parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
