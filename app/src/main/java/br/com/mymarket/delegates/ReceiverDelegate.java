package br.com.mymarket.delegates;

import android.content.Context;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.enuns.HttpMethod;

public interface ReceiverDelegate {
	public ReceiverDelegate registraObservador(BuscaInformacaoDelegate delegate);
	public void processaResultado(Context context, Object resultado, boolean sucesso);
	public void desregistra(MyMarketApplication application);
}
