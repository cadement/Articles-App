package com.sharecare.category.ui;

import com.google.inject.Inject;
import com.sharecare.category.container.CategoryConnector;
import com.vaadin.data.Container;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.workbench.list.ListPresenter;
import info.magnolia.ui.workbench.list.ListView;

public class CategoryListPresenter extends ListPresenter {

    @Inject
    public CategoryListPresenter(ListView view, ComponentProvider componentProvider) {
        super(view, componentProvider);
    }

    @Override
    protected Container initializeContainer() {
        return ((CategoryConnector) this.contentConnector).getCategoryContainer();
    }
}
