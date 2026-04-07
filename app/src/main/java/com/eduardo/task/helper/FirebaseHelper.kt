package com.eduardo.task.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    companion object {

        // Retorna a instância do Realtime Database
        fun getDatabase(): DatabaseReference {
            return FirebaseDatabase.getInstance().reference
        }

        // Retorna o ID único do usuário logado
        fun getIdUser(): String {
            return FirebaseAuth.getInstance().currentUser?.uid ?: ""
        }

        // Verifica se o usuário está autenticado
        fun getAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }
}