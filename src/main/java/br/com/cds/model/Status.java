package br.com.cds.model;

public enum Status {

	INATIVO("Inativo"),
	ATIVO("Ativo");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
