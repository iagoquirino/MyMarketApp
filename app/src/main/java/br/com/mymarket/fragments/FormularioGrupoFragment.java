package br.com.mymarket.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.mymarket.R;
import br.com.mymarket.activities.GrupoActivity;
import br.com.mymarket.activities.PessoaActivity;
import br.com.mymarket.adapters.FormGrupoContatoAdapter;
import br.com.mymarket.enuns.HttpMethod;
import br.com.mymarket.helpers.FormularioGrupoHelper;
import br.com.mymarket.model.Grupo;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.navegacao.EstadoGrupoActivity;
import br.com.mymarket.tasks.PersistObjectTask;

public class FormularioGrupoFragment extends Fragment {
	
	private FormularioGrupoHelper formularioGrupoHelper;
	private FormGrupoContatoAdapter formAdapter;
//	private ContatosAdapter contatosAdapter;
	private GrupoActivity activity;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_form_grupo, container, false);
    	activity = ((GrupoActivity)this.getActivity());
    	
    	formularioGrupoHelper = new FormularioGrupoHelper(view);
    	formularioGrupoHelper.colocarGrupoNoFormulario(activity.getItemSelecionado());
    	Button button = (Button)view.findViewById(R.id.btn_form);
    	
    	formAdapter = new FormGrupoContatoAdapter(activity, formularioGrupoHelper.getIntegrantes());
//    	contatosAdapter = new ContatosAdapter(activity,R.layout.listview_contatos, contatos);
    	
    	ListView listContatoAdd = (ListView)view.findViewById(R.id.contatos_grupo);
    	listContatoAdd.setAdapter(formAdapter);
    	
    	final Button buttonContatos = (Button) view.findViewById(R.id.search_contatos);
    	buttonContatos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Grupo grupo = formularioGrupoHelper.recuperarGrupo();
				grupo.setIntegrantes(formAdapter.getLista());
				activity.setItemSelecionado(grupo);
				Intent intent = new Intent(activity, PessoaActivity.class);
				activity.startActivityForResult(intent, 1);
			}
		});
    	
    	
    	listContatoAdd.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,long id) {
				Pessoa pessoaRemove = (Pessoa)adapter.getItemAtPosition(posicao);
				formAdapter.remove(pessoaRemove);
//				contatosAdapter.remove(pessoaRemove);
			}
		});
    	
    	button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Grupo grupo = formularioGrupoHelper.recuperarGrupo();
				//grupo.setIntegrantes(formAdapter.getLista());
				if(validarGrupo(grupo)){
                    String json = new Gson().toJson(grupo);

					if(activity.getItemSelecionado() == null){
                        new PersistObjectTask(activity,json, HttpMethod.POST).execute();
					}else{
                        new PersistObjectTask(grupo.getId(),activity,json,HttpMethod.PUT).execute();
					}
				}
			}
			
			private boolean validarGrupo(Grupo grupo) {
				if(grupo.getNome() == null || grupo.getNome().isEmpty()){
					Toast.makeText(activity, getString(R.string.form_grupo_validar_nome), Toast.LENGTH_SHORT).show();
					return false;
				}
				if(grupo.getIntegrantes() == null || grupo.getIntegrantes().isEmpty()){
					Toast.makeText(activity, getString(R.string.form_grupo_validar_integrantes), Toast.LENGTH_SHORT).show();
					//return false;
				}
				return true;
			}
		});
        return view;
    }

}
