package com.cursoandroid.lojasrenner.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.lojasrenner.R
import com.cursoandroid.lojasrenner.model.Categoria
import com.cursoandroid.lojasrenner.model.Feed
import com.cursoandroid.lojasrenner.model.feed
import com.cursoandroid.lojasrenner.view.adapters.CategoriasVerticalAdapter
import com.google.gson.Gson


class ProdutoFragment : Fragment() {

    private var categoriasVerticalAdapter: CategoriasVerticalAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_produto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriasVerticalAdapter = CategoriasVerticalAdapter()
        view.findViewById<RecyclerView>(R.id.rv_vertical).adapter = categoriasVerticalAdapter
        requestCategoria()
    }

    private fun requestCategoria() {
        Thread{
            val feed = Gson().fromJson(feed(), Feed::class.java)

            activity?.runOnUiThread{
                categoriasVerticalAdapter?.add(feed.categorias as MutableList<Categoria>)
                println(feed)
            }
        }.start()
    }

}