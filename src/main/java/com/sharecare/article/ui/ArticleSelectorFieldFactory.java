package com.sharecare.article.ui;

import com.sharecare.article.model.Article;
import com.vaadin.data.Item;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Field;
import info.magnolia.objectfactory.ComponentProvider;
import info.magnolia.ui.api.app.AppController;
import info.magnolia.ui.api.app.ChooseDialogCallback;
import info.magnolia.ui.api.context.UiContext;
import info.magnolia.ui.api.view.View;
import info.magnolia.ui.form.field.definition.LinkFieldDefinition;
import info.magnolia.ui.form.field.factory.LinkFieldFactory;
import info.magnolia.ui.form.field.transformer.Transformer;
import org.apache.commons.lang3.StringUtils;

public class ArticleSelectorFieldFactory extends LinkFieldFactory<ArticleSelectorFieldDefinition> {

    private Field<String> field;

    public ArticleSelectorFieldFactory(ArticleSelectorFieldDefinition definition,
                                       Item relatedFieldItem,
                                       AppController appController,
                                       UiContext uiContext,
                                       ComponentProvider componentProvider) {
        super(definition, relatedFieldItem, appController, uiContext, componentProvider);
    }

    @Override
    protected ChooseDialogCallback createChooseDialogCallback() {
        return new ChooseDialogCallback() {
            @Override
            public void onItemChosen(String actionName, final Object chosenValue) {
                String newValue = null;
                Article article = (Article) chosenValue;
                String id = article.getId();
                if (StringUtils.isNotBlank(id)) {
                    newValue = id;
                }
                field.setValue(newValue);
            }

            @Override
            public void onCancel() {
            }
        };
    }

    @Override
    public LinkFieldDefinition getFieldDefinition() {
        return super.getFieldDefinition();
    }

    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    protected Transformer<?> initializeTransformer(Class<? extends Transformer<?>> transformerClass) {
        return super.initializeTransformer(transformerClass);
    }

    @Override
    protected Converter<?, ?> initializeConverter(Class<? extends Converter<?, ?>> converterClass) {
        return super.initializeConverter(converterClass);
    }

    @Override
    protected Field<String> createFieldComponent() {
        field = super.createFieldComponent();
        return field;
    }
}
