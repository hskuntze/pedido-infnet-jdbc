package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAO extends JdbcDAO<Produto>{
	
	public ProdutoDAO() {
		super();
	}

	@Override
	public Boolean salvar(Produto obj) {
		String sql = "INSERT INTO produto (codigo, descricao, preco) VALUES (null,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getDescricao());
			pstm.setDouble(2, obj.getPreco());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean atualizar(Long cod, Produto obj) {
		String sql = "UPDATE produto SET descricao = ?, preco = ? WHERE codigo = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getDescricao());
			pstm.setDouble(2, obj.getPreco());
			pstm.setLong(3, cod);
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean deletar(Long cod) {
		String sql = "DELETE FROM produto WHERE codigo = ?";
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
	public Produto obter(Long cod) {
		String sql = "SELECT * FROM produto WHERE codigo = ?";
		Produto p = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setLong(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				Long codigo = rs.getLong("codigo");
				String desc = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				p = new Produto(codigo, desc, preco);
			}
			return p;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Produto> obterTodos() {
		String sql = "SELECT * FROM produto";
		List<Produto> lista = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery(sql);
			while(rs.next()) {
				Long cod = rs.getLong("codigo");
				String desc = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				lista.add(new Produto(cod, desc, preco));
			}
			return lista;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
