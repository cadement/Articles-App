package com.sharecare.category.ui;

import com.sharecare.category.container.CategoryConnector;
import com.vaadin.data.Container;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.workbench.tree.TreePresenter;
import info.magnolia.ui.workbench.tree.TreeView;

public class CategoryTreePresenter extends TreePresenter {

    public CategoryTreePresenter(TreeView view, ComponentProvider componentProvider) {
        super(view, componentProvider);
    }

    @Override
    protected Container initializeContainer() {
        return ((CategoryConnector) this.contentConnector).getCategoryContainer();
    }
}
