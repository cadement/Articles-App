package com.sharecare.article.model;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class GetArticleTag extends SimpleTagSupport {

    private String var;
    private String articleId;

    public void setVar(String var) {
        this.var = var;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
        ArticleRepository articleRepository = wac.getBean(ArticleRepository.class);
        pageContext.setAttribute(var, articleRepository.findOne(articleId), PageContext.PAGE_SCOPE);
    }
}
