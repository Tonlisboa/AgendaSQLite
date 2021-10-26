package br.edu.ifsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.Data.ContatoAdapter
import br.edu.ifsp.Data.DatabaseHelper
import br.edu.ifsp.Model.Contato

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var contatosLista = ArrayList<Contato>()
    var contatoAdapter: ContatoAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateUI()

    }

    fun updateUI()
    {
        contatosLista = db.listarContatos()
        contatoAdapter = ContatoAdapter(contatosLista)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = contatoAdapter

    }
}