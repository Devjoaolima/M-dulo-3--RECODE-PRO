import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public void RealizarCadastro(Cliente cliente) {
        String sql = "INSERT INTO Cliente (Cpf, Idade, Email, Nome, Telefone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setInt(2, cliente.getIdade());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getNome());
            preparedStatement.setString(5, cliente.getTelefone());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Cadastro não foi realizado.");
            } else {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int novoId = generatedKeys.getInt(1);
                        cliente.setId(novoId); // Atualiza o ID do cliente com o ID gerado pelo banco de dados
                        System.out.println("Cadastro realizado com sucesso. Novo ID: " + novoId);
                    } else {
                        System.err.println("Erro ao obter o ID gerado para o novo cadastro.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao realizar o cadastro: " + e.getMessage());
        }
    }

    public void CancelarCadastro(int clienteId) {
        String sql = "DELETE FROM Cliente WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, clienteId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cadastro com ID " + clienteId + " cancelado com sucesso.");
            } else {
                System.err.println("Nenhum registro encontrado com o ID " + clienteId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar o cadastro: " + e.getMessage());
        }
    }

    public void AtualizarCadastro(Cliente cliente) {
        String sql = "UPDATE Cliente SET Cpf = ?, Idade = ?, Email = ?, Nome = ?, Telefone = ? WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setInt(2, cliente.getIdade());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getNome());
            preparedStatement.setString(5, cliente.getTelefone());
            preparedStatement.setInt(6, cliente.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cadastro com ID " + cliente.getId() + " atualizado com sucesso.");
            } else {
                System.err.println("Nenhum registro encontrado com o ID " + cliente.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o cadastro: " + e.getMessage());
        }
    }

    public void visualizarCadastro(int clienteId) {
        String sql = "SELECT Id, Cpf, Idade, Email, Nome, Telefone FROM Cliente WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, clienteId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String cpf = resultSet.getString("Cpf");
                int idade = resultSet.getInt("Idade");
                String email = resultSet.getString("Email");
                String nome = resultSet.getString("Nome");
                String telefone = resultSet.getString("Telefone");

                System.out.println("Detalhes do cadastro do cliente:");
                System.out.println("ID: " + id);
                System.out.println("CPF: " + cpf);
                System.out.println("Idade: " + idade);
                System.out.println("Email: " + email);
                System.out.println("Nome: " + nome);
                System.out.println("Telefone: " + telefone);
            } else {
                System.err.println("Nenhum registro encontrado com o ID " + clienteId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao visualizar o cadastro: " + e.getMessage());
        }
    }
}
