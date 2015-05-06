package com.sharecare.category.ui;

import info.magnolia.ui.workbench.definition.ConfiguredContentPresenterDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryListPresenterDefinition extends ConfiguredContentPresenterDefinition {

    private final Logger logger = LoggerFactory.getLogger(CategoryListPresenterDefinition.class);

    public CategoryListPresenterDefinition() {
        logger.info("()");

        setImplementationClass(CategoryListPresenter.class);
        setViewType("listview");
        setActive(false);
        setIcon("icon-view-list");
    }
}
