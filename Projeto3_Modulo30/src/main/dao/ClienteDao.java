package main.dao;

import main.domain.Cliente;
import main.dao.generic.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends GenericDao<Cliente> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO TB_CLIENTE (id, nome, cpf, tel, endereco, numero, cidade, estado, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Cliente cliente) throws SQLException {
        stmt.setLong(1, cliente.getId());
        stmt.setString(2, cliente.getNome());
        stmt.setLong(3, cliente.getCpf());
        stmt.setLong(4, cliente.getTel());
        stmt.setString(5, cliente.getEnd());
        stmt.setInt(6, cliente.getNumero());
        stmt.setString(7, cliente.getCidade());
        stmt.setString(8, cliente.getEstado());
        stmt.setString(9, cliente.getEmail());
    }

    @Override
    protected Cliente extractEntity(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getLong("cpf"));
        cliente.setTel(rs.getLong("tel"));
        cliente.setEnd(rs.getString("endereco"));
        cliente.setNumero(rs.getInt("numero"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setEstado(rs.getString("estado"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM TB_CLIENTE");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(extractEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
