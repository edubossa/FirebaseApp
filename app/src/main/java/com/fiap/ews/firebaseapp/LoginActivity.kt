package com.fiap.ews.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import com.fiap.ews.firebaseapp.extensions.getText
import android.R.attr.password
import android.util.Log


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {

            // mAuth.createUserWithEmailAndPassword(email, password)
            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
                            val user = mAuth.currentUser
                            Log.d("LoginActivity", user.toString())
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, task.exception?.message,
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)

                            Log.e("LoginActivity", "Erro ao criar usuario")
                        }

                        // ...
                    }

        }

        btlogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(FragmentActivity.TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            Log.d("LoginActivity", user.toString())
                            //updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
                            //Toast.makeText(this@EmailPasswordActivity, "Authentication failed.",
                              //      Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                            Log.e("LoginActivity", "Erro ao logar")
                        }

                        // ...
                    }
        }


        btEmail.setOnClickListener {

            //val user: FirebaseUser? = mAuth.currentUser
            // OU
            val user = mAuth.currentUser

            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener {
                        task -> if (task.isSuccessful) {

                            Log.d("LoginActivity","Email Enviado com Sucesso!")

                        } else {
                        Log.e("LoginActivity", "Erro ao Enviar E-mail")
                        }
                })


        }


        btLogout.setOnClickListener {
            mAuth.signOut()
        }



    }
}
