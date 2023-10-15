import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViagemDAO {
    public boolean EmitirViagem(Viagem passagem) {
        String sql = "INSERT INTO Passagem (Viagemid, Usuarioid) VALUES (?, ?)";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, passagem.getViagemId());
            preparedStatement.setInt(2, passagem.getUsuarioId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.err.println("Pedido de passagem não foi emitido.");
            } else {
                System.out.println("Viagem emitida com sucesso.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao emitir a viagem: " + e.getMessage());
        }
        return false; 
    }

    public boolean ExcluirViagem(int passagemId) {
        String sql = "DELETE FROM Passagem WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, passagemId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Viagem com ID " + passagemId + " foi excluída com sucesso.");
                return true; 
            } else {
                System.err.println("Nenhuma viagem encontrada com o ID " + passagemId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir a viagem: " + e.getMessage());
        }
        return false; 
    }

    public boolean AtualizarViagem(Viagem passagem) {
        String sql = "UPDATE Passagem SET Viagemid = ?, Usuarioid = ? WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, passagem.getViagemId());
            preparedStatement.setInt(2, passagem.getUsuarioId());
            preparedStatement.setInt(3, passagem.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Viagem com ID " + passagem.getId() + " atualizada com sucesso.");
                return true; 
            } else {
                System.err.println("Nenhuma viagem encontrada com o ID " + passagem.getId());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a viagem: " + e.getMessage());
        }
        return false; 
    }

    public Viagem VisualizarViagem(int passagemId) {
        String sql = "SELECT Id, Viagemid, Usuarioid FROM Passagem WHERE Id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, passagemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Viagem passagem = new Viagem();
                passagem.setId(resultSet.getInt("Id"));
                passagem.setViagemId(resultSet.getInt("Viagemid"));
                passagem.setUsuarioId(resultSet.getInt("Usuarioid"));
                return passagem; 
            } else {
                System.err.println("Nenhuma viagem encontrada com o ID " + passagemId);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao visualizar a viagem: " + e.getMessage());
        }
        return null; 
    }
}
