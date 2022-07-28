package com.example.ejerciciomovil

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.auth0.android.jwt.JWT
import com.example.ejerciciomovil.Constante.Constantes
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception

class DashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        val ejercicio1 = JWT(Constantes.token.toString())
        val sesion: String? = ejercicio1.getClaim("titular").asString()

        val txtBienvenida = findViewById<TextView>(R.id.txtBienvenida)
        val imgCodeBar = findViewById<ImageView>(R.id.imageCode)
        val txtCodigo = findViewById<TextView>(R.id.txtCodigo)

        txtBienvenida.setText(sesion)
        txtCodigo.setText(Constantes.token)

        try {

        var barCoder : BarcodeEncoder = BarcodeEncoder()

        var bitmap: Bitmap = barCoder.encodeBitmap(
            Constantes.token,
            BarcodeFormat.QR_CODE,
            400,
            400,
        )

            imgCodeBar.setImageBitmap(bitmap)


        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}