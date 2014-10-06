package br.com.mymarket.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mymarket.R;
import br.com.mymarket.model.Pessoa;

public class PessoaAdapter extends BaseAdapter {
	
	private Context context;
    private final List<Pessoa> listas;
    
    private SparseBooleanArray mSelectedItemsIds;

    public PessoaAdapter(Context mContext, List<Pessoa> listas) {
        this.context = mContext;
        this.listas = listas;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return listas.size();
    }

    @Override
    public Object getItem(int i) {
        return listas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        int layout = R.layout.listview_contatos;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,viewGroup,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        
        Pessoa pessoa = (Pessoa) getItem(position);
        convertView.setBackgroundColor(mSelectedItemsIds.get(position) ? 0x9934B5E4	: Color.TRANSPARENT);
        viewHolder.nome.setText(pessoa.getNome());
        viewHolder.telefone.setText(pessoa.getCelular());
        return convertView;
    }

    public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}
    
    public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}
	
	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    public int getSelectedCount() {
		return mSelectedItemsIds.size();
	}

    class ViewHolder {
        TextView nome;
        TextView telefone;

        ViewHolder(View view) {
            this.nome = (TextView) view.findViewById(R.id.nome);
            this.telefone = (TextView) view.findViewById(R.id.telefone);
        }
    }
    

}
