package main.dao;

import main.dao.generic.GenericDao;
import main.domain.Produto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends GenericDao<Produto> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO TB_PRODUTO (id, codigo, nome, descricao, valor, categoria) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Produto produto) throws SQLException {
        stmt.setLong(1, produto.getId());
        stmt.setString(2, produto.getCodigo());
        stmt.setString(3, produto.getNome());
        stmt.setString(4, produto.getDescricao());
        stmt.setBigDecimal(5, produto.getValor());
        stmt.setString(6, produto.getCategoria());
    }

    @Override
    protected Produto extractEntity(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setValor(rs.getBigDecimal("valor"));
        produto.setCategoria(rs.getString("categoria"));
        return produto;
    }

    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM TB_PRODUTO");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(extractEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
}
