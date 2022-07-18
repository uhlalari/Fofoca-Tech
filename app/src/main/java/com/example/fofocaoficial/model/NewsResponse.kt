package com.example.fofocaoficial.model

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)

//Após isso fomos para estudo de coroutines ( que é uma feature do Kotlin, usada para escrever códigos assincronos sem usar o padrao de callbak
//após isso fomos para configurar o retrofit criando o pacote network e a news API dentro dele