package com.sharecare.category.ui;

import com.sharecare.category.container.CategoryConnector;
import com.sharecare.category.model.Category;
import com.vaadin.data.util.BeanItem;
import info.magnolia.ui.form.field.converter.IdentifierToPathConverter;

import java.util.Locale;

public class CategoryIdentifierToPathConverter implements IdentifierToPathConverter {

    private final CategoryConnector categoryConnector;

    public CategoryIdentifierToPathConverter(CategoryConnector categoryConnector) {
        this.categoryConnector = categoryConnector;
    }

    @Override
    public void setWorkspaceName(String workspaceName) {

    }

    @Override
    public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        return null;
    }

    @Override
    public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        return ((BeanItem<Category>) categoryConnector.getItemIdByUrlFragment(value)).getBean().getTitle();
    }

    @Override
    public Class<String> getModelType() {
        return String.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
