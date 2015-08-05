package br.com.mymarket.helpers;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.mymarket.R;
import br.com.mymarket.enuns.StatusCompra;
import br.com.mymarket.model.Grupo;
import br.com.mymarket.model.ListaCompra;


public class FormularioListaCompraHelper {

	private EditText nome;
	private Button botao;
	private Spinner combo;
	
	private ListaCompra listaCompra;
	
	public FormularioListaCompraHelper(View view){
		nome = (EditText)view.findViewById(R.id.form_list_compras_nome);
		combo = (Spinner)view.findViewById(R.id.form_list_grupos);
		botao = (Button)view.findViewById(R.id.btn_form_lista_compras);
		listaCompra = new ListaCompra();
	}


	public void colocarListaComprasNoFormulario(ListaCompra listaCompra) {
		if(listaCompra != null)
		{
			nome.setText(listaCompra.getNome());
			combo.setSelection(((ArrayAdapter)combo.getAdapter()).getPosition(listaCompra.getGrupo()));
			this.listaCompra = listaCompra;
			botao.setText("Confirmar");
		}else{
			this.listaCompra.setStatusCompra(StatusCompra.ABERTO);
			botao.setText("Inserir");
		}
	}
	
	public ListaCompra recuperarListaCompra(){
		listaCompra.setNome(nome.getText().toString());
		listaCompra.setGrupo((Grupo)combo.getSelectedItem());
		return listaCompra;
	}

}
