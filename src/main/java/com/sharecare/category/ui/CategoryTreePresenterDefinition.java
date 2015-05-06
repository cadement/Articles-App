package com.sharecare.category.ui;

import info.magnolia.ui.workbench.tree.TreePresenterDefinition;

public class CategoryTreePresenterDefinition extends TreePresenterDefinition {

    public CategoryTreePresenterDefinition() {
        setImplementationClass(CategoryTreePresenter.class);
        setViewType("treeview");
        setActive(false);
        setIcon("icon-view-tree");
    }
}
