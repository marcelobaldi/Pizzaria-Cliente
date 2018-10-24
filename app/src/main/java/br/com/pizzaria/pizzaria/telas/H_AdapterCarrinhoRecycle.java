package br.com.pizzaria.pizzaria.telas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import br.com.pizzaria.pizzaria.R;
import br.com.pizzaria.pizzaria.classes.F_Carrinho;
import br.com.pizzaria.pizzaria.classes.H_produtosCarrinho;

/**
 * Created by Marcelo on 29/11/2017.
 */

public class H_AdapterCarrinhoRecycle extends RecyclerView.Adapter<H_AdapterCarrinhoRecycle.CarrinhoViewHolder> {
    F_Carrinho carrinho = F_Carrinho.getInstancia();
    Context ctx;
    int removidos = 0;

    public H_AdapterCarrinhoRecycle(Context ctx){
        this.ctx = ctx;
    }

    TextView tx_valorTotal;

    @Override
    public CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.h_activity_carrinho_adpter_recycle, parent, false);

        CarrinhoViewHolder viewHolder =new CarrinhoViewHolder(view);
        return viewHolder;
    }


    //Desenha itens no carrinho
    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, final int position) {
        final H_produtosCarrinho produtoAtual = carrinho.getProdutos().get(position);

        holder.imagemItemCarrinho.setImageResource(produtoAtual.getProd().getImagem());             //setImageResource
        holder.txItemNome.setText(produtoAtual.getProd().getNome());
        holder.txItemQuatidade.setText(String.valueOf(produtoAtual.getQuantidade()));

        double valor_total = produtoAtual.getValor_total();
        holder.txItemPreço.setText(String.valueOf(valor_total));

        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gambirra para apagar itens
                carrinho.removeItem(carrinho.getProdutos().get(position-removidos));
                removidos ++;
                notifyItemRemoved(position);
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,E_TelaItem.class);
                intent.putExtra("prodCarrinho", (Serializable) produtoAtual );
                ctx.startActivity(intent);
            }
        })  ;
    }


    @Override
    public int getItemCount() {
        return carrinho.getProdutos().size();
    }

    class CarrinhoViewHolder extends RecyclerView.ViewHolder{

        ImageView imagemItemCarrinho;
        TextView txItemQuatidade, txItemPreço,txItemNome;
        Button btEdit,btRemove;

        public CarrinhoViewHolder(View itemView) {
            super(itemView);

            imagemItemCarrinho = (ImageView) itemView.findViewById(R.id.im_ItemRecycle);
            txItemQuatidade = (TextView) itemView.findViewById(R.id.ItemRecycle_quantidade);
            txItemPreço = (TextView) itemView.findViewById(R.id.ItemRecycle_Preco);
            txItemNome = (TextView) itemView.findViewById(R.id.ItemRecycle_Nome);
            btEdit = (Button) itemView.findViewById(R.id.bt_edit_item);
            btRemove = (Button) itemView.findViewById(R.id.bt_remove_item);
        }
    }
}
