package com.company;

public class Mensagem
{
    private String msg;
    private String user;
    private boolean read = false;

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}
