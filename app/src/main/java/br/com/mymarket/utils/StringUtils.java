package br.com.mymarket.utils;

import android.text.TextUtils;

public class StringUtils {
	
    public static String replaceCharacters(String cpf)
    {
        if(cpf == null)
            return "";
        return cpf.replaceAll("[^0-9]", "");
    }


    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
