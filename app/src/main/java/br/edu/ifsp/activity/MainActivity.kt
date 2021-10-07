package br.edu.ifsp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.data.ContatoAdapter
import br.edu.ifsp.data.DatabaseHelper
import br.edu.ifsp.model.Contato
import br.edu.ifsp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var contatosLista = ArrayList<Contato>()
    lateinit var contatoAdapter: ContatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(applicationContext, CadastroActivity::class.java)
            startActivity(intent)
        }

        updateUI()

    }

    fun updateUI()
    {
        contatosLista = db.listarContatos()
        contatoAdapter = ContatoAdapter(contatosLista)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = contatoAdapter

        var listener = object :ContatoAdapter.ContatoListener{
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, DetalheActivity::class.java)
                val c = contatoAdapter.contatosLista[pos]
                intent.putExtra("contato", c)
                startActivity(intent)
            }


        }
        contatoAdapter.setClickListener(listener)

    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

}