package br.edu.agendasqlite.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.agendasqlite.data.ContatoAdapter
import br.edu.agendasqlite.data.DatabaseHelper
import br.edu.agendasqlite.model.Contato
import br.edu.agendasqlite.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val db = DatabaseHelper(this)
    private var contatosLista = ArrayList<Contato>()
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

    private fun updateUI()
    {
        contatosLista = db.listarContatos()
        contatoAdapter = ContatoAdapter(contatosLista)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = contatoAdapter

        val listener = object :ContatoAdapter.ContatoListener{
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, DetalheActivity::class.java)
                val c = contatoAdapter.contatosListaFilterable[pos]
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                contatoAdapter.filter.filter(p0)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }


}