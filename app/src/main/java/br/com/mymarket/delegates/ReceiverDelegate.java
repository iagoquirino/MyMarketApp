package br.com.mymarket.delegates;

import android.content.Context;
import br.com.mymarket.MyMarketApplication;

public interface ReceiverDelegate {
	public ReceiverDelegate registraObservador(BuscaInformacaoDelegate delegate);
	public void processaResultado(Context context, Object resultado, boolean sucesso);
	public void desregistra(MyMarketApplication application);
}
