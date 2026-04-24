package com.example.atividadecomplementar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Views - Área e Perímetro
    private lateinit var etLarguraA: EditText
    private lateinit var etLarguraB: EditText
    private lateinit var etAlturaA: EditText
    private lateinit var etAlturaB: EditText
    private lateinit var tvPerimetro: TextView
    private lateinit var tvArea: TextView
    private lateinit var btnCalcularArea: Button

    // Views - Combustível
    private lateinit var etTempo: EditText
    private lateinit var etVelocidade: EditText
    private lateinit var etMediaCombustivel: EditText
    private lateinit var tvCombustivel: TextView
    private lateinit var btnCalcularCombustivel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa as views de Área e Perímetro
        etLarguraA = findViewById(R.id.etLarguraA)
        etLarguraB = findViewById(R.id.etLarguraB)
        etAlturaA = findViewById(R.id.etAlturaA)
        etAlturaB = findViewById(R.id.etAlturaB)
        tvPerimetro = findViewById(R.id.tvPerimetro)
        tvArea = findViewById(R.id.tvArea)
        btnCalcularArea = findViewById(R.id.btnCalcularArea)

        // Inicializa as views de Combustível
        etTempo = findViewById(R.id.etTempo)
        etVelocidade = findViewById(R.id.etVelocidade)
        etMediaCombustivel = findViewById(R.id.etMediaCombustivel)
        tvCombustivel = findViewById(R.id.tvCombustivel)
        btnCalcularCombustivel = findViewById(R.id.btnCalcularCombustivel)

        // Listener do botão Calcular Área/Perímetro
        btnCalcularArea.setOnClickListener {
            calcularAreaPerimetro()
        }

        // Listener do botão Calcular Combustível
        btnCalcularCombustivel.setOnClickListener {
            calcularCombustivel()
        }
    }

    /**
     * Calcula a Área e o Perímetro de um Quadrado ou Retângulo.
     *
     * - Quadrado: Largura A == Altura A (e B não preenchido)
     *     Área      = L * L
     *     Perímetro = 4 * L
     *
     * - Retângulo: usa os pares (LarguraA, AlturaA) para o primeiro retângulo
     *              e (LarguraB, AlturaB) para o segundo (campo B opcional).
     *     Área      = Base * Altura
     *     Perímetro = 2 * (Base + Altura)
     *
     * Lógica adotada (seguindo o mockup com 4 campos):
     *   - Se apenas A for preenchido com valores iguais (LarguraA == AlturaA) → Quadrado
     *   - Caso contrário → Retângulo usando LarguraA como base e AlturaA como altura
     *   - LarguraB e AlturaB são usados como segundo retângulo (opcional)
     */
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

        // Verifica se é Quadrado (base == altura) ou Retângulo
        if (larguraA == alturaA) {
            // Quadrado
            area = larguraA * larguraA           // L * L
            perimetro = 4 * larguraA             // 4 * L
        } else {
            // Retângulo
            area = larguraA * alturaA            // B * A
            perimetro = 2 * (larguraA + alturaA) // 2 * (B + A)
        }

        // Exibe os resultados
        tvArea.text = formatarResultado(area)
        tvPerimetro.text = formatarResultado(perimetro)
    }

    /**
     * Calcula a quantidade de litros de combustível para uma viagem.
     *
     * Fórmulas:
     *   distância    = tempo (h) × velocidade (km/h)
     *   litros usados = distância / média (km/L)
     */
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

        val distancia = tempo * velocidade        // km
        val litros = distancia / media            // litros

        tvCombustivel.text = formatarResultado(litros)
    }

    /**
     * Formata o resultado:
     * - Se for número inteiro exibe sem casas decimais (ex: 25)
     * - Caso contrário exibe com 2 casas decimais (ex: 12.50)
     */
    private fun formatarResultado(valor: Double): String {
        return if (valor % 1 == 0.0) {
            valor.toLong().toString()
        } else {
            String.format("%.2f", valor)
        }
    }
}
