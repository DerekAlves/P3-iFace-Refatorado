package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    private static Scanner in = new Scanner(System.in);
    private static Utility utili = new iFaceU();
    private static Exception exc = new iFaceE();

    private static void menu(Rede iface)
    {
        int option, i = 0;
        boolean exit = false;
        Usuario logado = null;
        int j;

        while(!exit)
        {
            if(logado == null)
            {
                System.out.print("---------------------------------------------------------\n");
                System.out.print("Nenhum usuário logado! Logue ou crie uma conta!\n");
                System.out.print("Digite:\n");
                System.out.print("[0]  - Sair;\n");
                System.out.print("[1]  - Criar conta;\n");
                System.out.print("[2]  - Logar;\n");

                option = exc.loadintmargin(0, 2);
                if(option == 0)
                {
                    System.out.print("---------------------------------------------------------\n");
                    System.out.print("Saindo!\n");
                    exit = true;
                }
                else if(option == 1)
                {
                    System.out.print("---------------------------------------------------------\n");
                    String login, password, user;
                    System.out.print("[1]  - Criar conta selecionada!\n");
                    System.out.print("Criando nova conta...\n");
                    System.out.print("Digite o seu login: ");
                    login = in.nextLine();
                    System.out.print("Digite a sua senha: ");
                    password = in.nextLine();
                    System.out.print("Digite o seu nome de Usuário (como você será conhecido na rede): ");
                    user = in.nextLine();
                    Usuario novo = new Usuario(login, password, user);
                    iface.getUsers().add(novo);
                    System.out.print("Usuário adicionado com sucesso!\n");
                }
                else if(option == 2)
                {
                    System.out.print("---------------------------------------------------------\n");
                    System.out.print("[2]  - Logar selecionada!\n");
                    System.out.print("Deseja listar usuários? [1] - Sim | [2] - Não: ");
                    option = exc.loadintmargin(1, 2);
                    System.out.print("---------------------------------------------------------\n");
                    if(option == 1) utili.listuser(iface.getUsers());
                    System.out.print("Digite seu login: ");
                    i = utili.finduser(iface.getUsers(), in.nextLine());
                    //System.out.print("---------------------------------------------------------\n");
                    if(i >= 0)
                    {
                        //System.out.print("---------------------------------------------------------\n");
                        System.out.print("Digite sua senha: ");
                        if(in.nextLine().equals(iface.getUsers().get(i).getPassword())) logado = iface.getUsers().get(i);
                        else System.out.print("Senha Inválida! Operação cancelada!\n");
                    }
                    else System.out.print("Tentativa de logar falhou (Login inválido), tente novamente!\n");
                }
            }
            else
            {
                System.out.print("---------------------------------------------------------\n");
                System.out.printf("Você está logado como: %s\n", iface.getUsers().get(i).getLogin());
                System.out.print("Gostaria de deslogar? [1] - Sim | [2] - Não: ");
                option = exc.loadintmargin(1, 2);
                if(option == 1)
                {
                    System.out.print("---------------------------------------------------------\n");
                    System.out.print("Usuário deslogado com sucesso!\n");
                    logado = null;
                }
                else
                {
                    System.out.print("---------------------------------------------------------\n");
                    System.out.printf("Você tem um total de %d mensagens, lidas %d, não lidas %d.\n",
                            iface.getUsers().get(i).getMsg().size(), utili.msgread(iface.getUsers().get(i).getMsg()),
                            iface.getUsers().get(i).getMsg().size() - utili.msgread(iface.getUsers().get(i).getMsg()));

                    if(iface.getUsers().get(i).getMsg().size() > 0)
                    {
                        System.out.print("Gostaria de ler as mensagens? [1] - Sim | [2] - Não: ");
                        option = exc.loadintmargin(1, 2);
                        if(option == 1) utili.readmsgs(iface.getUsers().get(i).getMsg());
                    }
                    System.out.print("---------------------------------------------------------\n");
                    System.out.printf("Você tem um total de %d solicitações de amizade.\n", iface.getUsers().get(i).getSolicitacoes().size());
                    if(iface.getUsers().get(i).getSolicitacoes().size() > 0)
                    {
                        System.out.print("Gostaria de ver as solicitações? [1] - Sim | [2] - Não: ");
                        option = exc.loadintmargin(1, 2);
                        if(option == 1) utili.accsamz(iface.getUsers(), iface.getUsers().get(i));
                    }
                    System.out.print("---------------------------------------------------------\n");
                    System.out.print("Digite:\n");
                    System.out.print("[0]  - Sair;\n");
                    System.out.print("[2]  - Editar Conta;\n");
                    System.out.print("[3]  - Adição de Amigos;\n");
                    System.out.print("[4]  - Envio de Mensagens;\n");
                    System.out.print("[5]  - Criação de Comunidades;\n");
                    System.out.print("[6]  - Adição de membros a uma comunidade;\n");
                    System.out.print("[7]  - Recuperar Informações sobre um determinado Usuário;\n");
                    System.out.print("[8]  - Remoção de Conta;\n");
                    System.out.print("[9]  - Não fazer nada.\n");

                    option = exc.loadintmargin(0, 9);
                    if(option == 0)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("Saindo\n");
                        exit = true;
                    }
                    else if (option == 2)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[2]  - Editar Conta selecionada!\n");
                        System.out.printf("Editando conta do Usuário logado!\nLogin: %s\nSe deseja editar outro Usuário, por favor logue com uma nova conta!\n", iface.getUsers().get(i).getLogin());
                        utili.editconta(iface.getUsers().get(i), iface);
                        logado = iface.getUsers().get(i);
                    }
                    else if (option == 3)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[3]  - Adição de Amigos selecionada!\n");
                        System.out.print("Digite o nome do usuário que deseja adicionar: ");
                        j = utili.finduser(iface.getUsers(), in.nextLine());
                        if(j >= 0) utili.addamz(iface.getUsers().get(i), iface.getUsers().get(j));
                        else System.out.print("Tente novamente, Usuário não encontrado!\n");

                    }
                    else if (option == 4)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[4]  - Envio de Mensagens selecionada!\n");
                        System.out.print("Digite o nome do destinatário da mensagem: ");
                        j = utili.finduser(iface.getUsers(), in.nextLine());
                        if(j >= 0) utili.envmsg(iface.getUsers().get(i), iface.getUsers().get(j));
                        else System.out.print("Tente novamente, Usuário não encontrado!\n");
                    }
                    else if (option == 5)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        String nome, descricao;
                        System.out.print("[5]  - Criação de Comunidades selecionada!\n");
                        System.out.print("Criando nova comunidade...\n");
                        System.out.print("Digite o nome da comunidade: ");
                        nome = in.nextLine();
                        System.out.print("Digite a descrição da comunidade: ");
                        descricao = in.nextLine();
                        Comunidade nova = new Comunidade(nome, descricao, logado);
                        nova.getMembros().add(logado);
                        System.out.print("Comunidade criada com sucesso!\n");
                        logado.getComunidades().add(nome);
                        iface.getComunidades().add(nova);
                    }
                    else if (option == 6)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[6]  - Adição de membros a uma comunidade selecionada!\n");
                        utili.addusertocomm(iface, logado.getUser());
                    }
                    else if (option == 7)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[7]  - Recuperar Informações sobre um determinado Usuário selecionada!\n");
                        utili.recuperarinfo(iface.getUsers(), iface.getUsers().get(i));
                    }
                    else if (option == 8)
                    {
                        System.out.print("---------------------------------------------------------\n");
                        System.out.print("[8]  - Remoção de Conta selecionada!\n");
                        System.out.print("Deseja realmente remover a conta? [1] - S | [2] - N\n");
                        option = exc.loadintmargin(1, 2);
                        if(option == 1)
                        {
                            utili.removeacc(iface, logado);
                            logado = null;
                        }
                        else System.out.print("Operação cancelada!");
                    }
                }
            }
        }
    }


    public static void main(String[] args)
    {
        Rede iface = new Rede();
        menu(iface);
    }
}
