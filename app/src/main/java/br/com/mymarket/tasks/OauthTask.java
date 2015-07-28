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

public class OauthTask extends AsyncTask<String, Void, String> {
	
	private MyMarketApplication application;
    private ReceiverDelegate evento;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	public OauthTask(MyMarketApplication application, ReceiverDelegate evento) {
		MyLog.i("OauthTask() " + application.getPackageName());
        this.application = application;
        this.evento = evento;
        this.application.registra(this);
    }

	@Override
	protected String doInBackground(String... params) {
		String email = params[0];
		String password = params[1];

		if(StringUtils.isValidEmail(email) && !TextUtils.isEmpty(password))
		{
			String json = new WebClient("oauth/login",this.application).post(getPessoaJson(email, password),true);
			return new TokenConverter().convert(json);
		}else{
			return null;
		}
	}

	private String getPessoaJson(String email, String password)
	{
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(email);
		pessoa.setSenha(password);
		return new Gson().toJson(pessoa);
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
