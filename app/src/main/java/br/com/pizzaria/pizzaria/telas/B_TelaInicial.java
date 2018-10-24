package br.com.pizzaria.pizzaria.telas;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.pizzaria.pizzaria.R;
import br.com.pizzaria.pizzaria.classes.A_Produtos;

public class B_TelaInicial extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_inicial);

        //Executa "X" Ação A Cada "x" Tempo (após splashScreen, chama a tela principal)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), C_TelaCategoria.class));
                finish();
            }
        },2000);
    }
}


