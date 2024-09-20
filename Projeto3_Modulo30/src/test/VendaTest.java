package test;

import main.domain.Produto;
import main.domain.ProdutoQuantidade;
import main.domain.Venda;
import main.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class VendaTest {

    private Venda venda;
    private Produto produto;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        venda = new Venda();
        produto = new Produto();
        produto.setCodigo("123");
        produto.setValor(new BigDecimal("10.00"));
        cliente = new Cliente();
        cliente.setId(1L);
        venda.setCliente(cliente);
    }

    @Test
    public void testAdicionarProduto() {
        venda.adicionarProduto(produto, 2);
        assertEquals(1, venda.getProdutos().size());
        assertEquals(2, venda.getQuantidadeTotalProdutos());
        assertEquals(new BigDecimal("20.00"), venda.getValorTotal());
    }

    @Test
    public void testRemoverProduto() {
        venda.adicionarProduto(produto, 2);
        venda.removerProduto(produto, 1);
        assertEquals(1, venda.getQuantidadeTotalProdutos());
        assertEquals(new BigDecimal("10.00"), venda.getValorTotal());
    }

    @Test
    public void testRemoverTodosProdutos() {
        venda.adicionarProduto(produto, 2);
        venda.removerTodosProdutos();
        assertEquals(0, venda.getQuantidadeTotalProdutos());
        assertEquals(BigDecimal.ZERO, venda.getValorTotal());
    }

    @Test
    public void testRecalcularValorTotalVenda() {
        venda.adicionarProduto(produto, 2);
        Produto outroProduto = new Produto();
        outroProduto.setCodigo("456");
        outroProduto.setValor(new BigDecimal("15.00"));
        venda.adicionarProduto(outroProduto, 1);
        assertEquals(new BigDecimal("35.00"), venda.getValorTotal());
    }

    @Test
    public void testValidarStatus() {
        venda.setStatus(Venda.Status.CONCLUIDA);
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            venda.adicionarProduto(produto, 1);
        });
        assertEquals("IMPOSSÍVEL ALTERAR VENDA FINALIZADA", exception.getMessage());
    }


    @Test
    public void testSetAndGetCliente() {
        venda.setCliente(cliente);
        assertEquals(cliente, venda.getCliente());
    }

    @Test
    public void testSetAndGetDataVenda() {
        Instant now = Instant.now();
        venda.setDataVenda(now);
        assertEquals(now, venda.getDataVenda());
    }

    @Test
    public void testSetAndGetStatus() {
        venda.setStatus(Venda.Status.INICIADA);
        assertEquals(Venda.Status.INICIADA, venda.getStatus());
    }
}

