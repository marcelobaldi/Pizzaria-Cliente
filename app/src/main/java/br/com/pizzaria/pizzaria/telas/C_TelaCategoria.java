package br.com.pizzaria.pizzaria.telas;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.pizzaria.pizzaria.R;

public class C_TelaCategoria extends AppCompatActivity {
    //Arrays
    int[] Imagens   = {R.drawable.pizza, R.drawable.esfiha, R.drawable.comida9, R.drawable.bebida};
    String[] Nomes  = {"Pizzas", "Esfihas","Comidas", "Bebidas"};
    String[] Descri = {"Molho de Tomate Natural", "15 cm e Massa Amanteigada", "Feijoada, Baião de 2 ...", "Refrigerante, Cerveja, Chopp ..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Página Xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_categoria);

        //ListView- Lista de A_Produtos Xml
        ListView ListaX = (ListView) findViewById(R.id.ListaDeProdutos);

        //ListView - Lista de A_Produtos Personalizada
        TelaAdapter TelaX = new TelaAdapter();
        ListaX.setAdapter(TelaX);

        //Retornar o Item Clicado
        ListaX.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Exibir o Item Clicado da Lista (Campos Add com Construtor)
                Toast.makeText(C_TelaCategoria.this, Nomes[i],Toast.LENGTH_SHORT).show();

                //Conforme Item Clicado, Ir para a Tela deste Item
                if (Nomes[i] == "Pizzas") {
                    Intent varX = new Intent(C_TelaCategoria.this, D_TelaProdutos.class);
                    startActivity(varX);
                }
            }
        });
    }


    //ListView - Lista Personalizada
    class TelaAdapter extends BaseAdapter {
        @Override
        public int getCount() {return Imagens.length;} // O Tamanho da Lista é o Tamanho da "quantidade de imagens"
        @Override
        public Object getItem(int i) {return null;}
        @Override
        public long getItemId(int i) {return 0;}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.c_activity_categoria_adapter,null);
                ImageView ImgX  = (ImageView)   view.findViewById(R.id.ListaP_Imagem);
                TextView  Txt1  = (TextView)    view.findViewById(R.id.ListaP_Nome);
                TextView  Txt2  = (TextView)    view.findViewById(R.id.ListaP_Descricao);

                ImgX.setImageResource(Imagens[i]);
                Txt1.setText(Nomes[i]);
                Txt2.setText(Descri[i]);
            return view;
        }
    }

    public void telaCarrinho(View view){
        //Intent intent = new Intent(C_TelaCategoria.this,G_TelaCarrinho.class);
        Intent intent = new Intent(C_TelaCategoria.this,H_TelaCarrinhoRecycle.class);
        startActivity(intent);
    }
}














