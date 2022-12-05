package edu.sistemas_distribuidos.terceira_entrega.local;

import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;
import edu.sistemas_distribuidos.terceira_entrega.local.proxys.ProxyGerenciarImovel;

import java.util.Scanner;

public class User {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Opções: cadastrar um imovel (1), buscar imovel por preço (2), " +
                    "remover imovel(3), editar imovel(4), Sair do programa(5)");
            String opcaoEscolhida = input.nextLine();
            switch (opcaoEscolhida) {
                case "1":
                    System.out.println("\nCadastrando Imovel -------\nDigite o nome do proprietário do imovel:");
                    String nomeProprietario = input.nextLine();
                    System.out.println("Digite o endereco do imovel: ");
                    String endereco = input.nextLine();
                    System.out.println("Digite o preco do imovel: ");
                    double precoImovel = input.nextDouble();
                    input.nextLine();
                    System.out.println(ProxyGerenciarImovel.cadastrarImovel(new Imovel(nomeProprietario, endereco, precoImovel)));
                    break;
                case "2":
                    System.out.println("Buscando Imovel");
                    break;
                case "3":
                    System.out.println("Removendo Imovel");
                    break;
                case "4":
                    System.out.println("Editando Imovel");
                    break;
                case "5":
                    System.out.println("Saindo....");
                    System.out.println("Até a próxima execução :)");
                    loop = false;
                    break;
                default:
                    System.out.println("Opcao Inválida");
            }
        }
    }
}
