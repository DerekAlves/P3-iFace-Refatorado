package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario
{
    private static Scanner in = new Scanner(System.in);

    private String login;
    private String password;
    private String user;
    private ArrayList<String> amigos = new ArrayList<String>();
    private ArrayList<String> comunidades = new ArrayList<String>();
    private ArrayList<Mensagem> msg = new ArrayList<Mensagem>();
    private ArrayList<String> solicitacoes = new ArrayList<String>();

    public Usuario(String login, String password, String user)
    {
        this.login = login;
        this.password = password;
        this.user = user;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public ArrayList<String> getAmigos() { return amigos; }
    public void setAmigos(ArrayList<String> amigos) { this.amigos = amigos; }

    public ArrayList<String> getComunidades() { return comunidades; }
    public void setComunidades(ArrayList<String> comunidades) { this.comunidades = comunidades; }

    public ArrayList<Mensagem> getMsg() { return msg; }
    public void setMsg(ArrayList<Mensagem> msg) { this.msg = msg; }

    public ArrayList<String> getSolicitacoes() { return solicitacoes; }
    public void setSolicitacoes(ArrayList<String> solicitacoes) { this.solicitacoes = solicitacoes; }
}
