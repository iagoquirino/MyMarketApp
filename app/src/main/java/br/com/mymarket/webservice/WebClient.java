package br.com.mymarket.webservice;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Application;

import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.infra.MyServer;


public class WebClient {
	
    private final String url;

    public WebClient(String relativeUrl,MyMarketApplication application) {
        this.url = new MyServer(application).uriFor(relativeUrl+"?token="+application.getToken());
    }

    public String get() {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            MyLog.i("EFETUANDO O GET PARA URL: " + this.url);
            get.setHeader("Accept", "application/json");
            get.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(get);
            String jsonDeResposta = EntityUtils.toString(response.getEntity());
            MyLog.i("RESPOSTA: " + jsonDeResposta);
            return jsonDeResposta;
        } catch (Exception e) {
            MyLog.e("ERRO: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public String delete() {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpDelete delete = new HttpDelete(url);
            MyLog.i("EFETUANDO O GET PARA URL: " + this.url);
            delete.setHeader("Accept", "application/json");
            delete.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(delete);
            String jsonDeResposta = EntityUtils.toString(response.getEntity());
            MyLog.i("RESPOSTA: " + jsonDeResposta);
            return jsonDeResposta;
        } catch (Exception e) {
            MyLog.e("ERRO: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String post() {
        return post(null,false);
    }
    
    public String post(String json,boolean isJson) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            MyLog.i("EFETUANDO O POST PARA URL: " + this.url + " BODY: " + json);
            if (json != null) {
                post.setEntity(new StringEntity(json));
                if(isJson){
                	post.setHeader("Content-type", "application/json");	
                }
            }
            post.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(post);
            String jsonDeResposta = EntityUtils.toString(response.getEntity());
            MyLog.i("JSON DE REPOSTA: " + jsonDeResposta);
            return jsonDeResposta;
        } catch (Exception e) {
            MyLog.i("ZICA NO POST!: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public String put(String json,boolean isJson) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);
            MyLog.i("EFETUANDO O PUT PARA URL: " + this.url + " BODY: " + json);
            if (json != null) {
                put.setEntity(new StringEntity(json));
                if(isJson){
                    put.setHeader("Content-type", "application/json");
                }
            }
            put.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(put);
            String jsonDeResposta = EntityUtils.toString(response.getEntity());
            MyLog.i("JSON DE REPOSTA: " + jsonDeResposta);
            return jsonDeResposta;
        } catch (Exception e) {
            MyLog.i("ZICA NO PUT!: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}