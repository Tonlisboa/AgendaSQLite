package br.edu.ifsp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.model.Contato
import br.edu.ifsp.R


class ContatoAdapter(val contatosLista:ArrayList<Contato>):RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    var listener:ContatoListener?=null

    fun setClickListener(listener:ContatoListener)
    {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContatoAdapter.ContatoViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.contato_celula, parent, false)
      return  ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoAdapter.ContatoViewHolder, position: Int) {
        holder.nomeVH.text = contatosLista[position].nome
        holder.foneVH.text = contatosLista[position].fone
    }

    override fun getItemCount(): Int {
        return contatosLista.size
    }

    inner class ContatoViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val nomeVH = view.findViewById<TextView>(R.id.nome)
        val foneVH = view.findViewById<TextView>(R.id.fone)

        init {
            view.setOnClickListener {
               listener?.onItemClick(adapterPosition)
            }
        }

    }

    interface ContatoListener
    {
        fun onItemClick(pos: Int)
    }

}