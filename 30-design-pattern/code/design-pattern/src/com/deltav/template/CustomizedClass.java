package com.deltav.template;

public class CustomizedClass extends TemplateAbstract {
    @Override
    protected void extract() {
        System.out.println("[Customized] Extracting data");
    }

    @Override
    protected void validate() {
        System.out.println("[Customized] Validating data");
    }
}
