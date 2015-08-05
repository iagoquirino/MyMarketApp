package br.com.mymarket.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import br.com.mymarket.MyMarketApplication;
import br.com.mymarket.R;
import br.com.mymarket.delegates.ReceiverDelegate;
import br.com.mymarket.infra.MyLog;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdRequest.Builder;
//import com.google.android.gms.ads.AdView;

public abstract class AppBaseActivity extends Activity {

    public static final String FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION = "br.com.mymarket.activities.FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION";
    private BaseActivityReceiver baseActivityReceiver = new BaseActivityReceiver();
    public static final IntentFilter INTENT_FILTER = createIntentFilter();
    protected ReceiverDelegate event;

    private static IntentFilter createIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        return filter;
    }

    protected void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected void setNormalScreen(){
        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);                 
    }

    protected void adRequest(){
//		Builder request = new AdRequest.Builder(); 
//		request.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
//		AdView adView = (AdView) findViewById(R.id.adviews);
//		adView.loadAd(request.build());
    }


    protected void registerBaseActivityReceiver() {
        registerReceiver(baseActivityReceiver, INTENT_FILTER);
    }

    protected void unRegisterBaseActivityReceiver() {
        unregisterReceiver(baseActivityReceiver);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        MyLog.i("DESTRUIU O PICO");
        this.event.desregistra(getMyMarketApplication());
        unRegisterBaseActivityReceiver();
    }

    public class BaseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION)) {
                finish();
            }
        }
    }

    protected void closeAllActivities() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setTitle((String)getString(R.string.app_name));
        alertDialog.setMessage((String)getString(R.string.comum_sair_app));
        alertDialog.setPositiveButton((String)getString(R.string.comum_sim), new OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                sendBroadcast(new Intent(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION));
                killApp(true);
            }
        });
        alertDialog.setNegativeButton((String)getString(R.string.comum_nao), null);
        alertDialog.show();
    }

    protected void killApp(boolean killSafely) {
        if (killSafely) {
            System.runFinalizersOnExit(true);
            System.exit(0);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    public MyMarketApplication getMyMarketApplication(){
        return (MyMarketApplication) getApplication();
    }

    public void processarException(Exception e) {
        Toast.makeText(this, "Erro na busca dos dados", Toast.LENGTH_SHORT).show();
    }


    public void toastInserido() {
        Toast.makeText(this, getString(R.string.comum_registro_inserido_sucesso), Toast.LENGTH_LONG).show();
    }

    public void toastAlterado() {
        Toast.makeText(this, getString(R.string.comum_registro_alterado_sucesso), Toast.LENGTH_LONG).show();
    }

    public void toastExcluido(){
        Toast.makeText(this, getString(R.string.comum_registro_deletado_sucesso), Toast.LENGTH_LONG).show();
    }

    public void toastErro() {
        Toast.makeText(this, getString(R.string.erro_api), Toast.LENGTH_LONG).show();
    }

    public void atualizarLista(){

    }

    public String getUri(){
        return null;
    }
}
