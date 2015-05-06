package com.sharecare.article.ui;

import info.magnolia.ui.workbench.definition.ConfiguredContentPresenterDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleListPresenterDefinition extends ConfiguredContentPresenterDefinition {

    private final Logger logger = LoggerFactory.getLogger(ArticleListPresenterDefinition.class);

    public ArticleListPresenterDefinition() {
        logger.info("()");

        setImplementationClass(ArticleListPresenter.class);
        setViewType("listview");
        setActive(true);
        setIcon("icon-view-list");
    }
}
