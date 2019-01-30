package com.company;

import java.util.ArrayList;

public interface Utility
{
    int finduser(ArrayList<Usuario> users, String user);
    void listuser(ArrayList<Usuario> users);
    void editconta(Usuario user, Rede iface);
    void envmsg(Usuario rem, Usuario dest);
    int findcomm(ArrayList<Comunidade> comunidades, String comunidade);
    void addusertocomm(Rede iface, String adm);
    void listcomm(ArrayList<String> comunidades);
    void listamz(ArrayList<String> users);
    int msgread(ArrayList<Mensagem> msgs);
    void recuperarinfo(ArrayList<Usuario> users, Usuario atual);
    void passmsgs(ArrayList<Mensagem> msgs, boolean todas);
    void accsamz(ArrayList<Usuario> users, Usuario dest);
    void removeacc(Rede iface, Usuario remover);
    void attatr(Rede iface, Usuario att, String before);
    void readmsgs(ArrayList<Mensagem> msgs);
    void addamz(Usuario rem, Usuario dest);

}
