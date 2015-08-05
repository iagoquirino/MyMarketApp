package br.com.mymarket.tasks;

import android.os.AsyncTask;

import br.com.mymarket.activities.AppBaseActivity;
import br.com.mymarket.converters.MensagemConverter;
import br.com.mymarket.enuns.HttpMethod;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.webservice.WebClient;

/**
 * Created by Iago on 23/10/2014.
 */
public class PersistObjectTask extends AsyncTask<String, Void, String> {

    private AppBaseActivity activity;
    private String uri;
    private Long id;
    private String json;
    private HttpMethod httpMethod;

    public PersistObjectTask(AppBaseActivity activity, String json, HttpMethod httpMethod) {
        this.activity = activity;
        this.uri = activity.getUri();
        this.json = json;
        this.httpMethod = httpMethod;
        activity.getMyMarketApplication().registra(this);
    }

    public PersistObjectTask(Long id, AppBaseActivity activity, String json, HttpMethod httpMethod) {
        this.id = id;
        this.activity = activity;
        this.uri = activity.getUri();
        this.json = json;
        this.httpMethod = httpMethod;
        activity.getMyMarketApplication().registra(this);
    }



    @Override
    protected String doInBackground(String... params) {
        String json = this.json;
        String response = "";
        MyLog.i("SEND:"+ json);
        if(this.httpMethod == HttpMethod.POST){
            response = new WebClient(uri,activity.getMyMarketApplication()).post(json, true);
        }else if(this.httpMethod == HttpMethod.PUT){
            response = new WebClient(uri +id.longValue(),activity.getMyMarketApplication()).put(json, true);
        }else if(this.httpMethod == HttpMethod.DELETE){
            response = new WebClient(uri +id.longValue(),activity.getMyMarketApplication()).delete();
        }
        return new MensagemConverter().convert(response);
    }

    @Override
    protected void onPostExecute(String mensagem) {
        if (mensagem!=null) {
            activity.atualizarLista();
            MyLog.i("OBTIDO MENSAGEM!");
            if(HttpMethod.POST == this.httpMethod){
                activity.toastInserido();
            }else if(HttpMethod.PUT == this.httpMethod){
                activity.toastAlterado();
            }else if(HttpMethod.DELETE == this.httpMethod){
                activity.toastExcluido();
            }
        } else {
            activity.toastErro();
        }
        activity.getMyMarketApplication().desregistra(this);
    }

}
