package br.com.mymarket.receivers;

import java.io.Serializable;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.constants.Constants;
import br.com.mymarket.delegates.BuscaInformacaoDelegate;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.exception.MyMarketException;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.model.Produto;

public class PessoaReceiver extends BroadcastReceiver implements ReceiverDelegate{

    private BuscaInformacaoDelegate delegate;
    
    public static final String RESULTADO_PESSOA = "resultadoPessoa";
    public static final String PESSOA_RECEBIDO = "Pessoas Recebido";
    public static final String PESSOA_PARAM = "pessoas";    

    public PessoaReceiver registraObservador(BuscaInformacaoDelegate delegate){
    	PessoaReceiver receiver = new PessoaReceiver();
        receiver.delegate = delegate;
        LocalBroadcastManager.getInstance(delegate.getMyMarketApplication()).registerReceiver(receiver,new IntentFilter(PESSOA_RECEBIDO));
        return receiver;
    }

    public void processaResultado(Context context,Object obj, boolean sucesso){
    	List<Pessoa> resultado = (List<Pessoa>) obj;
        Intent intent = new Intent(PESSOA_RECEBIDO);
        intent.putExtra(RESULTADO_PESSOA,(Serializable) resultado);
        intent.putExtra(Constants.SUCESSO,sucesso);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void desregistra(MyMarketApplication application){
        LocalBroadcastManager.getInstance(application).unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getBooleanExtra(Constants.SUCESSO,false) == true){
        	delegate.processaResultado(Pessoa.class,(List < Pessoa >) intent.getSerializableExtra(RESULTADO_PESSOA));
        }else{
        	delegate.processarException(new MyMarketException());
        }
    }
}