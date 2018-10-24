package br.com.pizzaria.pizzaria.telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.com.pizzaria.pizzaria.R;
import br.com.pizzaria.pizzaria.classes.F_Carrinho;
import br.com.pizzaria.pizzaria.classes.Pedido;

public class H_TelaCarrinhoRecycle extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tx_valorTotal,tx_quantidadeTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h__tela_carrinho_recycle);

        Log.i("Recycle","inicio");

        recyclerView = (RecyclerView) findViewById(R.id.rv_carrinho);

        tx_valorTotal =(TextView) findViewById(R.id.tx_valor);
        tx_quantidadeTotal = (TextView) findViewById(R.id.quantidade_Carrinho);

        //Criando o manager do recycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        Log.i("Recycle","meio");

        H_AdapterCarrinhoRecycle adapter = new H_AdapterCarrinhoRecycle(this);

        //Pegos o valor do total de preço e quantidade e itens e coloco no textview referente
        String stringValor = String.valueOf(F_Carrinho.getInstancia().calculaTotal());
        tx_valorTotal.setText("Valor:"+stringValor);
        String quantidade = String.valueOf(F_Carrinho.getInstancia().calculaQuatidade());
        tx_quantidadeTotal.setText(quantidade);

        Log.i("Recycle","meio2");
        recyclerView.setAdapter(adapter);
        Log.i("Recycle","fim");
    }

    public void confirmar(View view) {
        Pedido pedido = new Pedido(F_Carrinho.getInstancia());
    }
}
