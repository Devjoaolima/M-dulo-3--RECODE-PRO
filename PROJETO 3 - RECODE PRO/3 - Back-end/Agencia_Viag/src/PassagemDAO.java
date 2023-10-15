import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassagemDAO {
    public void RealizarCadastro(Passagem viagem) {
        String sql = "INSERT INTO Viagem (Preco, Taxas, Destino) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, viagem.getPreco());
            preparedStatement.setFloat(2, viagem.getTaxas());
            preparedStatement.setString(3, viagem.getDestino());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Cadastro de viagem não foi realizado.");
            } else {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int novoId = generatedKeys.getInt(1);
                        viagem.setId(novoId); // Atualiza o ID da viagem com o ID gerado pelo banco de dados
                        System.out.println("Cadastro de viagem realizado com sucesso. Novo ID: " + novoId);
                    } else {
                        System.err.println("Erro ao obter o ID gerado para a nova viagem.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao realizar o cadastro da viagem: " + e.getMessage());
        }
    }

    public void CancelarViagem(int viagemId) {
        String sql = "DELETE FROM Viagem WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, viagemId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Viagem com ID " + viagemId + " cancelada com sucesso.");
            } else {
                System.err.println("Nenhuma viagem encontrada com o ID " + viagemId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar a viagem: " + e.getMessage());
        }
    }

    public void AtualizarViagem(Passagem viagem) {
        String sql = "UPDATE Viagem SET Preco = ?, Taxas = ?, Destino = ? WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setFloat(1, viagem.getPreco());
            preparedStatement.setFloat(2, viagem.getTaxas());
            preparedStatement.setString(3, viagem.getDestino());
            preparedStatement.setInt(4, viagem.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Viagem com ID " + viagem.getId() + " atualizada com sucesso.");
            } else {
                System.err.println("Nenhuma viagem encontrada com o ID " + viagem.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a viagem: " + e.getMessage());
        }
    }

    public boolean VisualizarPedido(int passagemId) {
        String sql = "SELECT Viagemid, Usuarioid FROM Passagem WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, passagemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int viagemId = resultSet.getInt("Viagemid");
                int usuarioId = resultSet.getInt("Usuarioid");
                System.out.println("Detalhes do pedido de passagem com ID " + passagemId + ":");
                System.out.println("Viagem ID: " + viagemId);
                System.out.println("Usuário ID: " + usuarioId);
                return true; 
            } else {
                System.err.println("Nenhum pedido de passagem encontrado com o ID " + passagemId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao visualizar o pedido de passagem: " + e.getMessage());
        }
        return false; 
    }
}
