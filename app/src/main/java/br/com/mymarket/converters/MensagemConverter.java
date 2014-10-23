package br.com.mymarket.converters;

import org.json.JSONObject;

/**
 * Created by Iago on 23/10/2014.
 */
public class MensagemConverter {
    public String convert(String json) {
        try {
            JSONObject js = new JSONObject(json);
            return js.getString("message");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
