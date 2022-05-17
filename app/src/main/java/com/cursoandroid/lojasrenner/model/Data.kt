package com.cursoandroid.lojasrenner.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Feed(
    val categorias: List<Categoria>
)
data class Categoria(
    val titulo: String,
    val produtos: List<Produto>
)

data class Produto(
    val fotoPrincipal: String?,
    val titulo: String?,
    val precoAvista: Double,
    val precoParcela: Double,
    val quantidadeParcela: Int,
    val urlProduto: String?,
    val cores: Array<Cores>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArray(Cores)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fotoPrincipal)
        parcel.writeString(titulo)
        parcel.writeDouble(precoAvista)
        parcel.writeDouble(precoParcela)
        parcel.writeInt(quantidadeParcela)
        parcel.writeString(urlProduto)
        parcel.writeTypedArray(cores, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}

data class Tamanho(
    val tamanho: String?

): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tamanho)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tamanho> {
        override fun createFromParcel(parcel: Parcel): Tamanho {
            return Tamanho(parcel)
        }

        override fun newArray(size: Int): Array<Tamanho?> {
            return arrayOfNulls(size)
        }
    }
}

data class Cores(
    val fotoProdutoCor: String?,
    val cor: String?,
    val tamanhos: List<Tamanho>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Tamanho)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fotoProdutoCor)
        parcel.writeString(cor)
        parcel.writeTypedList(tamanhos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cores> {
        override fun createFromParcel(parcel: Parcel): Cores {
            return Cores(parcel)
        }

        override fun newArray(size: Int): Array<Cores?> {
            return arrayOfNulls(size)
        }
    }
}
