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

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.pizzaria.pizzaria.classes.A_Produtos;
import br.com.pizzaria.pizzaria.R;

public class D_TelaProdutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity_produtos);

        // ArrayList
        final ArrayList<A_Produtos> listaProdutos = new ArrayList<A_Produtos>();
        listaProdutos.add(new A_Produtos(R.drawable.pizza1, "Pizza Mussarela", "Muzzarela 3 Marias", 25.50));
        listaProdutos.add(new A_Produtos(R.drawable.pizza2, "Pizza Calabresa", "Calabresa Aurora", 27.00));

        // Carregar a Lista sem o Construtor do Adapter
        ListView varTempLista = (ListView) findViewById(R.id.ListaXml);
        Adapter varTempAdapter = new Adapter(listaProdutos);
        varTempAdapter.ListaAdapter = listaProdutos;
        varTempLista.setAdapter(varTempAdapter);

        //retornar o item clicado (nomeHolder por exemplo)
        varTempLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Exibir o Item Clicado da Lista (Campos Add com Construtor)
                Toast.makeText(D_TelaProdutos.this, listaProdutos.get(i).getNome(),Toast.LENGTH_SHORT).show();

                //Ir para outra Tela e Carregar o Item
                Intent varX = new Intent(D_TelaProdutos.this, E_TelaItem.class);
                varX.putExtra("TagX", (Serializable) listaProdutos.get(i) );
                startActivity(varX);
            }
        });
    }


    class Adapter extends BaseAdapter {
        ArrayList<A_Produtos> ListaAdapter;

        public Adapter(ArrayList<A_Produtos> listaAdapter) {
            ListaAdapter = listaAdapter;
        }

        public int getCount() {return ListaAdapter.size();}
        @Override
        public Object getItem(int i) {return ListaAdapter.get(i);}
        @Override
        public long getItemId(int i) {return i;}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView;
            ViewHolder holder;

            if(view == null) {
                convertView = getLayoutInflater().inflate(R.layout.d_activity_lista_adapter, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                convertView = view;
                holder = (ViewHolder) view.getTag();
            }

            holder.imagemHolder.setImageResource(ListaAdapter.get(i).getImagem());             //setImageResource
            holder.nomeHolder.setText(ListaAdapter.get(i).getNome());
            holder.descricaoHolder.setText(ListaAdapter.get(i).getDescricao());
            holder.precoHolder.setText(String.format("%.2f",ListaAdapter.get(i).getPreco()).replace('.',','));       //StringValueOf

            return convertView;
        }

        public class ViewHolder {
            public ImageView imagemHolder;
            public TextView  nomeHolder;
            public TextView  descricaoHolder;
            public TextView  precoHolder;

            public ViewHolder(View view) {
                imagemHolder    = (ImageView)   view.findViewById(R.id.ListaAdapter_Imagem);
                nomeHolder      = (TextView)    view.findViewById(R.id.ListaAdapter_Nome);
                descricaoHolder = (TextView)    view.findViewById(R.id.ListaAdapter_Descricao);
                precoHolder     = (TextView)    view.findViewById(R.id.ListaAdapter_Preco);
            }
        }
    }
}



/*
//Adapter Simples (GetView = Sem o Holder)
class Adapter extends BaseAdapter {
    ArrayList<A_Produtos> ListaAdapter;

    public int getCount() {return ListaAdapter.size();}
    @Override
    public Object getItem(int i) {return ListaAdapter.get(i);}
    @Override
    public long getItemId(int i) {return i;}
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = getLayoutInflater().inflate(R.layout.d_activity_lista_adapter,null);

        ImageView   ImgX  = (ImageView) view.findViewById(R.id.AdapterXml_Imagem);
        TextView    Txt1  = (TextView)  view.findViewById(R.id.AdapterXml_Nome);
        TextView    Txt2  = (TextView)  view.findViewById(R.id.AdapterXml_Descricao);
        TextView    Txt3  = (TextView)  view.findViewById(R.id.AdapterXml_Preco);

        ImgX.setImageResource(ListaAdapter.get(i).getImagem());             //setImageResource
        Txt1.setText(ListaAdapter.get(i).getNome());
        Txt2.setText(ListaAdapter.get(i).getDescricao());
        Txt3.setText(String.valueOf(ListaAdapter.get(i).getPreco()));       //StringValueOf

        return view;
    }
}
*/















//Classe Adapter - Dentro de Outra Classe
    //Se Classe Adapter "Própria", então Comando GetLayoutInflater Não Funciona;
    //Se Classe Adapter com Construtor, então os Comandos para Carregar a Lista Mudarão;
    //Se Classe Adapter com Construtor, então os Comandos para Exibir (Toast) mudará;

//Classe Atributos - Construtor
    //Sem Constutor é um Jeito para Montar a Lista, Com Construtor é de Outro;

//Classe Atributos - Getter and Setter
    //Sem Getter e Setter Atribuir Valores e Pegar Valores é de um Jeito, Com Getter e Setter é de Outro;
    //Sem Getter e Setter Exibir - Toast é de um Jeito, Com Getter e Setter é de Outro;

//Objetos (ArrayList)
    //Declarar Dentro do Mètodo OnCreate, precisa do "final". Declarar Na classe (no ínicio), não precisa da Classe;


//1 Criar a Classe dos Atributos:       Campos; Getter e Setter; Construtor;
//2 Criar a Activity ListView:          ListView;
//3 Criar a Activity ListAdapter:       Layout da Lista;
//4 Criar a ArrayList (Classe Main):    Itens Exibidos na Lista;

//5 Criar a Classe Adapter:             Exibir o Layout da Lista com os Itens Exibidos na Lista;
//6 Configurar a Classe Adapter         na classe Adapter
//7 Configurar a Listview               na classe Main


/* Carregar a Lista com o Construtor do Adapter
    //Na Classe do Adapter, Insrir o Construtor (Alt+Insert - Constructor);

    //Na Classe Maind, Carregar a Lista
        ListView varTempLista = (ListView) findViewById(R.id.ListaXml);
        Adapter varTempAdapter = new Adapter(ListaProdutos);                //
        varTempAdapter.ListaAdapter = ListaProdutos;
        varTempLista.setAdapter(varTempAdapter);

* Este Exemplo foi testado com a Classe Adapter Dentro da Classe Main. E se a Classe do Adapter fosse Separada?!?!
*/


//  Exemplo de Intent (Levando o Item da Lista para Outra Página)
//      Intent varX = new Intent(D_TelaProdutos.this, E_TelaItem.class);
//     varX.putExtra("TagX", (Serializable) ListaProdutos.get(i) );
//         startActivity(varX);


//Criar Classe "Adapter"
//extends BaseAdapter;
//Alt+Insert e Implements Methods = 4 metodos (getcount; getitem; getitemid; getview)
//Criar o Array List    "ListaAdapter"

// Configurar a Classe Adapter
//ArrayList<produtos>      "ListaAdapter"                  não entendi muito;
//getCount                 "ListaAdapter.size"
//getItem                  "ListaAdapter.get(i)"
//getItemId                "return i"

//GetView
// View getLayoutInflater com a listaAdapter xml.   Atenção !!!!!! Se a Classe for independente, muda o Comando !!!!!!!!!!!!!!!!!!!
// identificar os objetos da lista personalizada (findviewbyid) ...
// pegar os valores ... gets ... para ImageView é um jeito, TextView Texto outro jeito, TextView Número outro Jeito;

// Configurar a Classe Main
// Carregar a Lista;
// Identicar o Item Clicado e passar para outra tela;

// para passar para outra tela, a classe dos atributos tem que estar Serializable;

// criar o objeto arraylit como final no método, ou sem o final se declarar na classe. para todos verem;
// quando criar o objeto arraylist em uma classe java propria, acho que irá mudar o array list na classe main tb (para identificar);

