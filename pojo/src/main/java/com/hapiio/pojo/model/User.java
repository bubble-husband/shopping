package com.hapiio.pojo.model;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private Date createdate;

    private Date lastlogintime;

    private Integer enabled;

    private Integer accountnonexpired;

    private Integer accountnonlocked;

    private Integer credentialsnonexpired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getAccountnonexpired() {
        return accountnonexpired;
    }

    public void setAccountnonexpired(Integer accountnonexpired) {
        this.accountnonexpired = accountnonexpired;
    }

    public Integer getAccountnonlocked() {
        return accountnonlocked;
    }

    public void setAccountnonlocked(Integer accountnonlocked) {
        this.accountnonlocked = accountnonlocked;
    }

    public Integer getCredentialsnonexpired() {
        return credentialsnonexpired;
    }

    public void setCredentialsnonexpired(Integer credentialsnonexpired) {
        this.credentialsnonexpired = credentialsnonexpired;
    }
}