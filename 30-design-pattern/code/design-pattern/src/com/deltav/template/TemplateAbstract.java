package com.deltav.template;

public abstract class TemplateAbstract {
    public final void task() {
        extract();
        transform();
        validate();
        store();
    }

    protected void extract() {
        System.out.println("[Default] Extracting data");
    }

    protected void transform() {
        System.out.println("[Default] Transforming data");
    }

    protected void validate() {
        System.out.println("[Default] Validating data");
    }

    protected void store() {
        System.out.println("[Default] Storing data");
    }
}
