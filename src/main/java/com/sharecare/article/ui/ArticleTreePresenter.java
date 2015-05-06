package com.sharecare.article.ui;

import com.sharecare.article.container.ArticleConnector;
import com.vaadin.data.Container;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.workbench.tree.TreePresenter;
import info.magnolia.ui.workbench.tree.TreeView;

public class ArticleTreePresenter extends TreePresenter {

    public ArticleTreePresenter(TreeView view, ComponentProvider componentProvider) {
        super(view, componentProvider);
    }

    @Override
    protected Container initializeContainer() {
        return ((ArticleConnector) this.contentConnector).getArticleContainer();
    }
}
