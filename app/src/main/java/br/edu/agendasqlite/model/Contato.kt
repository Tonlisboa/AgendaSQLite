package br.edu.agendasqlite.model

import java.io.Serializable

class Contato ( var id: Int? = null,
                var nome:String="",
                var fone:String="",
                var email:String=""
              ):Serializable