package br.com.mymarket.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.mymarket.R.string;

public class Grupo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1640000875868104722L;

	private Long id;
	
	private String nome;
	
	private List<Pessoa> integrantes;
	
	private String dataCobranca;
	
	private String dataCriacao;
	
	private String dataAlteracao;

    public Grupo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

	public Grupo(String nome) {
		this.nome = nome;
	}

	public Grupo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pessoa> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Pessoa> integrantes) {
		this.integrantes = integrantes;
	}

	public String getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(String dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	
	public String toString()
	{
		return this.getNome();
	}
}
