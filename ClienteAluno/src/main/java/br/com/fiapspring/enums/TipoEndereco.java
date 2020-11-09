package br.com.fiapspring.enums;

public enum TipoEndereco {
	RESIDENCIAL(1),
	ENTREGA(2);
	
	private final int valor;
	
	TipoEndereco(int valor) {
		this.valor = valor;
	}

	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}
	
	
	
}
