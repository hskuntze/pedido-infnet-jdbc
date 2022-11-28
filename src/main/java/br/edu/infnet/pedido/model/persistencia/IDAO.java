package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

public interface IDAO<T> {
	Boolean salvar(T obj);
	Boolean atualizar(Long cod, T obj);
	Boolean deletar(Long cod);
	T obter(Long cod);
	List<T> obterTodos();
}
