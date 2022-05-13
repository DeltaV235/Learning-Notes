package com.deltav.mat.example1;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class WebPage {
    private String url;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
