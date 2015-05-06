package com.sharecare.category.ui;

import com.sharecare.category.model.CategoryRepository;
import com.vaadin.ui.Table;
import info.magnolia.ui.workbench.column.definition.ColumnFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class CategoryColumnFormatter implements ColumnFormatter {

    @Autowired private CategoryRepository categoryRepository;

    public CategoryColumnFormatter(ServletContext servletContext) {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        String categoryId = (String) source.getItem(itemId).getItemProperty(columnId).getValue();
        if (null != categoryId) {
            return categoryRepository.findOne(categoryId).getTitle();
        } else {
            return "";
        }
    }
}
