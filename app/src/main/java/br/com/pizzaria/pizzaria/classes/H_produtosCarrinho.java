package br.com.pizzaria.pizzaria.classes;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class H_produtosCarrinho  implements Serializable {
    ArrayList<A_Produtos> prod;
    ArrayList<String> acrecimos;
    int quantidade;
    double valor_total;

    public H_produtosCarrinho(A_Produtos prod, int quantidade,ArrayList<String> acrecimos) {
        this.prod = new ArrayList<A_Produtos>();
        this.prod.add(prod);
        this.quantidade = quantidade;
        this.valor_total = prod.getPreco()*quantidade;
        this.acrecimos = acrecimos;
    }

    public H_produtosCarrinho(ArrayList<A_Produtos> prod, int quantidade) {
        this.prod = prod;
        this.quantidade = quantidade;
        this.valor_total = getPrecoMeia()*quantidade;
    }

    public double getPrecoMeia(){
        double maior = 0;
        for (A_Produtos produto:prod){
            if (maior < produto.getPreco()){
                maior = produto.getPreco();
            }
        }
        return maior;
    }

    public void atualizaValor(){
        valor_total = getPrecoMeia()*quantidade;
    }

    public A_Produtos getProd() {
        if (prod.size() == 1) {
            return prod.get(0);
        }
        else{
            //Mudar
            return prod.get(0);
        }
    }

    public ArrayList<String> getAcrecimos() {
        return acrecimos;
    }

    public void setAcrecimos(ArrayList<String> acrecimos) {
        this.acrecimos = acrecimos;
    }

    public void setProd(ArrayList<A_Produtos> prod) {
        this.prod = prod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_total() {
        this.atualizaValor();
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    @Override
    public boolean equals(Object obj){
        //Função para verificar se o itens são iguais
        H_produtosCarrinho objteste = (H_produtosCarrinho) obj;
        boolean acrescimoVerificador = true;

        //Verifica se todos os acrecimos na lista são iguais
        if (acrecimos.size() != ((H_produtosCarrinho) obj).acrecimos.size()){
            acrescimoVerificador = false;
        }
        else {
            for (int i = 0; i < acrecimos.size(); i++) {
                //Se um acrescimo e diferente troca o verificador para false
                if (acrecimos.get(i) != ((H_produtosCarrinho) obj).acrecimos.get(i)) {
                    acrescimoVerificador = false;
                    break;
                }
            }
        }
        boolean produtosVerificador = true;

        //Verifica se todos os produtos na lista são iguais
        if (prod.size() != ((H_produtosCarrinho) obj).prod.size()){
            produtosVerificador = false;
        }
        else {
            //Mesma logica do produtos porem com o nome dos produtos
            for (int i = 0; i < prod.size(); i++) {
                String nomeProd = objteste.prod.get(i).getNome();
                if (!prod.get(i).getNome().equals(nomeProd)) {
                    produtosVerificador = false;
                    break;
                }
            }
        }

        Log.e("equal","compara Produto");
        return acrescimoVerificador && produtosVerificador;
    }
}
