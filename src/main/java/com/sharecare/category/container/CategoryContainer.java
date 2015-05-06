package com.sharecare.category.container;

import com.google.inject.Inject;
import com.sharecare.category.model.Category;
import com.sharecare.category.model.CategoryRepository;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import info.magnolia.ui.workbench.container.Refreshable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.UUID;

public class CategoryContainer extends BeanItemContainer<Category> implements Refreshable {

    @Autowired private CategoryRepository categoryRepository;

    @Inject
    public CategoryContainer(ServletContext servletContext) {
        super(Category.class);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);

        this.refresh();
    }

    @Override
    public void refresh() {
        this.removeAllItems();
        for (Category category : categoryRepository.findAll()) {
            this.addBean(category);
        }
    }

    @Override
    public Item addItem() throws UnsupportedOperationException {
        Category category = new Category(UUID.randomUUID().toString(),
                                         null,
                                         null);
        categoryRepository.save(category);

        return this.addBean(category);
    }
}
