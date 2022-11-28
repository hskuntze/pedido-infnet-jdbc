package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Cliente;

public class ClienteDAOTest {
	
	private Long index;
	private ClienteDAO dao;
	
	@Before
	public void setup() {
		dao = new ClienteDAO();
		index = dao.obterTodos().get(0).getCodigo();
	}
	
	@Test
	public void getShouldBeSuccessful() {
		Cliente cliente = dao.obter(index+1L);
		Assert.assertNotNull(cliente);
	}
	
	@Test
	public void getAllShouldBeSuccessful() {
		List<Cliente> lista = dao.obterTodos();
		Assert.assertTrue(lista.size() > 0);
		Assert.assertNotNull(lista);
	}

	@Test
	public void insertShouldBeSuccessful() {
		Cliente obj = new Cliente("Mário Roberto");
		boolean valid = dao.salvar(obj);
		Assert.assertTrue(valid);
	}

	@Test
	public void updateShouldBeSuccessful() {
		Cliente obj = new Cliente("Maria Roberta");
		boolean valid = dao.atualizar(index, obj);
		Assert.assertTrue(valid);
	}
	
	@Test
	public void deleteShouldBeSuccessful() {
		boolean valid = dao.deletar(index);
		Assert.assertTrue(valid);
	}
}
