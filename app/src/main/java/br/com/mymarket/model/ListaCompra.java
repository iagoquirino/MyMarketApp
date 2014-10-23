package br.com.mymarket.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mymarket.enuns.StatusCompra;

public class ListaCompra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 370641475206352884L;

	private Long id;
	
	private String nome;
	
	private Grupo grupo;
	
	private Pessoa dono;
	
	private List<Produto> produto;
	
	private List<Compra> compra;
	
	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;
	
	private StatusCompra statusCompra;


	public ListaCompra(Long id, Calendar dataCriacao, String nome) {
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.nome = nome;
	}

	public ListaCompra() {
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Pessoa getDono() {
		return dono;
	}

	public void setDono(Pessoa dono) {
		this.dono = dono;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
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

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public void setStatusCompra(StatusCompra statusCompra) {
		this.statusCompra = statusCompra;
	}
}
