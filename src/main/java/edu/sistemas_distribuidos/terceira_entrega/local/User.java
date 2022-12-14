package edu.sistemas_distribuidos.terceira_entrega.local;

import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Parametro;
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
                    System.out.println("Buscando Imovel -------- \n Digite a faixa de preço:");
                    double faixaPreco = input.nextDouble();
                    input.nextLine();
                    System.out.println(ProxyGerenciarImovel.buscarImovel(new Parametro(faixaPreco)));
                    break;
                case "3":
                    System.out.println("Removendo Imovel ---- \n Digite o N° ID do imovel que quer remover:");
                    int idImovel = input.nextInt();
                    input.nextLine();
                    System.out.println(ProxyGerenciarImovel.removerImovel(new Parametro(idImovel)));
                    break;
                case "4":
                    System.out.println("Editando Imovel --- \n Digite o N° ID do imovel que quer editar:");
                    int idImovel2 = input.nextInt();
                    input.nextLine();
                    System.out.println("Novo nome do proprietario do imovel:");
                    String novoNomeProprietario = input.nextLine();
                    System.out.println("Novo endereco do imovel:");
                    String novoEndereco = input.nextLine();
                    System.out.println("Novo preco do imovel:");
                    int novoPreco = input.nextInt();
                    input.nextLine();
                    System.out.println(ProxyGerenciarImovel.editarImovel(new Parametro(idImovel2),
                            new Imovel(novoNomeProprietario,novoEndereco,novoPreco)));
                    break;
                case "5":
                    ProxyGerenciarImovel.closeSocket();
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
