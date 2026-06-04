import java.sql.*;

public class PessoaDAO {
    public void inserir(Pessoa p) throws Exception {
        String sql = "INSERT INTO pessoa VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getCpf());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getEstadocivil());
            pstmt.setDate(4, p.getNascimento());
            pstmt.setInt(5, p.getAltura_cm());
            pstmt.setDouble(6, p.getPeso_kg());

            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Sucesso! Linhas inseridas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public void atualizar(Pessoa p) throws Exception {
        String sql = "UPDATE pessoa SET nome = ?, estadocivil = ?, nascimento = ?, altura_cm = ?, peso_kg = ? WHERE cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNome());
            pstmt.setString(2, p.getEstadocivil());
            pstmt.setDate(3, p.getNascimento());
            pstmt.setInt(4, p.getAltura_cm());
            pstmt.setDouble(5, p.getPeso_kg());
            pstmt.setString(6, p.getCpf());

            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Sucesso! Linhas atualizadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public Pessoa excluir(String cpf) throws Exception {
        String sql = "DELETE FROM pessoa WHERE cpf = ? RETURNING *";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Pessoa p = new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("estadocivil"),
                    rs.getDate("nascimento"),
                    rs.getInt("altura_cm"),
                    rs.getDouble("peso_kg")
                );
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
        return null;
    }

    public Pessoa buscar(String cpf) throws Exception {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Pessoa p = new Pessoa(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("estadocivil"),
                        rs.getDate("nascimento"),
                        rs.getInt("altura_cm"),
                        rs.getDouble("peso_kg")
                    );
                    return p;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }

        return null;
    }

    public java.util.List<Pessoa> listar( int limit) throws Exception {
        String sql = "SELECT * FROM pessoa";
        java.util.List<Pessoa> pessoas = new java.util.ArrayList<>();

        try (Connection conn = Conexao.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setMaxRows(limit);

            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pessoa p = new Pessoa(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("estadocivil"),
                    rs.getDate("nascimento"),
                    rs.getInt("altura_cm"),
                    rs.getDouble("peso_kg")
                );
                pessoas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return pessoas;
    }
}
