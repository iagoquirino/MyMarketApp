package br.com.mymarket.model;

import java.io.Serializable;
import java.util.Calendar;

import br.com.mymarket.enuns.StatusProduto;

public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2914286244585036243L;

	private Long id;
	
	private String nome;
	
	private String marca;
	
	private Integer quantidade;
	
	private StatusProduto statusProduto;
	
	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;

	public Produto(String nome, StatusProduto statusProduto) {
		this.nome = nome;
		this.statusProduto = statusProduto;
	}

	public Produto() {
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
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public StatusProduto getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(StatusProduto statusProduto) {
		this.statusProduto = statusProduto;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public boolean isComprado() {
		return StatusProduto.COMPRADO.equals(this.getStatusProduto());
	}
	
}
