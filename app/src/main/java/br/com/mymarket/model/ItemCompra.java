package br.com.mymarket.model;

import java.io.Serializable;
import java.util.Calendar;

public class ItemCompra implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5624449590365259853L;

	private Long id;

	private Long idProduto;

	private String marca;

	private String nome;

	public ItemCompra(){
		super();
	}

	public ItemCompra(long idProduto){
		this();
		this.idProduto= idProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
