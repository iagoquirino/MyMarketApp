package br.com.mymarket.delegates;

import br.com.mymarket.MyMarketApplication;

public interface BuscaInformacaoDelegate {
    MyMarketApplication getMyMarketApplication();
    void processaResultado(Class clazz,Object obj);
    void processarException(Exception e);
}
