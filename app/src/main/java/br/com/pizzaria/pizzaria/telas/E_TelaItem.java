package br.com.pizzaria.pizzaria.telas;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pizzaria.pizzaria.classes.A_Produtos;
import br.com.pizzaria.pizzaria.classes.F_Carrinho;
import br.com.pizzaria.pizzaria.R;
import br.com.pizzaria.pizzaria.classes.H_produtosCarrinho;

public class E_TelaItem extends AppCompatActivity {
    A_Produtos prod;      //Variável com Classe dos Atributos (para puxar valores da 1a. página);??????????????????????????
    double calculoXX;
    int quantidade;
    ArrayList<String> acrescimos = new ArrayList<String>();
    H_produtosCarrinho prodCarrinho = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Carregar a Página Xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity_item);

        //Identificar Obejtos da Activity(Tela) - Final para ser enxergada em outras Funções!!!!!
        final ImageView       imagem      = (ImageView)       findViewById(R.id.SegundaTela_Imagem);
        final TextView        nome        = (TextView)        findViewById(R.id.SegundaTela_Nome);
        final TextView        descricao   = (TextView)        findViewById(R.id.SegundaTela_Descricao);
        final TextView        preco       = (TextView)        findViewById(R.id.SegundaTela_Preco);
        final CompoundButton  borda       = (CompoundButton)  findViewById(R.id.SegundaTela_check_Borda);
        final TextView        qt          = (TextView)        findViewById(R.id.SegundaTela_Quantidade);
        final TextView        total       = (TextView)        findViewById(R.id.SegundaTela_Total);
        final ImageButton     mais        = (ImageButton)     findViewById(R.id.SegundaTela_bt_Mais);
        final ImageButton     menos       = (ImageButton)     findViewById(R.id.SegundaTela_bt_Menos);

        //Receber dados da 1a Tela (Item do Produto Clicado)
        Intent intent = getIntent();
        prod = (A_Produtos) intent.getSerializableExtra("TagX");
        prodCarrinho = (H_produtosCarrinho) intent.getSerializableExtra("prodCarrinho");
        if (prodCarrinho != null){
            prod  = prodCarrinho.getProd();
            Log.e( "TEste","TEste log" );
        }


        //Pegar os Valores Recebidos e Colocar nos Objetos da Activity(Tela)
        imagem.setImageResource(prod.getImagem());
        nome.setText(prod.getNome());
        descricao.setText(prod.getDescricao());
        preco.setText(String.valueOf(prod.getPreco())); //pegar, converter para texto, imputar num objeto;

        //Definir os Valores Iniciais (Campos que não vieram da tela anterior)
        quantidade = 1;
        borda.setChecked(false);
        if (prodCarrinho != null){
            quantidade = prodCarrinho.getQuantidade();
            if (prodCarrinho.getAcrecimos().size() > 0){
                borda.setChecked(true);
            }
        }
        qt.setText(String.valueOf(quantidade));      //Define o Valor Inicial da Quantidade como 1
                    //Define o Valor Inicial da Borda, como sem borda (não clicado)
        total.setText(String.valueOf(prod.getPreco()));   //define o valor inicial do Total


        // Ação do Botão CheckList (Borda de Catupirty)
        borda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            double valorCheckBox = 5;                                //Preço da Borda

            if (borda.isChecked()) {                                 //Se Habilitar/Desabilitar o checkbox, o valor de 1 produto volta
                qt.setText(String.valueOf(1));                       //Atualizar Quantidade Para 1
                preco.setText(String.valueOf(prod.getPreco()));      //Atualizar Preço Para o Preço Unitário + 5 reais da borda
                total.setText(String.valueOf(prod.getPreco() + valorCheckBox));      //Atualizar Total Para o Preço Unitário
                prod.setPreco(prod.getPreco()+valorCheckBox); //Atualizando preço do produto
            } else {
                qt.setText(String.valueOf(1));                       //Atualizar Quantidade Para 1
                preco.setText(String.valueOf(prod.getPreco()));      //Atualizar Preço Para o Preço Unitário
                total.setText(String.valueOf(prod.getPreco()));      //Atualizar Total Para o Preço Unitário
            }
            if (!acrescimos.contains("Borda")){
                acrescimos.add("Borda");
            }
            }
        });


        //Ação do Botão Quantidade Mais
        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int Var1 = Integer.valueOf(qt.getText().toString());              //Pegar Campo Quantidade
                quantidade++;                                           //Aumentar mais um
                qt.setText(String.valueOf(quantidade));                                 //Atualizar o campo quantidade

                if (borda.isChecked()) {                                            //se tiver borda, então "precoHolder unitário" é precoHolder + borda
                    double novoValor = Double.valueOf(preco.getText().toString());  //Pegar o campo precoHolder
                    double soma = novoValor + 5;                                    // somar o campo precoHolder com mais a borda
                    double calculoXX = quantidade * soma;                               // calulcular precoHolder x quantidade
                    total.setText(String.valueOf(calculoXX));                       // exibir o caluclo no campo total
                } else{
                    double novoValor = Double.valueOf(preco.getText().toString());  //Pegar o campo precoHolder
                    double calculoXX = quantidade * novoValor;                          // calulcular precoHolder x quantidade
                    total.setText(String.valueOf(calculoXX));                       // exibir o caluclo no campo total
                }
            }
        });


        //Ação do Botão Quantidade Menos
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantidade--;

                qt.setText(String.valueOf(quantidade));
                if (borda.isChecked()) {
                    double novoValor = Double.valueOf(preco.getText().toString());  //Pegar o campo precoHolder
                    double soma = novoValor + 5;                                    // somar o campo precoHolder com mais a borda
                    calculoXX = quantidade * soma;                               // calulcular precoHolder x quantidade
                    total.setText(String.valueOf(calculoXX));                       // exibir o caluclo no campo total
                } else {
                    double novoValor = Double.valueOf(preco.getText().toString());  //Pegar o campo precoHolder
                    calculoXX = quantidade * novoValor;                          // calulcular precoHolder x quantidade
                    total.setText(String.valueOf(calculoXX));                       // exibir o caluclo no campo total
                }

            }
        });
    }


    public void addCarinho(View view){
        F_Carrinho carrinho = F_Carrinho.getInstancia();
        carrinho.addProduto(new H_produtosCarrinho(prod,quantidade,acrescimos));
        carrinho.addValor(prod.getPreco());

        Log.i("telaItem","quantidade: "+quantidade);

        Toast.makeText(this,prod.getNome()+" Adicionado ao Carrinho",Toast.LENGTH_SHORT).show();

        //Ir para a Tela Inicial
        Intent varX = new Intent(E_TelaItem.this, C_TelaCategoria.class);
        startActivity(varX);
    }
}


/* Botão do Carrinho - Vinícius     Com o For, as vezes não adicionava nada!!!!!!!
    public void addCarinho(View view){
        F_Carrinho carrinho = F_Carrinho.getInstancia();
        for (int i =0;i<quantidade;i++){
            carrinho.addProduto(prod);
            carrinho.addValor(prod.getPreco());
        }
        Toast.makeText(this,"Item "+prod.getNome()+" Adicionado ao carrinho",Toast.LENGTH_SHORT).show();
        finish();
    }
*/







/*
// variável do objeto com final, todos os métodos da classe enxerga
// variável temporária (soma) com final, só o metodo em que esta enxerga. Não funciona o final ....

// comando para testar !!!!!!!!!!!!!
//    Toast.makeText(E_TelaItem.this, String.valueOf(novoValor), Toast.LENGTH_SHORT).show();

// Calculos = Inteiro x Inteiro (int); Decimal x Decimal (double) se não dá pau!!!!
// aprender fazer conversões de inteiro para Decimal, etc;
// aprender fazer cnversões dos numeros para Text = Exibir Toast;

// Toast.makeText(E_TelaItem.this, String.valueOf(calculo), Toast.LENGTH_SHORT).show();

// Precisa do campo total para funcionar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1


// QT é a quantidade !!!  novovalor é o produto


//    int qtMais = QT * novoValor;
//    int QtMais = varMais * novoValor;
//    varTempX.setText(String.valueOf(QtMais));

//      double nomeHolder = Double.valueOf(varTempNome.getText().toString());

// receber texto
//     Intent varX2 = getIntent();
//    String valor2 = varX2.getStringExtra("TagX");
//    Toast.makeText(E_TelaItem.this, valor2, Toast.LENGTH_LONG).show();

//    final EditText varX = (EditText)findViewById(R.id.check_EditText1);
//    varX.setText(String.valueOf(100));

       /* final CompoundButton varx1 = (CompoundButton) findViewById(R.id.check_checkBox1);
        varx1.setChecked(false);                        // Objeto E_TelaItem aparecendo desabilitado
        varx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String habilitadox   =   varx1.isChecked() ? "Habilitado" : "Desabilitado";
                Toast.makeText(E_TelaItem.this, habilitadox, Toast.LENGTH_LONG).show();

                double valor = Double.parseDouble(varX.getText().toString());
                double valor2 = 100;
                double soma = valor + valor2;

                EditText varXY = (EditText)findViewById(R.id.check_EditText2);
                varXY.setText(String.valueOf(soma));

                if(varx1.isChecked()) {
                    Toast.makeText(E_TelaItem.this, "Aumentar R$ 100,00", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(E_TelaItem.this, "Manter o Preço Normal", Toast.LENGTH_LONG).show();
                }
            }
        });*/

//pq tem que ser declarado Final?! senão não funciona?!
//como faze para atualizar automático, quando clicado = .setOnClickListener (o mesmo comando do botão de comando);

// Exportar para TextView;
// Exportar para Objeto_Edit;
// Exibir na Caixa de Entrada;
// Trabalhar com Condicionadores (IF);