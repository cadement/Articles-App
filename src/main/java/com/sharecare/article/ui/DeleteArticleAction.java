package com.sharecare.article.ui;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.inject.Inject;
import com.sharecare.article.model.Article;
import com.sharecare.article.model.ArticleRepository;
import com.vaadin.data.util.BeanItem;
import info.magnolia.event.EventBus;
import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.ui.api.action.AbstractAction;
import info.magnolia.ui.api.action.ActionExecutionException;
import info.magnolia.ui.api.context.UiContext;
import info.magnolia.ui.api.event.AdmincentralEventBus;
import info.magnolia.ui.api.event.ContentChangedEvent;
import info.magnolia.ui.api.overlay.ConfirmationCallback;
import info.magnolia.ui.form.action.SaveFormActionDefinition;
import info.magnolia.ui.vaadin.overlay.MessageStyleTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.Collection;

public class DeleteArticleAction extends AbstractAction<SaveFormActionDefinition> {

    private final Logger logger = LoggerFactory.getLogger(DeleteArticleAction.class);

    @Autowired private ArticleRepository articleRepository;

    private final Collection<BeanItem<Article>> items;
    private final SimpleTranslator              i18n;
    private final UiContext                     uiContext;
    private final EventBus                      eventBus;

    @Inject
    public DeleteArticleAction(SaveFormActionDefinition definition,
                               Collection<BeanItem<Article>> items,
                               SimpleTranslator i18n,
                               UiContext uiContext,
                               @Named(AdmincentralEventBus.NAME) EventBus eventBus,
                               ServletContext servletContext) {
        super(definition);

        logger.info("(" + definition.getName() + "," + items + "," + i18n.getClass().getSimpleName() + "," + uiContext + "," + eventBus + ")");

        this.items = items;
        this.i18n = i18n;
        this.uiContext = uiContext;
        this.eventBus = eventBus;

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        wac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void execute() throws ActionExecutionException {
        logger.info("()");

        uiContext.openConfirmation(
                MessageStyleTypeEnum.WARNING,
                getConfirmationQuestion(),
                i18n.translate("ui-framework.actions.deleteItem.warningText"),
                i18n.translate("ui-framework.actions.deleteItem.confirmText"),
                i18n.translate("ui-framework.actions.deleteItem.cancelText"),
                true,
                new ConfirmationCallback() {

                    @Override
                    public void onSuccess() {
                        DeleteArticleAction.this.executeAfterConfirmation();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
    }

    protected void executeAfterConfirmation() {
        logger.info("()");

        try {
            articleRepository.delete(FluentIterable.from(items)
                                                .transform(new Function<BeanItem<Article>, Article>() {
                                                    @Override
                                                    public Article apply(BeanItem<Article> beanItem) {
                                                        return beanItem.getBean();
                                                    }
                                                }));


            eventBus.fireEvent(new ContentChangedEvent(items.iterator().next().getBean()));

            String message = getSuccessMessage();
            if (!StringUtils.isEmpty(message)) {
                uiContext.openNotification(MessageStyleTypeEnum.INFO, true, message);
            }
        } catch (Exception e) {
            String message = getFailureMessage();
            if (!StringUtils.isEmpty(message)) {
                uiContext.openNotification(MessageStyleTypeEnum.ERROR, false, message);
            }
        }
    }

    private String getConfirmationQuestion() {
        if (items.size() == 1) {
            return i18n.translate("ui-framework.actions.deleteItem.confirmationQuestionOneItem");
        } else {
            return String.format(i18n.translate("ui-framework.actions.deleteItem.confirmationQuestionManyItems"), items.size());
        }
    }

    protected String getSuccessMessage() {
        if (items.size() == 1) {
            return i18n.translate("ui-framework.actions.deleteItem.successOneItemDeleted");
        } else {
            return i18n.translate("ui-framework.actions.deleteItem.successManyItemsDeleted", items.size());
        }
    }

    protected String getFailureMessage() {
        return i18n.translate("ui-framework.actions.deleteItem.deletionfailure", items.size(), items.size());
    }

}
