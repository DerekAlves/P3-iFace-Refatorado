package com.company;

import java.util.ArrayList;

public class Rede
{
    ArrayList<Usuario> users = new ArrayList<Usuario>();
    ArrayList<Comunidade> comunidades = new ArrayList<Comunidade>();

    public ArrayList<Usuario> getUsers() { return users; }
    public void setUsers(ArrayList<Usuario> users) { this.users = users; }

    public ArrayList<Comunidade> getComunidades() { return comunidades; }
    public void setComunidades(ArrayList<Comunidade> comunidades) { this.comunidades = comunidades; }
}
