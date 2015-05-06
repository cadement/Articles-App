package com.sharecare.article.ui;

import com.google.inject.Inject;
import com.sharecare.article.container.ArticleConnector;
import com.vaadin.data.Container;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.workbench.list.ListPresenter;
import info.magnolia.ui.workbench.list.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleListPresenter extends ListPresenter {

    private final Logger logger = LoggerFactory.getLogger(ArticleListPresenter.class);

    @Inject
    public ArticleListPresenter(ListView view, ComponentProvider componentProvider) {
        super(view, componentProvider);

        logger.info("(" + view + "," + componentProvider + ")");
    }

    @Override
    protected Container initializeContainer() {
        logger.info("()");

        Container result = ((ArticleConnector) this.contentConnector).getArticleContainer();

        logger.info("\t->" + result);

        return result;
    }
}
