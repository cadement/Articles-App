package com.sharecare.article.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Article {

    @Id
    private String id;
    private String url;
    private String title;
    private String category;
    private Date   published;
    private String body;
    private String state;

    public Article(String id, String url, String title, String category, Date published, String body, String state) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.category = category;
        this.published = published;
        this.body = body;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!id.equals(article.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Article:" + ((null == title) ? id : title);
    }
}
