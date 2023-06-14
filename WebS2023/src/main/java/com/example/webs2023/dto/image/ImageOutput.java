package com.example.webs2023.dto.image;

public class ImageOutput {

    private Long id;
    private String link;

    public ImageOutput() {
    }

    public ImageOutput(String link) {
        this.link = link;
    }

    public ImageOutput(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
