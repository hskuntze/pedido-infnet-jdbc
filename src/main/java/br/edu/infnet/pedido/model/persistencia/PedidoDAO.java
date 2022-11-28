package br.edu.infnet.pedido.model.persistencia;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.Produto;

public class PedidoDAO extends JdbcDAO<Pedido>{
	
	public PedidoDAO() {
		super();
	}

	@Override
	public Boolean salvar(Pedido obj) {
		String sql = "INSERT INTO pedido (codigo, data, cliente_cod) VALUES (null, ?, ?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(obj.getData()));
			pstm.setLong(2, obj.getCliente().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean atualizar(Long cod, Pedido obj) {
		String sql = "UPDATE pedido SET data = ?, cliente_cod = ? WHERE codigo = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(obj.getData()));
			pstm.setLong(2, obj.getCliente().getCodigo());
			pstm.setLong(3, cod);
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean deletar(Long cod) {
		String sql = "DELETE FROM pedido WHERE codigo = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, cod);
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Pedido obter(Long cod) {
		String sql = "SELECT p.codigo, p.data, p.cliente_cod, c.nome "
				+ " FROM pedido p "
				+ " JOIN cliente c ON c.codigo = p.cliente_cod "
				+ " WHERE p.codigo = ?";
		Pedido pedido = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String clienteNome = rs.getString("nome");
				pedido = new Pedido(codigo, data, new Cliente(clienteNome));
			}
			return pedido;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Pedido> obterTodos() {
		String sql = "SELECT p.codigo, p.data, c.nome, pr.descricao, pr.preco FROM pedido p"+
						" JOIN cliente c "+
						" JOIN produto_pedido i "+
						" JOIN produto pr "+
						" ON p.cliente_cod = c.codigo "+
						" AND p.codigo = i.codigo_pedido "+
						" AND pr.codigo = i.codigo_produto";
		Map<Long, Pedido> pedidos = new TreeMap<>();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String desc = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Pedido p = null;
				if(pedidos.get(codigo) == null) {
					p = new Pedido(codigo, data, new Cliente(nome));
					p.setItens(new ArrayList<>());
					pedidos.put(codigo, p);
				}
				Produto pr = new Produto(desc, preco);
				p = pedidos.get(codigo);
				p.getItens().add(pr);
			}
			return new ArrayList<Pedido>(pedidos.values());
		} catch (SQLException e) {
			e.getMessage();
		}
		return null;
	}
}
