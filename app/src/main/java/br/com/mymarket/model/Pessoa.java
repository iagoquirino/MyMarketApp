package br.com.mymarket.model;

import java.io.Serializable;
import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Pessoa implements Serializable,Parcelable,Comparable<Pessoa>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 467678398697569121L;

	private long id;
	
	private String celular;
	
	private String senha;
	
	private String email;
	
	private String nome;
	
	private Calendar dataCriacao;
	
	private Calendar dataAlteracao;
	
	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String celular) {
		this.nome = nome; 
		this.celular = celular;
	}

	public Pessoa(Parcel source) {
        this.id = source.readLong();
        this.celular = source.readString();
        this.email = source.readString();
        this.nome = source.readString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	  dest.writeLong(this.id);
	  dest.writeString(this.celular);
	  dest.writeString(this.email);
	  dest.writeString(this.nome);
	}

	
   public static final Parcelable.Creator<Pessoa> CREATOR = new Parcelable.Creator<Pessoa>() {
	   
        @Override
        public Pessoa createFromParcel(Parcel source) {
            return new Pessoa(source);
        }
 
        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };

	@Override
	public int compareTo(Pessoa another) {
		return this.nome.compareTo(another.getNome());
	}
	
}
