package com.hapiio.pojo.model;

import lombok.Data;

import java.util.Date;

@Data
public class FastDfsFile {

    private Long id;
    private String name;
    private String server;
    private String groupname;
    private String path;
    private Date createTime;


    public FastDfsFile() {

    }

    public FastDfsFile(String name, String server, String groupname, String path, Date createTime) {
        this.name = name;
        this.server = server;
        this.groupname = groupname;
        this.path = path;
        this.createTime = createTime;
    }

    public FastDfsFile(Long id, String name, String server, String groupname, String path, Date createTime) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.groupname = groupname;
        this.path = path;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
