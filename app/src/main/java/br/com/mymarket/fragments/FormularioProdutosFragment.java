package br.com.mymarket.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import br.com.mymarket.R;
import br.com.mymarket.activities.ProdutosActivity;
import br.com.mymarket.helpers.FormularioProdutosHelper;
import br.com.mymarket.model.Produto;
import br.com.mymarket.navegacao.EstadoProdutosActivity;

public class FormularioProdutosFragment extends Fragment {

	private FormularioProdutosHelper formularioProdutosHelper;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_form_produtos, container, false);
    	final ProdutosActivity activity = ((ProdutosActivity)this.getActivity());
    	formularioProdutosHelper = new FormularioProdutosHelper(view);
    	formularioProdutosHelper.colocarProdutoNoFormulario(activity.getItemSelecionado());
    	Button button = (Button)view.findViewById(R.id.btn_form_produtos);
    	button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Produto produto = formularioProdutosHelper.recuperarProduto();
				if(validarProduto(produto)){
					//TODO PERSIST OR POST OR PUT.
					if(activity.getItemSelecionado() == null){
						activity.persiste(produto);
						activity.toastInserido();
					}else{
						activity.persiste(produto);
						activity.toastInserido();
					}					
					activity.alteraEstadoEExecuta(EstadoProdutosActivity.CADASTRAR);
				}
			}

			private boolean validarProduto(Produto produto) {
				if(produto.getNome() == null || produto.getNome().isEmpty()){
					Toast.makeText(activity, getString(R.string.form_produtos_validar_nome), Toast.LENGTH_SHORT).show();
					return false;
				}
				return true;
			}
		});
        return view;
    }
}
