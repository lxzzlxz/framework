package com.liuzemin.server.framework.fdfs.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class FileModel implements Serializable {

    private static final long serialVersionUID = -6101764721639561249L;

    @ApiModelProperty(value="文件名称")
    private String fileName;

    @ApiModelProperty(value="文件路径")
    private String filePath;

    @ApiModelProperty(value="文件大小")
    private Long size;

    @ApiModelProperty(value="文件下载url")
    private String url;

    @ApiModelProperty(value="类型，1：目录，2：文件")
    private Integer type;

    @ApiModelProperty(value="子节点")
    private List<FileModel> children;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<FileModel> getChildren() {
        return children;
    }

    public void setChildren(List<FileModel> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
