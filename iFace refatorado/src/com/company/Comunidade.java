package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Comunidade
{
    Scanner in = new Scanner(System.in);
    private String nome;
    private String descricao;
    private String adm;
    private ArrayList<Usuario> membros = new ArrayList<Usuario>();

    public Comunidade(String nome, String descricao, Usuario adm)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.adm = adm.getUser();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getAdm() { return adm; }
    public void setAdm(String adm) { this.adm = adm; }

    public ArrayList<Usuario> getMembros() { return membros; }
    public void setMembros(ArrayList<Usuario> membros) { this.membros = membros; }

    @Override
    public String toString() {
        return "---------------------------------------------------------\nNome da comunidade: " + nome + "\nDescrição: " + descricao +"\nAdministrador: " + adm + "\n";
    }
}
