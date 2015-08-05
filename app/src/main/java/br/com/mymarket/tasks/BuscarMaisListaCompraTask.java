package br.com.mymarket.tasks;

import java.util.List;

import android.os.AsyncTask;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.converters.GrupoConverter;
import br.com.mymarket.converters.ListaCompraConverter;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.mocks.ListaComprasMocks;
import br.com.mymarket.model.ListaCompra;
import br.com.mymarket.webservice.Pagina;
import br.com.mymarket.webservice.WebClient;

public class BuscarMaisListaCompraTask extends AsyncTask<Pagina, Void, List<ListaCompra>> {

    private Exception erro;
    //private BuscaMaisPostsDelegate delegate;
    private MyMarketApplication application;
    private ReceiverDelegate evento;
    
    public BuscarMaisListaCompraTask(MyMarketApplication application,ReceiverDelegate evento){
        this.application = application;
        this.application.registra(this);
        this.evento = evento;
    }
	
    @Override
    protected List<ListaCompra> doInBackground(Pagina... paginas) {
        try {
            String jsonDeResposta = new WebClient("listaCompras/",this.application).get();
            return new ListaCompraConverter().convert(jsonDeResposta);
        } catch (Exception e) {
            this.erro = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<ListaCompra> retorno) {
        MyLog.i("RETORNO OBTIDO LISTA COMPRAS!");

        if (retorno!=null) {
        	this.evento.processaResultado(this.application,retorno,true);
        } else {
        	this.evento.processaResultado(this.application,retorno,false);
        }

        this.application.desregistra(this);
    }

}
