package br.com.mymarket.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import br.com.mymarket.R;
import br.com.mymarket.activities.PessoaActivity;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.model.Produto;

public class PessoaFragment extends Fragment {
	
	private ListView listview;
    
    private PessoaActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.listview = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        activity = ((PessoaActivity)this.getActivity());
		activity.registerForContextMenu(this.listview);
		
		this.listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,int posicao, long id) {
				activity.setarContatosSelecionados((Pessoa)adapter.getItemAtPosition(posicao),posicao);
			}
		});
		
        this.listview.setAdapter(activity.getAdapter());
        return this.listview;
    }

}
