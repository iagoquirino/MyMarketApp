package br.com.mymarket.tasks;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;

import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.converters.TokenConverter;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.utils.StringUtils;
import br.com.mymarket.webservice.WebClient;

/**
 * Created by Iago on 30/07/2015.
 */
public class RegisterTask  extends AsyncTask<String, Void, String> {

    private MyMarketApplication application;
    private ReceiverDelegate evento;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public RegisterTask(MyMarketApplication application, ReceiverDelegate evento) {
        MyLog.i("RegisterTask() " + application.getPackageName());
        this.application = application;
        this.evento = evento;
        this.application.registra(this);
    }

    private String getPessoaJson(String nome, String celular, String email, String password, String deviceId)
    {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setSenha(password);
        pessoa.setCelular(celular);
        pessoa.setDeviceId(deviceId);
        return new Gson().toJson(pessoa);
    }


    @Override
    protected String doInBackground(String... params) {
        String nome = params[0];
        String celular = params[1];
        String email = params[2];
        String password = params[3];
        String deviceId = params[4];

       String json = new WebClient("oauth/registrar",this.application).post(getPessoaJson(nome,celular,email,password,deviceId),true);
       return new TokenConverter().convert(json);
    }


    @Override
    protected void onPostExecute(String token) {
        MyLog.i("OBTIDO MEU TOKEN!");
        if (token!=null) {
            evento.processaResultado(this.application,token,true);
        } else {
            evento.processaResultado(this.application,token,false);
        }
        this.application.desregistra(this);
    }

}
