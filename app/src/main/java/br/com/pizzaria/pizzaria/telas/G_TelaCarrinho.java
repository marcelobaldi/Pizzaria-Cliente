package br.com.pizzaria.pizzaria.telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import br.com.pizzaria.pizzaria.R;
import br.com.pizzaria.pizzaria.classes.F_Carrinho;
import br.com.pizzaria.pizzaria.classes.H_produtosCarrinho;
import br.com.pizzaria.pizzaria.classes.Pedido;

public class G_TelaCarrinho extends AppCompatActivity {
    F_Carrinho carrinho;
    ListView listView;
    TextView tx_valorTotal;

    TextView quantidade_carrinho;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_activity_carrinho);

        tx_valorTotal = (TextView) findViewById(R.id.tx_valor);

        carrinho = F_Carrinho.getInstancia();
        ArrayList<String> nomesProdutos = new ArrayList<String>();

        //Passando em todos os produtos
        for (int i = 0;i<carrinho.getProdutos().size();i++){
            nomesProdutos.add(carrinho.getProdutos().get(i).getProd().getNome());   //Adicionando o nome de um produto a lista de string
        }

        //ListView - Lista de A_Produtos Personalizada
        ListView ListaX = (ListView) findViewById(R.id.carrinho_lista);
        TelaAdapter TelaX = new TelaAdapter();
        ListaX.setAdapter(TelaX);

        if (F_Carrinho.getInstancia()!= null) {
            String stringValor = String.valueOf(carrinho.calculaTotal());
            tx_valorTotal.setText("Valor:"+stringValor);
        }




    }

    class TelaAdapter extends BaseAdapter {
//        Context ctx;
//        public TelaAdapter(Context ctx){
//            this.ctx = ctx
//        }

        @Override
        public int getCount() {return carrinho.getProdutos().size();}
        @Override
        public Object getItem(int i) {return null;}
        @Override
        public long getItemId(int i) {return 0;}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            H_produtosCarrinho produtoAtual = carrinho.getProdutos().get(i);
            //view = ctx.getLayoutInflater().inflate(R.layout.g_activity_carrinho_adapter,null);
            view = getLayoutInflater().inflate(R.layout.g_activity_carrinho_adapter,null);

                ImageView   ImgX  = (ImageView) view.findViewById(R.id.ListaAdapter_Imagem);
                TextView    Txt1  = (TextView)  view.findViewById(R.id.ListaAdapter_Nome);
                TextView    Txt2  = (TextView)  view.findViewById(R.id.ListaAdapter_Descricao);
                TextView    Txt3  = (TextView)  view.findViewById(R.id.CarrinhoAdapter_Preco);

                ImgX.setImageResource(produtoAtual.getProd().getImagem());             //setImageResource
                Txt1.setText(produtoAtual.getProd().getNome());
                Txt2.setText(String.valueOf(produtoAtual.getQuantidade()));

                double valor_total = produtoAtual.getValor_total();
                Txt3.setText(String.valueOf(valor_total));


            return view;
        }
    }

    public void confirmar(View view){
        Pedido pedido = new Pedido( carrinho );
    }
}



/*
// Adapter Simples (Não Precisa da Classe Herdada do BaseAdapter; Não Precisa de uma Activity Xml
ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nomesProdutos);
listView = (ListView) findViewById(R.id.carrinho_lista);
listView.setAdapter(adapters);
*/
