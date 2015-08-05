package br.com.mymarket.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.lang.reflect.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.mymarket.model.ListaCompra;
import br.com.mymarket.utils.DateUtils;

public class ListaCompraConverter {
	   public List<ListaCompra> convert(String json) {
	        try {
				Type listType = new TypeToken<ArrayList<ListaCompra>>() {}.getType();
				return  new Gson().fromJson(json, listType);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
