package com.example.demo.response;

/**
 * Created by babu.kannar@indianic.com on 10/6/2016.
 */
public class MenuResponse {

    private String label;
    private String link;

    public MenuResponse() {

    }

    public MenuResponse(String label, String link) {
        this.label = label;
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
