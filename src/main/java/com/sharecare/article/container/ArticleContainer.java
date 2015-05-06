package com.sharecare.article.container;

import com.google.inject.Inject;
import com.sharecare.article.model.Article;
import com.sharecare.article.model.ArticleRepository;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import info.magnolia.ui.workbench.container.Refreshable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.UUID;

public class ArticleContainer extends BeanItemContainer<Article> implements Refreshable {

    @Autowired private ArticleRepository articleRepository;

    @Inject
    public ArticleContainer(ServletContext servletContext) {
        super(Article.class);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);

        this.refresh();
    }

    @Override
    public void refresh() {
        this.removeAllItems();
        for (Article article : articleRepository.findAll()) {
            this.addBean(article);
        }
    }

    @Override
    public Item addItem() throws UnsupportedOperationException {
        Article article = new Article(UUID.randomUUID().toString(),
                                            null,
                                            null,
                                            null,
                                            null,
                                            null,
                                            "draft");
        articleRepository.save(article);

        return this.addBean(article);
    }
}
