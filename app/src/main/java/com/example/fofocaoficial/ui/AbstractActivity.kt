package com.example.fofocaoficial.ui


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        onInject()
    }

    @LayoutRes

    protected abstract fun getLayout(): Int
    protected abstract fun onInject()
}

//criada para deixar o código mais reutilizável, com métodos abstratos e fomos para o articleActivity