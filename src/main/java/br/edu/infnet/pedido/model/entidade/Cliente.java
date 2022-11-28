package br.edu.infnet.pedido.model.entidade;

public class Cliente {
	private Long codigo;
	private String nome;
	
	public Cliente() {
	}
	
	public Cliente(String nome) {
		this.nome = nome;
	}

	public Cliente(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + "]";
	}
}
