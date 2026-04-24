package com.example.atividadecomplementar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etLarguraA: EditText
    private lateinit var etLarguraB: EditText
    private lateinit var etAlturaA: EditText
    private lateinit var etAlturaB: EditText
    private lateinit var tvPerimetro: TextView
    private lateinit var tvArea: TextView
    private lateinit var btnCalcularArea: Button

    private lateinit var etTempo: EditText
    private lateinit var etVelocidade: EditText
    private lateinit var etMediaCombustivel: EditText
    private lateinit var tvCombustivel: TextView
    private lateinit var btnCalcularCombustivel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLarguraA = findViewById(R.id.etLarguraA)
        etLarguraB = findViewById(R.id.etLarguraB)
        etAlturaA = findViewById(R.id.etAlturaA)
        etAlturaB = findViewById(R.id.etAlturaB)
        tvPerimetro = findViewById(R.id.tvPerimetro)
        tvArea = findViewById(R.id.tvArea)
        btnCalcularArea = findViewById(R.id.btnCalcularArea)

        etTempo = findViewById(R.id.etTempo)
        etVelocidade = findViewById(R.id.etVelocidade)
        etMediaCombustivel = findViewById(R.id.etMediaCombustivel)
        tvCombustivel = findViewById(R.id.tvCombustivel)
        btnCalcularCombustivel = findViewById(R.id.btnCalcularCombustivel)

        btnCalcularArea.setOnClickListener {
            calcularAreaPerimetro()
        }

        btnCalcularCombustivel.setOnClickListener {
            calcularCombustivel()
        }
    }

    private fun calcularAreaPerimetro() {
        val larguraAStr = etLarguraA.text.toString()
        val alturaAStr = etAlturaA.text.toString()

        if (larguraAStr.isEmpty() || alturaAStr.isEmpty()) {
            Toast.makeText(this, "Preencha pelo menos Largura A e Altura A!", Toast.LENGTH_SHORT).show()
            return
        }

        val larguraA = larguraAStr.toDoubleOrNull()
        val alturaA = alturaAStr.toDoubleOrNull()

        if (larguraA == null || alturaA == null) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        val area: Double
        val perimetro: Double

        if (larguraA == alturaA) {
            //quadrado
            area = larguraA * larguraA
            perimetro = 4 * larguraA
        } else {
            //retângulo
            area = larguraA * alturaA
            perimetro = 2 * (larguraA + alturaA)
        }

        tvArea.text = formatarResultado(area)
        tvPerimetro.text = formatarResultado(perimetro)
    }

    private fun calcularCombustivel() {
        val tempoStr = etTempo.text.toString()
        val velocidadeStr = etVelocidade.text.toString()
        val mediaStr = etMediaCombustivel.text.toString()

        if (tempoStr.isEmpty() || velocidadeStr.isEmpty() || mediaStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos de combustível!", Toast.LENGTH_SHORT).show()
            return
        }

        val tempo = tempoStr.toDoubleOrNull()
        val velocidade = velocidadeStr.toDoubleOrNull()
        val media = mediaStr.toDoubleOrNull()

        if (tempo == null || velocidade == null || media == null) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (media == 0.0) {
            Toast.makeText(this, "A média de combustível não pode ser zero!", Toast.LENGTH_SHORT).show()
            return
        }

        val distancia = tempo * velocidade        //km
        val litros = distancia / media            //litros

        tvCombustivel.text = formatarResultado(litros)
    }

    private fun formatarResultado(valor: Double): String {
        return if (valor % 1 == 0.0) {
            valor.toLong().toString()
        } else {
            String.format("%.2f", valor)
        }
    }
}
