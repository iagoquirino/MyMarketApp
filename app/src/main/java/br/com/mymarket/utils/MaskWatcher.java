package br.com.mymarket.utils;

import java.text.ParseException;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MaskWatcher implements TextWatcher {

    private String mMask;
    String mResult = "";
    private EditText editText;
    private boolean increment = false;

    public MaskWatcher(String mask,EditText editText){
        mMask = mask;
        this.editText = editText;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!increment){
            return;
        }
        String mask = mMask;
        String value = s.toString();

        if(value.equals(mResult))
            return;

        try {

            // prepare the formatter
            MaskedFormatter formatter = new MaskedFormatter(mask);
            formatter.setValueContainsLiteralCharacters(false);
            formatter.setPlaceholderCharacter((char)1);

            // get a string with applied mask and placeholder chars
            value = formatter.valueToString(value);

            try{

                // find first placeholder
                value = value.substring(0, value.indexOf((char)1));

                //process a mask char
                if(value.charAt(value.length()-1) == mask.charAt(value.length()-1)){
                    value = value.substring(0, value.length() - 1);
                }

            }
            catch(Exception e){}

            mResult = value;

            editText.setText(mResult);
            //s.replace(0, s.length(), mResult);

        } catch (ParseException e) {

            //the entered value does not match a mask
            int offset = e.getErrorOffset();
            value = removeCharAt(value, offset);

            editText.setText(value);
        }
        editText.setSelection(editText.getText().length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        increment = count > 0;
    }

    public static String removeCharAt(String s, int pos) {

        StringBuffer buffer = new StringBuffer(s.length() - 1);
        buffer.append(s.substring(0, pos)).append(s.substring(pos + 1));
        return buffer.toString();

    }

}
