package br.com.mymarket.helpers;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import br.com.mymarket.R;
import br.com.mymarket.model.ListaCompra;


public class FormularioListaCompraHelper {

	private EditText nome;
	private Button botao;
	
	private ListaCompra listaCompra;
	
	public FormularioListaCompraHelper(View view){
		nome = (EditText)view.findViewById(R.id.form_list_compras_nome);
		botao = (Button)view.findViewById(R.id.btn_form_lista_compras);
		listaCompra = new ListaCompra();
	}


	public void colocarListaComprasNoFormulario(ListaCompra listaCompra) {
		if(listaCompra != null)
		{
			nome.setText(listaCompra.getNome());
			this.listaCompra = listaCompra;
			botao.setText("Confirmar");
		}else{
			botao.setText("Inserir");
		}
	}
	
	public ListaCompra recuperarListaCompra(){
		listaCompra.setNome(nome.getText().toString());
		return listaCompra;
	}

}
