package br.com.mymarket.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.mymarket.model.Grupo;
import br.com.mymarket.model.Produto;

public class ProdutoConverter {
	   public List<Produto> convert(String json) {
	        try {
                Type listType = new TypeToken<ArrayList<Produto>>() {}.getType();
                return  new Gson().fromJson(json, listType);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
