package br.edu.infnet.pedido.model.persistencia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;

public class PedidoDAOTest {
	
	private IDAO<Pedido> pDao;
	private IDAO<Cliente> cDao;
	private Cliente c;
	private Long index;
	
	@Before
	public void setup() {
		pDao = new PedidoDAO();
		cDao = new ClienteDAO();
		c = cDao.obterTodos().get(0);
		index = pDao.obterTodos().get(0).getCodigo();
	}
	
	@Test
	public void getAllShouldBeSuccessful() {
		IDAO<Pedido> pedidoDao = new PedidoDAO();
		List<Pedido> lista = pedidoDao.obterTodos();
		
		Assert.assertTrue(lista.size() > 0);
	}
	
	@Test
	public void getShouldBeSuccessful() {
		Pedido p = pDao.obter(index);
		Assert.assertNotNull(p);
	}
	
	@Test
	public void insertShouldBeSuccessful() {
		
		Pedido p = new Pedido(null, LocalDate.now(), c);
		boolean verify = pDao.salvar(p);
		
		Assert.assertTrue(verify);
	}
	
	@Test
	public void updateShouldBeSuccessful() {
		int max, min, range, rd;
		max = 15;
		min = 1;
		range = max - min + 1;
		rd = (int) (Math.random() * range) + min;
		
		Pedido p = new Pedido(null, LocalDate.now().plus(rd, ChronoUnit.DAYS), c);
		boolean verify = pDao.atualizar(index+1L, p);
		
		Assert.assertTrue(verify);
	}
	
//	@Test
//	public void deleteShouldBeSuccessful() {
//		boolean verify = pDao.deletar(15L);
//		
//		Assert.assertTrue(verify);
//	}
}
