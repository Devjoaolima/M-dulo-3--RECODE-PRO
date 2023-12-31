import java.util.Scanner;

public class Agencia {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        PassagemDAO passagemDAO = new PassagemDAO();
        ViagemDAO viagemDAO = new ViagemDAO();

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n====================== Agencia de Viagem ======================\n");
                System.out.println("1 - Realizar Cadastro de Cliente");
                System.out.println("2 - Cancelar Cadastro de Cliente");
                System.out.println("3 - Atualizar Cadastro de Cliente");
                System.out.println("4 - Visualizar Cadastro de Cliente");
                System.out.println("5 - Realizar Cadastro de Viagem");
                System.out.println("6 - Cancelar Viagem");
                System.out.println("7 - Atualizar Viagem");
                System.out.println("8 - Visualizar Pedido de Passagem");
                System.out.println("9 - Emitir Viagem");
                System.out.println("10 - Excluir Viagem");
                System.out.println("11 - Atualizar Viagem");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        Cliente clienteCadastro = new Cliente();
                        clienteDAO.realizarCadastro(clienteCadastro);
                    }
                    case 2 -> {
                        System.out.print("Digite o ID do cliente a ser cancelado: ");
                        int clienteIdCancelar = scanner.nextInt();
                        clienteDAO.cancelarCadastro(clienteIdCancelar);
                    }
                    case 3 -> {
                        Cliente clienteAtualizacao = new Cliente();
                        clienteDAO.atualizarCadastro(clienteAtualizacao);
                    }
                    case 4 -> {
                        System.out.print("Digite o ID do cliente a ser visualizado: ");
                        int clienteIdVisualizar = scanner.nextInt();
                        clienteDAO.visualizarCadastro(clienteIdVisualizar);
                    }
                    case 5 -> {
                        Passagem viagemCadastro = new Passagem();
                        passagemDAO.realizarCadastro(viagemCadastro);
                    }
                    case 6 -> {
                        System.out.print("Digite o ID da viagem a ser cancelada: ");
                        int viagemIdCancelar = scanner.nextInt();
                        passagemDAO.cancelarViagem(viagemIdCancelar);
                    }
                    case 7 -> {
                        Passagem viagemAtualizacao = new Passagem();
                        passagemDAO.atualizarViagem(viagemAtualizacao);
                    }
                    case 8 -> {
                        System.out.print("Digite o ID do pedido de passagem a ser visualizado: ");
                        int passagemIdVisualizar = scanner.nextInt();
                        passagemDAO.visualizarPedido(passagemIdVisualizar);
                    }
                    case 9 -> {
                        Viagem viagemEmissao = new Viagem();
                        if (viagemDAO.EmitirViagem(viagemEmissao)) {
                            System.out.println("Viagem emitida com sucesso.");
                        } else {
                            System.out.println("Erro ao emitir a viagem.");
                        }
                    }
                    case 10 -> {
                        System.out.print("Digite o ID da viagem a ser excluída: ");
                        int viagemIdExcluir = scanner.nextInt();
                        if (viagemDAO.ExcluirViagem(viagemIdExcluir)) {
                            System.out.println("Viagem excluída com sucesso.");
                        } else {
                            System.out.println("Erro ao excluir a viagem.");
                        }
                    }
                    case 11 -> {
                        Viagem viagemAtualizacao = new Viagem();
                        if (viagemDAO.AtualizarViagem(viagemAtualizacao)) {
                            System.out.println("Viagem atualizada com sucesso.");
                        } else {
                            System.out.println("Erro ao atualizar a viagem.");
                        }
                    }
                    case 0 -> System.out.println("Saindo do programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (choice != 0);
        }
    }
}
