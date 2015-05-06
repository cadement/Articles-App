package com.sharecare.article.container;

import com.google.inject.Inject;
import com.sharecare.article.model.Article;
import com.sharecare.article.model.ArticleRepository;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import info.magnolia.ui.vaadin.integration.contentconnector.SupportsCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class ArticleConnector implements SupportsCreation {

    @Autowired ArticleRepository articleRepository;

    private final ArticleContainer articleContainer;

    @Inject
    public ArticleConnector(ServletContext servletContext) {
        articleContainer = new ArticleContainer(servletContext);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public ArticleContainer getArticleContainer() {
        return articleContainer;
    }

    @Override
    public Object getNewItemId(Object parentId, Object typeDefinition) {
        return getItemId(articleContainer.addItem());
    }

    @Override
    public String getItemUrlFragment(Object itemId) {
        if (null != itemId) {
            return ((Article) itemId).getId();
        } else {
            return null;
        }
    }

    @Override
    public Object getItemIdByUrlFragment(String urlFragment) {
        if (null != urlFragment) {
            return articleRepository.findOne(urlFragment);
        } else {
            return null;
        }
    }

    @Override
    public Object getDefaultItemId() {
        return null;
    }

    @Override
    public Item getItem(Object itemId) {
        return articleContainer.getItem(itemId);
    }

    @Override
    public Object getItemId(Item item) {
        if (null != item) {
            return ((BeanItem<Article>) item).getBean();
        } else {
            return null;
        }
    }

    @Override
    public boolean canHandleItem(Object itemId) {
        if (null != itemId) {
            return articleContainer.containsId(itemId);
        } else {
            return false;
        }
    }
}
