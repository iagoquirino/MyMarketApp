package br.com.mymarket.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ActionMode;
import br.com.mymarket.R;
import br.com.mymarket.adapters.PessoaAdapter;
import br.com.mymarket.constants.Constants;
import br.com.mymarket.delegates.BuscaInformacaoDelegate;
import br.com.mymarket.infra.ActionModePessoaCallback;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.navegacao.EstadoPessoaActivity;
import br.com.mymarket.receivers.PessoaReceiver;
import br.com.mymarket.tasks.RecuperarContatosTask;


public class PessoaActivity extends AppBaseActivity implements BuscaInformacaoDelegate{
	
	private EstadoPessoaActivity estado;
	private ActionMode mActionMode;
	private PessoaAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBaseActivityReceiver();
        this.estado = EstadoPessoaActivity.INICIO;
        this.mActionMode = startActionMode(new ActionModePessoaCallback(this));
        this.event = new PessoaReceiver().registraObservador(this);
	}

	public void alteraEstadoEExecuta(EstadoPessoaActivity estado) {
        this.estado = estado;
        this.estado.executa(this);
	}
	
    @Override
    public void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
        MyLog.i("SALVANDO ESTADO!!");
        outState.putSerializable(Constants.ESTADO_ATUAL,this.estado);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        MyLog.i("RESTAURANDO ESTADO!!");
        this.estado = (EstadoPessoaActivity) savedInstanceState.getSerializable(Constants.ESTADO_ATUAL);
    }

    @Override
    public void onResume(){
        super.onResume();
        MyLog.i("EXECUTANDO ESTADO!!" + this.estado);
        this.estado.executa(this);
    }

	@Override
	public void onBackPressed() {
		MyLog.i("FECHAR PESSOA ACTIVITY");
		if(getActionMode() != null){
			resetActionMode();
			finish();
		}		
		super.onBackPressed();
	}
	
	public ActionMode getActionMode(){
		return this.mActionMode;
	}
	
	public void resetActionMode() {
		this.mActionMode = null;
	}

	public PessoaAdapter getAdapter() {
		return this.adapter;
	}

	public void buscarContatosAplicativo() {
		new RecuperarContatosTask(getMyMarketApplication(), event).execute();
	}

	@Override
	public void processaResultado(Object obj) {
    	List<Pessoa> listas = (List<Pessoa>) obj;
    	this.adapter = new PessoaAdapter(this, listas);
    	alteraEstadoEExecuta(EstadoPessoaActivity.LISTAGEM);
	}

	public void adicionarContatos(List<Pessoa> list) {
    	MyLog.i("SEND RESULT ACTIVITY - "+ list.size());
		Intent returnIntent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList("result", (ArrayList<? extends Parcelable>) list); // Be sure con is not null here
		returnIntent.putExtras(bundle);
		setResult(RESULT_OK,returnIntent);
		finish();
	}
	
	public void setarContatosSelecionados(Pessoa pessoa, int posicao) {
		if(getActionMode() != null){
			onListItemSelect(pessoa,posicao);
		}
	}
	private void onListItemSelect(Pessoa pessoa, int posicao) {
		getAdapter().toggleSelection(posicao);
		boolean hasCheckedItems = getAdapter().getSelectedCount() > 0;
		if (hasCheckedItems && getActionMode() == null){
			this.mActionMode = startActionMode(new ActionModePessoaCallback(this));
		}
		if (getActionMode() != null)
		{
			String selecionado = getAdapter().getSelectedCount() <= 1 ? (String)getString(R.string.comum_selecionado) : (String)getString(R.string.comum_selecionados);
			getActionMode().setTitle(String.valueOf(getAdapter().getSelectedCount()) + " " + selecionado);
		}
	}
}
