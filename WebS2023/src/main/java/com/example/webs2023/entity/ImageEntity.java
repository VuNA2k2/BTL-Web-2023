package com.example.webs2023.entity;

public class ImageEntity {
    private Long id;
    private String link;
    private Long refId;
    private String refType;

    public ImageEntity() {
    }

    public ImageEntity(Long id, String link, Long refId, String refType) {
        this.id = id;
        this.link = link;
        this.refId = refId;
        this.refType = refType;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
