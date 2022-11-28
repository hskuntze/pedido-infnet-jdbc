package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Cliente;

public class ClienteDAO extends JdbcDAO<Cliente> {
	
	public ClienteDAO() {
		super();
	}
	
	@Override
	public Boolean salvar(Cliente cliente) {
		String sql = "INSERT INTO cliente (codigo, nome) VALUES (null, ?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public Boolean atualizar(Long codigo, Cliente cliente) {
		String sql = "UPDATE cliente SET nome = ? WHERE codigo = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cliente.getNome());
			pstm.setLong(2, codigo);
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public Boolean deletar(Long codigo) {
		String sql = "DELETE FROM cliente WHERE codigo = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, codigo);
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public Cliente obter(Long codigo) {
		String sql = "SELECT * FROM cliente WHERE codigo = ?";
		Cliente cliente = new Cliente();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String nome = rs.getString("nome");
				Long id = rs.getLong("codigo");
				cliente = new Cliente(id, nome);
			}
			return cliente;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Cliente> obterTodos(){
		String sql = "SELECT * FROM cliente";
		List<Cliente> lista = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String nome = rs.getString("nome");
				Cliente c = new Cliente(codigo, nome);
				lista.add(c);
			}
			return lista;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
}
