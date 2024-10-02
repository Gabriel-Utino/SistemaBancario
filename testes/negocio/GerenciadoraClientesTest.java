package negocio;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GerenciadoraClientesTest {
	
	private GerenciadoraClientes gerClientes;
	private int idCLiente01 = 1;
	private int idCLiente02 = 2;
	
	@Before
	public void setUp() {
		Cliente cliente01 = new Cliente(idCLiente01, "João", 31, "joao@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCLiente02, "Maria", 34, "maria@gmail.com", 1, true);
		
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}
	
	@Test
	public void testPesquisaCliente() {
		Cliente cliente = gerClientes.pesquisaCliente(idCLiente01);
		
		assertThat(cliente.getId(), is(idCLiente01));
		
	}
	
	@Test
	public void testRemoveCliente() {
		boolean clienteRemovido = gerClientes.removeCliente(idCLiente02);
		
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCLiente02));
	}
    @Test
    public void testIdadeValidaLimiteInferior() {
        Cliente cliente = new Cliente(1, "Teste", 18, "teste@gmail.com", 1, true);
        assertTrue(cliente.getIdade() >= 18 && cliente.getIdade() <= 65);
    }

    @Test
    public void testIdadeInvalidaLimiteInferior() {
        Cliente cliente = new Cliente(2, "Teste", 17, "teste2@gmail.com", 1, true);
        assertFalse(cliente.getIdade() >= 18 && cliente.getIdade() <= 65);
    }

    @Test
    public void testIdadeValidaLimiteSuperior() {
        Cliente cliente = new Cliente(3, "Teste", 65, "teste3@gmail.com", 1, true);
        assertTrue(cliente.getIdade() >= 18 && cliente.getIdade() <= 65);
    }

    @Test
    public void testIdadeInvalidaLimiteSuperior() {
        Cliente cliente = new Cliente(4, "Teste", 66, "teste4@gmail.com", 1, true);
        assertFalse(cliente.getIdade() >= 18 && cliente.getIdade() <= 65);
    }
}
