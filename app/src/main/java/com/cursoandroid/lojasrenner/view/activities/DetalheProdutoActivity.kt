package com.cursoandroid.lojasrenner.view.activities

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.cursoandroid.lojasrenner.R
import com.cursoandroid.lojasrenner.model.Cores
import com.cursoandroid.lojasrenner.model.Produto
import com.cursoandroid.lojasrenner.model.Tamanho

class DetalheProdutoActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    lateinit var produto: Produto
    lateinit var spinCores: Spinner
    lateinit var spinTamanhos: Spinner
    lateinit var imageProduto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LojasRenner)
        setContentView(R.layout.activity_detalhe_produto)
        supportActionBar?.hide()

        produto = intent.getParcelableExtra<Produto>("produto")!!

        carregarInformacoesProduto()


    }

    private fun carregarInformacoesProduto() {
        var precoParcelado: String = "(" + produto.quantidadeParcela.toString()
        precoParcelado += String.format("x R$ %.2f)", produto.precoParcela)

        findViewById<TextView>(R.id.txtTituloProduto).setText(produto.titulo)
        findViewById<TextView>(R.id.txtPrecoAvista).setText(
            String.format("R$ %.2f", produto.precoAvista))
        findViewById<TextView>(R.id.txtPrecoParcelado).setText(precoParcelado)

        val res: Resources =  getResources()
        val resID: Int =
            res.getIdentifier(produto.fotoPrincipal, "drawable", this.getPackageName())
        val drawable = res.getDrawable(resID)
        imageProduto = findViewById<ImageView>(R.id.image_produto)
        imageProduto.setImageDrawable(drawable)

        //======= Cores ======
        val stockList: MutableList<String> = ArrayList()
        produto.cores?.forEachIndexed { index, element ->
            stockList.add(produto.cores?.get(index)?.cor.toString())
        }

        var stockArr: Array<String?>? = arrayOfNulls(stockList.size)
        stockArr = stockList.toTypedArray()


        //======= Tamanhos ======
        val stockList2: MutableList<String> = ArrayList()
        produto.cores?.get(0)?.tamanhos?.forEachIndexed { index, element ->
            stockList2.add(produto.cores?.get(0)?.tamanhos!!.get(index)?.tamanho.toString())
        }

        var stockArr2: Array<String?>? = arrayOfNulls(stockList2.size)
        stockArr2 = stockList2.toTypedArray()

        spinCores = findViewById<Spinner>(R.id.spinnerCores)
        spinTamanhos = findViewById<Spinner>(R.id.spinnerTamanhos)

        spinCores!!.setOnItemSelectedListener(this)
        spinTamanhos!!.setOnItemSelectedListener(this)


        val adapterViewCores = ArrayAdapter(this,android.R.layout.simple_spinner_item,stockArr)
        val adapterViewTamanhos = ArrayAdapter(this,android.R.layout.simple_spinner_item,stockArr2)

        adapterViewCores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterViewTamanhos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinCores!!.setAdapter(adapterViewCores)
        spinTamanhos!!.setAdapter(adapterViewTamanhos)
    }

    fun voltar(view: View){
        finish()
    }

    fun comprar(view: View){
        val url = produto.urlProduto

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(Intent.createChooser(intent, "Abrir com"))
    }



    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        if(p0?.getId() == R.id.spinnerCores){

            val res: Resources =  getResources()
            val resID: Int =
                res.getIdentifier(produto.cores?.get(p2)?.fotoProdutoCor, "drawable", this.getPackageName())
            val drawable = res.getDrawable(resID)
            imageProduto.setImageDrawable(drawable)


            //======= Tamanhos ======
            val stockList2: MutableList<String> = ArrayList()
            produto.cores?.get(p2)?.tamanhos?.forEachIndexed { index, element ->
                stockList2.add(produto.cores?.get(p2)?.tamanhos!!.get(index)?.tamanho.toString())
            }

            var stockArr2: Array<String?>? = arrayOfNulls(stockList2.size)
            stockArr2 = stockList2.toTypedArray()

            spinTamanhos!!.setOnItemSelectedListener(this)

            val adapterViewTamanhos = ArrayAdapter(this,android.R.layout.simple_spinner_item,stockArr2)
            adapterViewTamanhos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinTamanhos!!.setAdapter(adapterViewTamanhos)
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}


