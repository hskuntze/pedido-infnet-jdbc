package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAOTest {	
	
	private IDAO<Produto> dao;
	private Long index;
	
	@Before
	public void setup() {
		dao = new ProdutoDAO();
		index = dao.obterTodos().get(0).getCodigo();
	}
	
	@Test
	public void getAllShouldBeSuccessful() {
		List<Produto> lista = dao.obterTodos();
		
		Assert.assertTrue(lista.size() > 0);
		Assert.assertNotNull(lista);
		

		System.out.println("\n getAll:");
		System.out.println(lista);
	}
	
	@Test
	public void getShouldBeSuccessful() {
		Produto p = dao.obter(index);
		
		Assert.assertNotNull(p);
		
		System.out.println("\n get:");
		System.out.println(p);
	}
	
	@Test
	public void insertShouldBeSuccessful() {
		Produto p = new Produto("Geléia de Abóbora", 125.59);
		
		boolean verify = dao.salvar(p);
		
		Assert.assertTrue(verify);
		
		System.out.println("\n salvar:");
		System.out.println(p);
	}
	
	@Test
	public void updateShouldBeSuccessful() {
		Produto p = new Produto("Cebola empanada", 15.0);
		
		boolean verify = dao.atualizar(index, p);
		
		Assert.assertTrue(verify);
		
		System.out.println("\n atualizar:");
		System.out.println(p);
	}
	
	@Test
	public void deleteShouldBeSuccessful() {
		boolean verify = dao.deletar(index+3L);
		Assert.assertTrue(verify);
	}
}
