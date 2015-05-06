package com.sharecare.category.ui;

import info.magnolia.ui.form.field.definition.LinkFieldDefinition;

public class CategorySelectorFieldDefinition extends LinkFieldDefinition {

    public CategorySelectorFieldDefinition() { //CategoryConnector categoryConnector) {
        this.setAppName("categories");
//        this.setIdentifierToPathConverter(new CategoryIdentifierToPathConverter(categoryConnector));
    }
}
