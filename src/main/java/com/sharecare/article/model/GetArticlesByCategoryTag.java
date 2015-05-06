package com.sharecare.article.model;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetArticlesByCategoryTag extends SimpleTagSupport {

    private String var;
    private String categoryId;

    public void setVar(String var) {
        this.var = var;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
        ArticleRepository articleRepository = wac.getBean(ArticleRepository.class);
        pageContext.setAttribute(var, articleRepository.findByCategory(categoryId), PageContext.PAGE_SCOPE);
    }
}
