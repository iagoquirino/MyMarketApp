package br.com.mymarket.navegacao;

import br.com.mymarket.R;
import br.com.mymarket.activities.PessoaActivity;
import br.com.mymarket.fragments.PessoaFragment;
import br.com.mymarket.fragments.ProgressFragment;
import br.com.mymarket.utils.FragmentUtils;

public enum EstadoPessoaActivity {
	
	INICIO{
        public void executa(PessoaActivity activity){
        	activity.buscarContatosAplicativo();
        	activity.alteraEstadoEExecuta(EstadoPessoaActivity.AGUARDANDO);
        }
    },AGUARDANDO{
        public void executa(PessoaActivity activity){
        	FragmentUtils.colocaOuBuscaFragmentNaTela(activity,R.id.fragment_principal,ProgressFragment.class,false);
        }
    },LISTAGEM{
        public void executa(PessoaActivity activity){
        	FragmentUtils.colocaOuBuscaFragmentNaTela(activity,R.id.fragment_principal,PessoaFragment.class,false);
        }
    };

    public void executa(PessoaActivity activity){

    }

}
