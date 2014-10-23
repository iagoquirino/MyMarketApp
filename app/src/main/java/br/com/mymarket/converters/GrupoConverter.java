package br.com.mymarket.converters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.mymarket.model.Grupo;
import br.com.mymarket.model.ListaCompra;
import br.com.mymarket.utils.DateUtils;

public class GrupoConverter {
	   public List<Grupo> convert(String json) {
	        try {
                Type listType = new TypeToken<ArrayList<Grupo>>() {}.getType();
                return  new Gson().fromJson(json, listType);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
