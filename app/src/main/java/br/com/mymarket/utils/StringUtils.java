package br.com.mymarket.utils;

public class StringUtils {
	
    public static String replaceCharacters(String cpf)
    {
        if(cpf == null)
            return "";
        return cpf.replaceAll("[^0-9]", "");
    }
}
