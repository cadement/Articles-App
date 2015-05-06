package com.sharecare.category.ui;

import info.magnolia.ui.workbench.column.definition.PropertyColumnDefinition;

public class CategoryColumnDefinition extends PropertyColumnDefinition {

    public CategoryColumnDefinition() {
        this.setFormatterClass(CategoryColumnFormatter.class);
    }
}
