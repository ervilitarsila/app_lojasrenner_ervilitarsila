package com.cursoandroid.lojasrenner.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.lojasrenner.R
import com.cursoandroid.lojasrenner.model.Categoria
import com.cursoandroid.lojasrenner.model.Produto
import com.cursoandroid.lojasrenner.view.activities.DetalheProdutoActivity

//import kotlin.android.synthetic.main.item_produto.view.*

class CategoriasVerticalAdapter  (
    private val categorias: MutableList<Categoria> = mutableListOf()
        ): RecyclerView.Adapter<CategoriasVerticalAdapter.VerticalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHolder = VerticalHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_categoria_produto, parent, false)
    )

    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
        holder.bind(categorias[position])
    }

    fun add(categorias: MutableList<Categoria> ){
        this.categorias.clear()
        this.categorias.addAll(categorias)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categorias.size

    class VerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        CategoriasHorizontalAdapter.ClickProduto {
        fun bind(categoria: Categoria){
            with(itemView){
                this.findViewById<TextView>(R.id.txtTituloCategoria).setText(categoria.titulo)

                this.findViewById<RecyclerView>(R.id.rv_horizontal).layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.HORIZONTAL,false
                )
                this.findViewById<RecyclerView>(R.id.rv_horizontal).adapter = CategoriasHorizontalAdapter(categoria.produtos, this@VerticalHolder)
            }

        }

        override fun clickProduto(produto: Produto) {

            val intent = Intent(itemView.context, DetalheProdutoActivity::class.java)
            intent.putExtra("produto",produto)

            itemView.context.startActivity(intent)
        }

    }
}