package com.hapiio.pojo.model;

import java.util.List;

public class Specification {
    private Long id;

    private String specName;

    private List<SpecificationOption> specificationOptionList;

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }

    public Specification(Long id, String specName, List<SpecificationOption> specificationOptionList) {
        this.id = id;
        this.specName = specName;
        this.specificationOptionList = specificationOptionList;
    }

    public Specification(Long id, String specName) {
        this.id = id;
        this.specName = specName;
    }

    public Specification() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
}