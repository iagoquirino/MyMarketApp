package br.com.mymarket.model;

import android.content.ClipData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5149535001513535922L;

	private Long id;
	
	private Pessoa pessoa;
	
	private List<ItemCompra> itens;

	private BigDecimal valor;

	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;

	public Compra(){
		super();
	}

	public Compra(BigDecimal valor, List<ItemCompra> itens){
		this();
		this.valor = valor;
		this.itens = itens;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
