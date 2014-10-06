package br.com.mymarket.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.mymarket.R;
import br.com.mymarket.activities.MainActivity;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.utils.MaskWatcher;

public class OauthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oauth, null);
		EditText cellPhone = (EditText) view.findViewById(R.id.oat_cellphone);
		cellPhone.addTextChangedListener(new MaskWatcher("(###) #####-####", cellPhone));
        return view;
    }
	
    public void acessarApk(View view) {
    	MyLog.i("CLICK FRAGMENT OAUTH");
    	((MainActivity)this.getActivity()).acessarApk(view);
	}
    
}
