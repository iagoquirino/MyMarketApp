package br.com.mymarket.helpers;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.mymarket.R;
import br.com.mymarket.model.Produto;

public class FormularioProdutosHelper {

	private EditText nome;
	private EditText marca;
	private EditText quantidade;
	private Button botao;
	private Produto produto;
	
	public FormularioProdutosHelper(View view){
		nome = (EditText)view.findViewById(R.id.form_produtos_nome);
		marca = (EditText)view.findViewById(R.id.form_produtos_marca);
		quantidade = (EditText)view.findViewById(R.id.form_produtos_quantidade);
		botao = (Button)view.findViewById(R.id.btn_form_produtos);
		produto = new Produto();
	}


	public void colocarProdutoNoFormulario(Produto produto) {
		if(produto != null)
		{
			nome.setText(produto.getNome());
			marca.setText(produto.getMarca());
			if(produto.getQuantidade() != null){
				quantidade.setText(produto.getQuantidade().toString());
			}
			botao.setText("Confirmar");
			this.produto = produto;
		}else{
			botao.setText("Inserir");
		}
	}
	
	public Produto recuperarProduto(){
		produto.setNome(nome.getText().toString());
		produto.setMarca(marca.getText().toString());
		if(!quantidade.getText().toString().isEmpty()){
			produto.setQuantidade(Integer.valueOf(quantidade.getText().toString()));
		}
		return produto;
	}
	
}
