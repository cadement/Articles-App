package com.sharecare.category.container;

import com.google.inject.Inject;
import com.sharecare.category.model.Category;
import com.sharecare.category.model.CategoryRepository;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import info.magnolia.ui.vaadin.integration.contentconnector.SupportsCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class CategoryConnector implements SupportsCreation {

    @Autowired CategoryRepository categoryRepository;

    private final CategoryContainer categoryContainer;

    @Inject
    public CategoryConnector(ServletContext servletContext) {
        categoryContainer = new CategoryContainer(servletContext);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public CategoryContainer getCategoryContainer() {
        return categoryContainer;
    }

    @Override
    public Object getNewItemId(Object parentId, Object typeDefinition) {
        return getItemId(categoryContainer.addItem());
    }

    @Override
    public String getItemUrlFragment(Object itemId) {
        if (null != itemId) {
            return ((Category) itemId).getId();
        } else {
            return null;
        }
    }

    @Override
    public Object getItemIdByUrlFragment(String urlFragment) {
        if (null != urlFragment) {
            return categoryRepository.findOne(urlFragment);
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
        return categoryContainer.getItem(itemId);
    }

    @Override
    public Object getItemId(Item item) {
        if (null != item) {
            return ((BeanItem) item).getBean();
        } else {
            return null;
        }
    }

    @Override
    public boolean canHandleItem(Object itemId) {
        if (null != itemId) {
            return categoryContainer.containsId(itemId);
        } else {
            return false;
        }
    }
}
