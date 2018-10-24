package br.com.pizzaria.pizzaria.classes;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pedido {
    String id;
    boolean aceito;
    String status;
    F_Carrinho carrinho;
    private static final String TAG = "Pedido";


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenciaPedido;

    public Pedido(F_Carrinho carrinho) {
        Log.i(TAG,"Iniciando Carrinho");
        this.aceito = false;
        this.status = "Enviando Pedido";
        this.carrinho = carrinho;

        this.enviarPedido();
    }

    public void enviarPedido(){
        Log.i(TAG,"Criando Pedido");
        DatabaseReference referenceBase = database.getReference().child("Pedidos");
        referenciaPedido = referenceBase.push();

        this.id = referenceBase.getKey();

        DatabaseReference referenceProdutos = referenciaPedido.child("Produto");
        for (H_produtosCarrinho prod:carrinho.getProdutos()){
            DatabaseReference prodRef = referenceProdutos.child(prod.getProd().getNome());
            prodRef.child( "quantidade" ).setValue(prod.quantidade);
            prodRef.child( "Acrecimos" ).setValue( prod.acrecimos);
            prodRef.child( "valor" ).setValue( prod.getValor_total());
        }
        this.status = "Enviado";
        referenciaPedido.child("Status").setValue(this.status);
        
    }

}
