package br.edu.infnet.pedido.model.entidade;

import java.time.LocalDate;
import java.util.List;

public class Pedido {

	private Long codigo;
	private LocalDate data;
	private Cliente cliente;
	private List<Produto> itens;
	
	public Pedido() {
	}

	public Pedido(Cliente cliente, List<Produto> itens) {
		super();
		this.data = LocalDate.now();
		this.cliente = cliente;
		this.itens = itens;
	}
	
	public Pedido(Long codigo, LocalDate data, Cliente cliente) {
		this.codigo = codigo;
		this.data = data;
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", data=" + data + ", cliente=" + cliente + ", itens=" + itens + "]";
	}
}
