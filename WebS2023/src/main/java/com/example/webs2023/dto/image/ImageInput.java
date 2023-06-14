package com.example.webs2023.dto.image;

public class ImageInput {
    private String link;
    private Long refId;
    private String refType;

    public ImageInput() {
    }

    public ImageInput(String link, Long refId, String refType) {
        this.link = link;
        this.refId = refId;
        this.refType = refType;
    }

    public String getLink() {
        return link;
    }

    public Long getRefId() {
        return refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }
}
