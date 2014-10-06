package br.com.mymarket.navegacao;

import br.com.mymarket.R;
import br.com.mymarket.activities.GrupoActivity;
import br.com.mymarket.fragments.FormularioGrupoFragment;
import br.com.mymarket.fragments.GrupoFragment;
import br.com.mymarket.fragments.ProgressFragment;
import br.com.mymarket.utils.FragmentUtils;

public enum EstadoGrupoActivity {
	 INICIO{
	        public void executa(GrupoActivity activity){
	        	activity.buscarListaGrupo();
	        	activity.alteraEstadoEExecuta(EstadoGrupoActivity.AGUARDANDO_LISTAGEM);
	        }
	    },AGUARDANDO_LISTAGEM{
	        public void executa(GrupoActivity activity){
	        	FragmentUtils.colocaOuBuscaFragmentNaTela(activity,R.id.fragment_principal,ProgressFragment.class,false);
	        }
	    },LISTAS_RECEBIDAS{
	    	public void executa(GrupoActivity activity){
	    		FragmentUtils.colocaOuBuscaFragmentNaTela(activity,R.id.fragment_principal,GrupoFragment.class,false);
	    	}
	    },CADASTRAR{
	    	public void executa(GrupoActivity activity){
	    		FragmentUtils.colocaOuBuscaFragmentNaTela(activity,R.id.fragment_principal,FormularioGrupoFragment.class,false);
	    	}
	    };

	    public void executa(GrupoActivity activity){

	    } 
}
