package com.example.ejerciciomovil


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.ejerciciomovil.Constante.Constantes
import com.example.ejerciciomovil.Interface.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.button)
        val email = findViewById<EditText>(R.id.edtEmail)
        val pass = findViewById<EditText>(R.id.edtPassword)


        boton.setOnClickListener {
            validar(email.text.toString(), pass.text.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validar(email: String, pass: String) = CoroutineScope(Dispatchers.IO).launch{

        val retrofit = Retrofit.Builder()
            .baseUrl("http://testandroid.macropay.com.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(APIService::class.java)
        val call = service.getTokenAuth(email,pass)

        if (call.success){
            val ejercicio1 = JWT(call.token)
            Constantes.token = call.token.toString()
            val myIntent = Intent(this@MainActivity, DashActivity::class.java)
             //Optional parameters
            this@MainActivity.startActivity(myIntent)
        }else{
            Log.e("Error", "")
        }

    }
}