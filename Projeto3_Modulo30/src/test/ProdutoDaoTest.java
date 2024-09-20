package test;

import main.domain.Produto;
import main.dao.ProdutoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDaoTest {
    private ProdutoDao produtoDao;

    @BeforeEach
    public void setup() {
        produtoDao = new ProdutoDao();
    }

    @Test
    public void testSaveAndFindAll() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setCodigo("P001");
        produto.setNome("Produto A");
        produto.setDescricao("Descrição do Produto A");
        produto.setValor(BigDecimal.valueOf(100.00));
        produto.setCategoria("Categoria A");

        produtoDao.save(produto);
        List<Produto> produtos = produtoDao.findAll();

        assertFalse(produtos.isEmpty());
        assertEquals(1, produtos.size());

        Produto retrievedProduto = produtos.get(0);
        assertEquals("P001", retrievedProduto.getCodigo());
        assertEquals("Produto A", retrievedProduto.getNome());
        assertEquals("Descrição do Produto A", retrievedProduto.getDescricao());
        assertTrue(BigDecimal.valueOf(100.00).compareTo(retrievedProduto.getValor()) == 0);
        assertEquals("Categoria A", retrievedProduto.getCategoria());
    }
}

