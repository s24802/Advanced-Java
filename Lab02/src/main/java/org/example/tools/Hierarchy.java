package org.example.tools;

import org.example.model.Geography;
import org.example.model.abstraction.IHaveHierarchicalStructure;

public class Hierarchy <TItem extends IHaveHierarchicalStructure<TItem>>{

    public void setRootElement(TItem rootGeography) {

    }

    public TItem findElementById(int i) {

    }
}
