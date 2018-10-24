
package br.com.pizzaria.pizzaria.classes;
import java.io.Serializable;

public class A_Produtos implements Serializable {
    //Campos da Lista
    private int     imagem;
    private String  nome;
    private String  descricao;
    private double  preco;


    public A_Produtos(String nome) {
        this.nome = nome;
    }

    //Getter and Setter
    public int getImagem() {return imagem;}
    public void setImagem(int imagem) {this.imagem = imagem;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}



    //Construtor dos Campos do Array List (Quantidade e Total não entram
    public A_Produtos(int imagem, String nome, String descricao, double preco) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
}




//Classe dos Atributos Serializable, para passar os dados para outra tela (intent)