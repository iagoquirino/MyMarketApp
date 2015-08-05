package br.com.mymarket.tasks;

import java.util.List;

import android.os.AsyncTask;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.converters.GrupoConverter;
import br.com.mymarket.converters.ProdutoConverter;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.mocks.ProdutoMock;
import br.com.mymarket.model.ListaCompra;
import br.com.mymarket.model.Produto;
import br.com.mymarket.webservice.Pagina;
import br.com.mymarket.webservice.WebClient;

public class BuscarProdutosTask extends AsyncTask<Pagina, Void, List<Produto>> {

    private Exception erro;
    private MyMarketApplication application;
    private ListaCompra listaCompra;
    private ReceiverDelegate evento;
    
    public BuscarProdutosTask(MyMarketApplication application,ListaCompra listaCompra,ReceiverDelegate evento){
        this.application = application;
        this.listaCompra = listaCompra;
        this.evento = evento;
        this.application.registra(this);
    }
	
    @Override
    protected List<Produto> doInBackground(Pagina... paginas) {
        try {
            String jsonDeResposta = new WebClient("produtos/"+this.listaCompra.getId(),this.application).get();
            return new ProdutoConverter().convert(jsonDeResposta);
        } catch (Exception e) {
            this.erro = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Produto> retorno) {
        MyLog.i("RETORNO OBTIDO LISTA PRODUTOS!");
        if (retorno!=null) {
            this.evento.processaResultado(this.application,retorno,true);
        } else {
        	this.evento.processaResultado(this.application,retorno,false);
        }
        this.application.desregistra(this);
    }

}
