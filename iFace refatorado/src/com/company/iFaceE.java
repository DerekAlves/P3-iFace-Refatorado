package com.company;

import java.util.Scanner;

public class iFaceE implements Exception
{
    private Scanner in = new Scanner(System.in);
    public int loadint()
    {
        int n = 0;
        boolean ci = false;
        while (!ci) {
            try {
                System.out.print("Insira um número inteiro: ");
                n = Integer.parseInt(in.next());
                ci = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, você digitou um valor que não é inteiro. " + e.getMessage());
            }
        }
        in.nextLine();
        return n;
    }

    public int loadintmargin(int a, int b)
    {
        int n = Integer.MAX_VALUE;
        System.out.printf("Digite um número entre [%d, %d]\n", a, b);
        while(n < a || n > b)
        {
            n = loadint();
            if(n < a) System.out.print("Número menor que o limite...\n");
            else if(n > b) System.out.print("Número maior que o limite...\n");
        }
        return n;
    }


    public double loaddouble()
    {
        double n = 0;
        boolean ci = false;
        while(!ci)
        {
            try
            {
                System.out.print("Insira um número decimal: ");
                n = Double.parseDouble(in.next());
                ci = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Entrada inválida, você digitou um valor que não é decimal. " + e.getMessage());
            }
        }
        in.nextLine();
        return n;
    }
}
