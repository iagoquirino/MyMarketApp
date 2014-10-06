package br.com.mymarket.tasks;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.infra.Contacts;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.webservice.Pagina;

public class RecuperarContatosTask extends AsyncTask<Pagina, Void, List<Pessoa>> {

	private MyMarketApplication application;
	private ReceiverDelegate event;
	
	public RecuperarContatosTask(MyMarketApplication application,ReceiverDelegate event) {
		MyLog.i("RecuperarContatosTask() " + application.getPackageName());
		this.application = application;
		this.application.registra(this);
		this.event = event;
	}
	

	@Override
	protected List<Pessoa> doInBackground(Pagina... paginas) {
		Contacts contact = new Contacts(this.application);
		contact.setContatos();
		List<Pessoa> contatos = contact.getContacts();
		Collections.sort(contatos);
		return contatos;
	}
	
    @Override
    protected void onPostExecute(List<Pessoa> retorno) {
        MyLog.i("RETORNO OBTIDO LISTA PESSOAS!");
        if (retorno!=null) {
        	this.event.processaResultado(this.application,retorno,true);
        } else {
        	this.event.processaResultado(this.application,retorno,false);
        }
        this.application.desregistra(this);
    }
}
