package test;

import main.dao.ClienteDao;
import main.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoTest {

    private ClienteDao clienteDao;

    @BeforeEach
    public void setup() {
        clienteDao = new ClienteDao();
    }

    @Test
    public void testSaveAndFindAll() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf(12345678901L);
        cliente.setTel(987654321L);
        cliente.setEnd("Rua A");
        cliente.setNumero(123);
        cliente.setCidade("Cidade A");
        cliente.setEstado("Estado A");
        cliente.setEmail("joao@example.com");

        clienteDao.save(cliente);

        List<Cliente> clientes = clienteDao.findAll();
        assertFalse(clientes.isEmpty());
        assertEquals(1, clientes.size());

        Cliente retrievedCliente = clientes.get(0);
        assertEquals("João", retrievedCliente.getNome());
        assertEquals(12345678901L, retrievedCliente.getCpf());
        assertEquals(987654321L, retrievedCliente.getTel());
        assertEquals("Rua A", retrievedCliente.getEnd());
        assertEquals(123, retrievedCliente.getNumero());
        assertEquals("Cidade A", retrievedCliente.getCidade());
        assertEquals("Estado A", retrievedCliente.getEstado());
        assertEquals("joao@example.com", retrievedCliente.getEmail());
    }
}
