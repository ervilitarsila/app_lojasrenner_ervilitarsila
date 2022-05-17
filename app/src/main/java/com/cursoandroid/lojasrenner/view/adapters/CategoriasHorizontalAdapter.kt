package com.cursoandroid.lojasrenner.view.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.lojasrenner.R
import com.cursoandroid.lojasrenner.model.Produto


class CategoriasHorizontalAdapter(
    private val produtos: List<Produto>, var clickProduto: ClickProduto
): RecyclerView.Adapter<CategoriasHorizontalAdapter.HorizontalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalHolder = HorizontalHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
    )

    override fun onBindViewHolder(holder: HorizontalHolder, position: Int) {
        val produto: Produto = produtos.get(position)

        holder.bind(produtos[position])

        holder.itemProduto.setOnClickListener {
            clickProduto.clickProduto(produto)
        }
    }

    override fun getItemCount(): Int = produtos.size

    interface ClickProduto{

        fun clickProduto(produto: Produto)

    }

    class HorizontalHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemProduto = itemView.findViewById<ConstraintLayout>(R.id.item_produto)

        fun bind(produto: Produto){
            with(itemView){
                itemView.findViewById<TextView>(R.id.titulo_produto).setText(produto.titulo)
                itemView.findViewById<TextView>(R.id.txtPrecoAvista).setText(
                        String.format("R$ %.2f%n",produto.precoAvista))


                val res: Resources =  getResources()
                val resID: Int =
                    res.getIdentifier(produto.fotoPrincipal, "drawable", this.context.getPackageName())
                val drawable = res.getDrawable(resID)
                itemView.findViewById<ImageView>(R.id.item_icon).setImageDrawable(drawable)
            }
        }
    }
}