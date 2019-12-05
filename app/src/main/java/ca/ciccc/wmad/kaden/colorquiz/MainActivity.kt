package ca.ciccc.wmad.kaden.colorquiz

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textViewScore: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button

    private val arrayOfColours = Colour.values()
    private val sizeOfColourArray = arrayOfColours.size

    private var rightAnswer: Int = 0
    private var curScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewScore = findViewById(R.id.textView_score)
        textViewQuestion = findViewById(R.id.textView_question)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)
    }

    override fun onStart() {
        super.onStart()

        initialize()
    }

    private fun initialize() {
        setScore(0)
        generateRandomColor()
    }

    private fun setScore(s: Int) {
        curScore = s
        textViewScore.text = String.format("Score: %d", curScore)
    }

    private fun generateRandomColor() {
        val random = Random(System.currentTimeMillis())
        val ranNum1 = random.nextInt(sizeOfColourArray)
        var ranNum2: Int
        do {
            ranNum2 = random.nextInt(sizeOfColourArray)
        } while (ranNum1 == ranNum2)

        buttonLeft.setBackgroundColor(getColorCode(arrayOfColours[ranNum1]))
        buttonRight.setBackgroundColor(getColorCode(arrayOfColours[ranNum2]))

        rightAnswer = random.nextInt(2)
        textViewQuestion.text =
            arrayOfColours[if (rightAnswer == 0) ranNum1 else ranNum2].name
                .replace("_", " ").capitalize()
    }

    fun answer(view: View) {
        val chosen = when (view.id) {
            R.id.button_left -> 0
            R.id.button_right -> 1
            else -> -1
        }
        if (chosen == rightAnswer) {
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show()
            setScore(++curScore)
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        generateRandomColor()
    }
}

fun getColorCode(c: Colour) = when (c) {
    Colour.WHITE -> Color.WHITE
    Colour.LIGHT_GRAY -> Color.LTGRAY
    Colour.GRAY -> Color.GRAY
    Colour.DARK_GRAY -> Color.DKGRAY
    Colour.BLACK -> Color.BLACK
    Colour.RED -> Color.RED
    Colour.YELLOW -> Color.YELLOW
    Colour.GREEN -> Color.GREEN
    Colour.MAGENTA -> Color.MAGENTA
    Colour.CYAN -> Color.CYAN
    Colour.BLUE -> Color.BLUE
}

enum class Colour {
    WHITE, LIGHT_GRAY, GRAY, DARK_GRAY, BLACK, RED, YELLOW, GREEN, MAGENTA, CYAN, BLUE
}
