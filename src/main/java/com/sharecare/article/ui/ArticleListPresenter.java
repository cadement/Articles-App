package com.sharecare.article.ui;

import com.google.inject.Inject;
import com.sharecare.article.container.ArticleConnector;
import com.vaadin.data.Container;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.workbench.list.ListPresenter;
import info.magnolia.ui.workbench.list.ListView;

public class ArticleListPresenter extends ListPresenter {

    @Inject
    public ArticleListPresenter(ListView view, ComponentProvider componentProvider) {
        super(view, componentProvider);
    }

    @Override
    protected Container initializeContainer() {
        return ((ArticleConnector) this.contentConnector).getArticleContainer();
    }
}
