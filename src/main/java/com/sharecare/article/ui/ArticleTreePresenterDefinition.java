package com.sharecare.article.ui;

import info.magnolia.ui.workbench.tree.TreePresenterDefinition;

public class ArticleTreePresenterDefinition extends TreePresenterDefinition {

    public ArticleTreePresenterDefinition() {
        setImplementationClass(ArticleTreePresenter.class);
        setViewType("treeview");
        setActive(false);
        setIcon("icon-view-tree");
    }
}
