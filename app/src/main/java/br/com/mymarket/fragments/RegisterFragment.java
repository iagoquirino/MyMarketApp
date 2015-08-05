package br.com.mymarket.fragments;

import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import br.com.mymarket.R;
import br.com.mymarket.activities.MainActivity;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.utils.MaskWatcher;

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);

        TextView tv = (TextView)view.findViewById(R.id.japossuicadastro);
        tv.setPaintFlags(tv.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

		EditText cellPhone = (EditText) view.findViewById(R.id.oat_cellphone);
		cellPhone.addTextChangedListener(new MaskWatcher("(###) #####-####", cellPhone));
        return view;
    }

}
