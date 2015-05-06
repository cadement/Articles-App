package com.sharecare.category.ui;

import com.google.inject.Inject;
import com.sharecare.category.model.Category;
import com.sharecare.category.model.CategoryRepository;
import com.vaadin.data.util.BeanItem;
import info.magnolia.ui.api.action.AbstractAction;
import info.magnolia.ui.api.action.ActionExecutionException;
import info.magnolia.ui.form.EditorCallback;
import info.magnolia.ui.form.EditorValidator;
import info.magnolia.ui.form.action.SaveFormActionDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SaveCategoryAction extends AbstractAction<SaveFormActionDefinition> {

    @Autowired private CategoryRepository categoryRepository;

    private final BeanItem<Category> item;
    private final EditorCallback     callback;
    private final EditorValidator    validator;

    @Inject
    public SaveCategoryAction(SaveFormActionDefinition definition,
                              BeanItem<Category> item,
                              EditorCallback callback,
                              EditorValidator validator,
                              ServletContext servletContext) {
        super(definition);

        this.item = item;
        this.callback = callback;
        this.validator = validator;

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void execute() throws ActionExecutionException {
        validator.showValidation(true);
        if (validator.isValid()) {
            try {
                categoryRepository.save(item.getBean());
            } catch (Exception e) {
                throw new ActionExecutionException(e);
            }
            callback.onSuccess(getDefinition().getName());
        }
    }
}
