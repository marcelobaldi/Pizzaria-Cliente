package br.com.pizzaria.pizzaria.classes;
import android.util.Log;

import java.util.ArrayList;

public class F_Carrinho {

    //Declarar Variável Com o Mesmo Nome da Classe
    private static F_Carrinho carrinho;

    //Campos Usados No Carrinho (Classe Produtos + ValorTotal do Carrinho)
    private ArrayList<H_produtosCarrinho> produtos;
    private double valorTotal;

    //Getter da Classe Produtos (Item) + Campo Total do Carrinho (soma de todos os itens)
    public ArrayList<H_produtosCarrinho> getProdutos() {return produtos;}

    //Construtor da Classe Personalizado !!!!!!!!!
    private F_Carrinho(){
        produtos = new ArrayList<H_produtosCarrinho>();
    }

    public static F_Carrinho getInstancia(){
        if (carrinho == null){
            carrinho = new F_Carrinho();
        }
        return carrinho;
    }

    //Função para Adicionar Item no Carrinho
    public void addProduto(H_produtosCarrinho produto){
        int id;
        //Pecorrendo todos os produtos da lista de produtos
        for (id=0;id<produtos.size();id++){
            if(produtos.get(id).equals(produto)){
                //Se algum produto for igual: Avisa que contem o produto, e adiciona sua quantidade
                //e finaliza a função
                Log.e("AddProduto","Contem o produto");
                produtos.get(id).quantidade += produto.quantidade;
                //produtos.set(id,produto);
                return;
            }
        }
        //Se não tem o item adiciona o produto
        Log.e("AddProduto","Não Contem o produto");
        produtos.add(produto);
    }

    //Somando itens do carrinho
    public double somandoTotal(){
        double valorCompra = 0;
//        for (int i=0;i<produtos.size();i++){
//            valorCompra = valorCompra  + produtos.get(i).getValor_total();
//        }
        for(H_produtosCarrinho produto:produtos){
            valorCompra += produto.getValor_total();
        }
        return valorCompra;
    }


    //Função Para Atualizar a Soma Total do Carrinho (quando item adicionado)
    public void addValor(double valor){
        valor += valor;
    }

    //Função Para Atualizar a Soma Total do Carrinho (quando item removido)
    public void removeValor(double valor){
        valor -= valor;
    }

    //Funcao Cacula total
    public double calculaTotal(){
        double valorTotal = 0;
        for (int i =0;i<produtos.size();i++){
            valorTotal += produtos.get(i).valor_total;
        }
        return valorTotal;
    }

    public int calculaQuatidade(){
        int quantidade = 0;
        for (int i =0;i<produtos.size();i++){
            quantidade += produtos.get(i).quantidade;
        }
        //Outro jeito de fazer
//        for(H_produtosCarrinho produto:produtos){
//            quantidade += produto.quantidade;
//        }
        return quantidade;
    }


    //Funcao Remover item
    public void removeItem(H_produtosCarrinho produto){
        produtos.remove(produto);
    }

}

//Primeiro Esta Classe, depois a Função que esta no Botao Adicinar ao Carrinho (Tela Item);