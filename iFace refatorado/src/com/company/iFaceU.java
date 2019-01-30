package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class iFaceU implements Utility
{
    private static Scanner in = new Scanner(System.in);
    Exception exc = new iFaceE();

    public int finduser(ArrayList<Usuario> users, String user)//encontrar usuário
    {
        System.out.printf("Procurando usuário: %s...\n", user);
        int i;
        for(i = 0; i < users.size(); i++)
        {
            if(user.equals(users.get(i).getUser()))
            {
                System.out.print("Usuário encontrado!\n");
                return i;
            }
            else if (user.equals(users.get(i).getLogin()))
            {
                System.out.print("Login encontrado!\n");
                return i;
            }
        }
        return -1;//nao encontrado
    }

    public int findcomm(ArrayList<Comunidade> comunidades, String comunidade)//ENCONTRAR COMUNIDADE
    {
        System.out.printf("Procurando comunidade: %s...\n", comunidade);
        int i;
        for(i = 0; i < comunidades.size(); i++)
        {
            if(comunidade.equals(comunidades.get(i).getNome()))
            {
                System.out.print("Comunidade encontrada!\n");
                return i;
            }
        }
        return -1;//nao encontrado
    }

    public void listuser(ArrayList<Usuario> users)//listar usuários
    {
        System.out.print("Listando usuários...\n");
        int i;
        for(i = 0; i < users.size(); i++)
            System.out.printf("Usuário: %s | Login: %s\n", users.get(i).getUser(), users.get(i).getLogin());
    }

    public void editconta(Usuario user, Rede iface)// editar conta
    {
        int option;
        String bef = user.getUser();
        System.out.printf("Editando a conta de: %s...\n", user.getUser());
        System.out.print("Deseja mesmo editar? [1] - Sim | [2] - Não: ");
        option = exc.loadintmargin(1, 2);
        if(option == 1)
        {
            System.out.print("Deseja editar seu login? [1] - Sim | [2] - Não: ");
            option = exc.loadintmargin(1, 2);
            if(option == 1)
            {
                in.nextLine();
                System.out.print("Digite o novo login: ");
                user.setLogin(in.nextLine());
            }
            else System.out.print("Não editado\n");
            System.out.print("Deseja editar sua senha? [1] - Sim | [2] - Não: ");
            option = exc.loadintmargin(1, 2);
            if(option == 1)
            {
                in.nextLine();
                System.out.print("Digite a nova senha: ");
                user.setPassword(in.nextLine());
            }
            else System.out.print("Não editado\n");
            System.out.print("Deseja editar seu nome de Usuário? [1] - Sim | [2] - Não: ");
            option = exc.loadintmargin(1, 2);
            if(option == 1)
            {
                in.nextLine();
                System.out.print("Digite o novo nome de Usuário: ");
                user.setUser(in.nextLine());
            }
            else System.out.print("Não editado\n");
            attatr(iface, user, bef);
        }
        else System.out.print("Edição cancelada!\n");

        System.out.print("Fim da edição!\n");
    }

    public void envmsg(Usuario rem, Usuario dest)//enviar mensagem
    {
        int option;
        System.out.printf("Você está enviando uma mensagem para: %s.\n", dest.getUser());
        System.out.print("Deseja mesmo enviar a mensagem? [1] - Sim | [2] - Não: ");
        option = exc.loadintmargin(1, 2);
        if(option == 1)
        {
            in.nextLine();
            System.out.print("Digite a mensagem a ser enviada:\n");
            Mensagem nova = new Mensagem();
            nova.setMsg(in.nextLine());
            nova.setUser(rem.getUser());
            dest.getMsg().add(nova);
            System.out.print("Mensagem enviada com sucesso!\n");
        }
        else System.out.print("Mensagem não enviada!\n");
    }

    public void addusertocomm(Rede iface, String adm)//Adicionar Usuario a comunidade
    {
        System.out.print("Digite o nome da comunidade que irá receber o novo membro: ");
        int i = findcomm(iface.getComunidades(), in.nextLine());
        int j;
        if(i >= 0)
        {
            System.out.println(iface.getComunidades().get(i).toString());
            if(adm.equals(iface.comunidades.get(i).getAdm()))
            {
                System.out.print("Digite o nome do usuário a ser adicionado: ");
                j = finduser(iface.getUsers(), in.nextLine());
                if(j >= 0)
                {
                    iface.getComunidades().get(i).getMembros().add(iface.getUsers().get(j));
                    iface.getUsers().get(i).getComunidades().add(iface.getComunidades().get(i).getNome());
                    System.out.print("Usuário adicionado a comunidade com sucesso!\n");
                }
            }
            else System.out.print("Você não é o administrador da comunidade, só administradores podem adicionar novos membros!\n");
        }
    }

    public void listcomm(ArrayList<String> comunidades)//Listar comunidades
    {
        System.out.print("Listando comunidades...\n");
        int i;
        for(i = 0; i < comunidades.size(); i++)
            System.out.printf("%s\n", comunidades.get(i));
    }

    public void listamz(ArrayList<String> users)//Listar Amizades
    {
        System.out.print("Listando amigos...\n");
        int i;
        for(i = 0; i < users.size(); i++)
            System.out.printf("%s\n", users.get(i));
    }

    public int msgread(ArrayList<Mensagem> msgs)//calcular mensagens lidas.
    {
        int i = 0;
        for(Mensagem msg: msgs) if(msg.isRead()) i++;
        return i;
    }


    public void recuperarinfo(ArrayList<Usuario> users, Usuario atual)//Recuperar informações
    {
        int i;
        System.out.print("Deseja recuperar suas informações [1], ou informações de outro usuário [2]? : ");
        int option = exc.loadintmargin(1, 2);
        if(option == 1)
        {
            System.out.print("Recuperando suas informações...\n");
            System.out.printf("Nome: %s\nLogin: %s\nSenha: %s\n", atual.getUser(), atual.getLogin(), atual.getPassword());
            listamz(atual.getAmigos());
            listcomm(atual.getComunidades());
            System.out.printf("Você tem um total de %d mensagens, lidas %d, não lidas %d.\n", atual.getMsg().size(), msgread(atual.getMsg()), atual.getMsg().size() - msgread(atual.getMsg()));
            System.out.printf("Você tem %d solicitações de amizade!\n", atual.getSolicitacoes().size());
            System.out.print("Fim!\n");
        }
        else if(option == 2)
        {
            System.out.print("Digite o nome do usuário procurado: ");
            i = finduser(users, in.nextLine());
            System.out.printf("Nome: %s\n", users.get(i).getUser());
            listamz(users.get(i).getAmigos());
            listcomm(users.get(i).getComunidades());
            System.out.print("Fim!\n");
        }
    }

    public void addamz(Usuario rem, Usuario dest)//adicionar amizade
    {
        System.out.printf("Deseja realmente enviar um pedido de amizade a: %s? [1] - Sim | [2] - Não: ", dest.getUser());
        int option;
        option = exc.loadintmargin(1, 2);
        if(option == 1) dest.getSolicitacoes().add(rem.getUser());
        else System.out.print("Operação cancelada!\n");
    }

    public void readmsgs(ArrayList<Mensagem> msgs)//ler mensagens
    {
        System.out.print("Deseja ver todas as mensagens? [1]\nOu ver apenas as não lidas? [2]: ");
        int option;
        option = exc.loadintmargin(1, 2);
        if(option == 1)
        {
            System.out.print("Todas as mensagens!\n");
            passmsgs(msgs, true);
        }
        else if(option == 2)
        {
            System.out.print("Apenas mensagens não lidas!\n");
            passmsgs(msgs, false);
        }
    }

    public void passmsgs(ArrayList<Mensagem> msgs, boolean todas)
    {
        int option;

        for(Mensagem msg: msgs)
        {
            if(todas)
            {
                System.out.print("---------------------------------------------------------\n");
                System.out.printf("%s\nEnviada por: %s\n\n", msg.getMsg(), msg.getUser());
                if(msg.isRead())
                {
                    System.out.print("Deseja marcar como não lida? [1] - Sim | 2 - Não: ");
                    option = exc.loadintmargin(1, 2);
                    if(option == 1) msg.setRead(false);
                }
                else
                {
                    System.out.print("Deseja marcar como lida? [1] - Sim | 2 - Não: ");
                    option = exc.loadintmargin(1, 2);
                    if(option == 1) msg.setRead(true);
                }
            }
            else
            {
                System.out.print("---------------------------------------------------------\n");
                System.out.printf("%s\nEnviada por: %s\n\n", msg.getMsg(), msg.getUser());
                System.out.print("Deseja marcar como lida? [1] - Sim | 2 - Não: ");
                option = exc.loadintmargin(1, 2);
                if(option == 1) msg.setRead(true);
            }
        }
    }


    public void accsamz(ArrayList<Usuario> users, Usuario dest)//aceitar amizade
    {
        int i, j;
        int option;
        for (j = 0; j < dest.getSolicitacoes().size(); j++)
        {
            System.out.printf("Solicitação de amizade enviada por: %s. Aceitar - [1] | Rejeitar - [2]: ", dest.getSolicitacoes().get(j));
            option = exc.loadintmargin(1, 2);

            if(option == 1)
            {
                System.out.print("Aceita!\n");
                i = finduser(users, dest.getSolicitacoes().get(j));
                dest.getAmigos().add(dest.getSolicitacoes().get(j));
                users.get(i).getAmigos().add(dest.getUser());
            }
            else
            {
                System.out.print("Rejeitado!\n");
            }
            dest.getSolicitacoes().remove(dest.getSolicitacoes().get(j));
        }
    }

    public void removeacc(Rede iface, Usuario remover)//remover conta
    {
        System.out.printf("Removendo a conta de: %s...\n", remover.getLogin());
        iface.getUsers().remove(remover);
        int i, j;
        for(i = 0; i < iface.getUsers().size(); i++)//remover mensagens
        {
            for (j = 0; j < iface.getUsers().get(i).getMsg().size(); j++)
            {
                if(remover.getUser().equals(iface.getUsers().get(i).getMsg().get(j).getUser() ) )
                    iface.getUsers().get(i).getMsg().remove(iface.getUsers().get(i).getMsg().get(j));
            }
        }

        for(i = 0; i < iface.getUsers().size(); i++)//remover amizades
        {
            for (j = 0; j < iface.getUsers().get(i).getAmigos().size(); j++)
            {
                if(remover.getUser().equals(iface.getUsers().get(i).getAmigos().get(j) ) )
                    iface.getUsers().get(i).getAmigos().remove(iface.getUsers().get(i).getAmigos().get(j));
            }
        }

        for(i = 0; i < iface.getUsers().size(); i++)//remover solicitações
        {
            for (j = 0; j < iface.getUsers().get(i).getSolicitacoes().size(); j++)
            {
                if(remover.getUser().equals(iface.getUsers().get(i).getSolicitacoes().get(j) ) )
                    iface.getUsers().get(i).getSolicitacoes().remove(iface.getUsers().get(i).getSolicitacoes().get(j));
            }
        }
        boolean flag = false;
        for(i = 0; i < iface.getComunidades().size(); i++)//remover da comunidade e por adm se necessário
        {
            if(remover.getUser().equals(iface.getComunidades().get(i).getAdm()))
            {
                for(j = 0; j < iface.getComunidades().get(i).getMembros().size(); j++)
                {
                    if( !(remover.getUser().equals(iface.getComunidades().get(i).getMembros().get(j).getUser() ) ) )
                    {
                        iface.getComunidades().get(i).setAdm(iface.getComunidades().get(i).getMembros().get(j).getUser());
                        flag = true;
                    }
                }
                if(!flag) iface.getComunidades().remove(iface.comunidades.get(i));
            }
            for(j = 0; j < iface.getComunidades().get(i).getMembros().size(); j++)
            {
                if(remover.getUser().equals(iface.getComunidades().get(i).getMembros().get(j).getUser()))
                    iface.getComunidades().get(i).getMembros().remove(iface.getComunidades().get(i).getMembros().get(j));
            }
        }
    }

    public void attatr(Rede iface, Usuario att, String before)//atualizar informações /rever
    {
        System.out.print("Atualizando dados...\n");
        int i, j;
        for(i = 0; i < iface.getUsers().size(); i++)//atualizar remetente da msg
        {
            for (j = 0; j < iface.getUsers().get(i).getMsg().size(); j++)
            {
                if(before.equals(iface.getUsers().get(i).getMsg().get(j).getUser() ) )
                    iface.getUsers().get(i).getMsg().get(j).setUser(att.getUser());
            }
        }

        for(i = 0; i < iface.getUsers().size(); i++)//att amigo
        {
            for (j = 0; j < iface.getUsers().get(i).getAmigos().size(); j++)
            {
                if(before.equals(iface.getUsers().get(i).getAmigos().get(j) ) )
                {
                    iface.getUsers().get(i).getAmigos().remove(before);
                    iface.getUsers().get(i).getAmigos().add(att.getUser());
                }
            }
        }

        for(i = 0; i < iface.getUsers().size(); i++)//att sol de amz
        {
            for (j = 0; j < iface.getUsers().get(i).getSolicitacoes().size(); j++)
            {
                if(before.equals(iface.getUsers().get(i).getSolicitacoes().get(j) ) )
                {
                    iface.getUsers().get(i).getSolicitacoes().remove(before);
                    iface.getUsers().get(i).getSolicitacoes().add(att.getUser());
                }
            }
        }

        for(i = 0; i < iface.getComunidades().size(); i++)//att info na comm
        {
            if(before.equals(iface.getComunidades().get(i).getAdm()))
            {
                iface.getComunidades().get(i).setAdm(att.getUser());
            }

            for(j = 0; j < iface.getComunidades().get(i).getMembros().size(); j++)
            {
                if(before.equals(iface.getComunidades().get(i).getMembros().get(j).getUser()))
                {
                    Usuario aux = iface.getComunidades().get(i).getMembros().get(j);
                    iface.getComunidades().get(i).getMembros().remove(aux);
                    iface.getComunidades().get(i).getMembros().add(att);
                }
            }
        }
    }
}
