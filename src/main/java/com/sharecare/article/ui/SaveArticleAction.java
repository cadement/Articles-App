package com.sharecare.article.ui;

import com.google.inject.Inject;
import com.sharecare.article.model.Article;
import com.sharecare.article.model.ArticleRepository;
import com.vaadin.data.util.BeanItem;
import info.magnolia.ui.api.action.AbstractAction;
import info.magnolia.ui.api.action.ActionExecutionException;
import info.magnolia.ui.form.EditorCallback;
import info.magnolia.ui.form.EditorValidator;
import info.magnolia.ui.form.action.SaveFormActionDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class SaveArticleAction extends AbstractAction<SaveFormActionDefinition> {

    private final Logger logger = LoggerFactory.getLogger(SaveArticleAction.class);

    @Autowired private ArticleRepository articleRepository;

    private final BeanItem<Article> item;
    private final EditorCallback    callback;
    private final EditorValidator   validator;

    @Inject
    public SaveArticleAction(SaveFormActionDefinition definition,
                                BeanItem<Article> item,
                                EditorCallback callback,
                                EditorValidator validator,
                                ServletContext servletContext) {
        super(definition);

        logger.info("(" + definition.getName() + "," + item + "," + callback + "," + validator + "," + servletContext + ")");

        this.item = item;
        this.callback = callback;
        this.validator = validator;

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void execute() throws ActionExecutionException {
        logger.info("()");

        validator.showValidation(true);
        if (validator.isValid()) {
            try {
                articleRepository.save(item.getBean());
            } catch (Exception e) {
                throw new ActionExecutionException(e);
            }
            callback.onSuccess(getDefinition().getName());
        }
    }
}
