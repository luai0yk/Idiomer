package com.esh.eih;

public class IdiomsModel {
    int id;
    String idiom, arIdiom,
            definition, arDefinition,
            example, arExample;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public String getIdiom() {
        return idiom;
    }

    public void setArIdiom(String arIdiom) {
        this.arIdiom = arIdiom;
    }

    public String getArIdiom() {
        return arIdiom;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public void setArDefinition(String arDefinition) {
        this.arDefinition = arDefinition;
    }

    public String getArDefinition() {
        return arDefinition;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setArExample(String arExample) {
        this.arExample = arExample;
    }

    public String getArExample() {
        return arExample;
    }
}
