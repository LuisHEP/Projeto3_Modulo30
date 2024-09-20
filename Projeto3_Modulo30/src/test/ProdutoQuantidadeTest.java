package test;

import main.domain.Produto;
import main.domain.ProdutoQuantidade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoQuantidadeTest {

    private ProdutoQuantidade produtoQuantidade;
    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto();
        produto.setCodigo("123");
        produto.setValor(new BigDecimal("10.00"));

        produtoQuantidade = new ProdutoQuantidade();
        produtoQuantidade.setProduto(produto);
    }

    @Test
    public void testAdicionar() {
        produtoQuantidade.adicionar(2);
        assertEquals(2, produtoQuantidade.getQuantidade());
        assertEquals(new BigDecimal("20.00"), produtoQuantidade.getValorTotal());
    }

    @Test
    public void testRemover() {
        produtoQuantidade.adicionar(2);
        produtoQuantidade.remover(1);
        assertEquals(1, produtoQuantidade.getQuantidade());
        assertEquals(new BigDecimal("10.00"), produtoQuantidade.getValorTotal());
    }

    @Test
    public void testSetAndGetProduto() {
        assertEquals(produto, produtoQuantidade.getProduto());
    }

    @Test
    public void testSetAndGetQuantidade() {
        produtoQuantidade.setQuantidade(5);
        assertEquals(5, produtoQuantidade.getQuantidade());
    }

    @Test
    public void testSetAndGetValorTotal() {
        produtoQuantidade.setValorTotal(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), produtoQuantidade.getValorTotal());
    }

    @Test
    public void testSetAndGetId() {
        produtoQuantidade.setId(1L);
        assertEquals(1L, produtoQuantidade.getId());
    }
}