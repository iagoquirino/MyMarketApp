package br.com.mymarket.infra;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import br.com.mymarket.R;
import br.com.mymarket.activities.PessoaActivity;
import br.com.mymarket.activities.ProdutosActivity;
import br.com.mymarket.model.Pessoa;
import br.com.mymarket.model.Produto;

public class ActionModePessoaCallback implements ActionMode.Callback {

	
	private List<Pessoa> list;
	
	private PessoaActivity activity;
	
	public ActionModePessoaCallback(PessoaActivity activity){
		this.activity = activity;
	}
	
	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		mode.getMenuInflater().inflate(R.menu.menu_actionmode, menu);
		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		return false;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		if(item.getItemId() ==  R.id.menu_send_shop) {
				list = new ArrayList<Pessoa>();
				SparseBooleanArray selected = activity.getAdapter().getSelectedIds();
				for (int i = (selected.size() - 1); i >= 0; i--) {
					if (selected.valueAt(i)) {
						Pessoa selectedItem = (Pessoa)activity.getAdapter().getItem(selected.keyAt(i));
						list.add(selectedItem);
					}
				}
				activity.adicionarContatos(list);
//				mode.finish();
				return true;
		}
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		activity.getAdapter().removeSelection();
		activity.onBackPressed();
	}
}