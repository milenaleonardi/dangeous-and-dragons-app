package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var totalPoints = 27
    private val minPoints = 8
    private val maxPoints = 15
    private var usedPoints = 0

    private lateinit var raceSpinner: Spinner
    private lateinit var classSpinner: Spinner

    private lateinit var strengthValue: TextView
    private lateinit var dexterityValue: TextView
    private lateinit var constitutionValue: TextView
    private lateinit var intelligenceValue: TextView
    private lateinit var wisdomValue: TextView
    private lateinit var charismaValue: TextView

    private var strengthPoints = minPoints
    private var dexterityPoints = minPoints
    private var constitutionPoints = minPoints
    private var intelligencePoints = minPoints
    private var wisdomPoints = minPoints
    private var charismaPoints = minPoints

    private lateinit var textPointsLeft: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa os Spinners de Raça e Classe
        raceSpinner = findViewById(R.id.spinner_race)
        classSpinner = findViewById(R.id.spinner_class)

        // Preenche o Spinner de Raça com um ArrayAdapter
        ArrayAdapter.createFromResource(
            this,
            R.array.race_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            raceSpinner.adapter = adapter
        }

        // Preenche o Spinner de Classe com um ArrayAdapter
        ArrayAdapter.createFromResource(
            this,
            R.array.class_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            classSpinner.adapter = adapter
        }

        // Inicializa os TextViews de valor dos atributos
        strengthValue = findViewById(R.id.text_strength_value)
        dexterityValue = findViewById(R.id.text_dexterity_value)
        constitutionValue = findViewById(R.id.text_constitution_value)
        intelligenceValue = findViewById(R.id.text_intelligence_value)
        wisdomValue = findViewById(R.id.text_wisdom_value)
        charismaValue = findViewById(R.id.text_charisma_value)

        textPointsLeft = findViewById(R.id.text_points_left)
        submitButton = findViewById(R.id.button_submit)

        updatePointsLeft()

        findViewById<Button>(R.id.button_decrease_strength).setOnClickListener {
            decreaseAttribute("strength")
        }
        findViewById<Button>(R.id.button_increase_strength).setOnClickListener {
            increaseAttribute("strength")
        }

        findViewById<Button>(R.id.button_decrease_dexterity).setOnClickListener {
            decreaseAttribute("dexterity")
        }
        findViewById<Button>(R.id.button_increase_dexterity).setOnClickListener {
            increaseAttribute("dexterity")
        }

        findViewById<Button>(R.id.button_decrease_constitution).setOnClickListener {
            decreaseAttribute("constitution")
        }
        findViewById<Button>(R.id.button_increase_constitution).setOnClickListener {
            increaseAttribute("constitution")
        }

        findViewById<Button>(R.id.button_decrease_intelligence).setOnClickListener {
            decreaseAttribute("intelligence")
        }
        findViewById<Button>(R.id.button_increase_intelligence).setOnClickListener {
            increaseAttribute("intelligence")
        }

        findViewById<Button>(R.id.button_decrease_wisdom).setOnClickListener {
            decreaseAttribute("wisdom")
        }
        findViewById<Button>(R.id.button_increase_wisdom).setOnClickListener {
            increaseAttribute("wisdom")
        }

        findViewById<Button>(R.id.button_decrease_charisma).setOnClickListener {
            decreaseAttribute("charisma")
        }
        findViewById<Button>(R.id.button_increase_charisma).setOnClickListener {
            increaseAttribute("charisma")
        }

        submitButton.setOnClickListener {
            if (usedPoints < totalPoints) {
                Toast.makeText(this, "Please distribute all points before submitting!", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed with submission
                Toast.makeText(this, "Success! Your character has been created.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun increaseAttribute(attribute: String) {
        if (totalPoints <= 0) return

        if (usedPoints < totalPoints) {
            when (attribute) {
                "strength" -> {
                    if (strengthPoints < maxPoints) {
                        strengthPoints++
                        totalPoints--
                        strengthValue.text = strengthPoints.toString()
                    }
                }

                "dexterity" -> {
                    if (dexterityPoints < maxPoints) {
                        dexterityPoints++
                        totalPoints--
                        dexterityValue.text = dexterityPoints.toString()
                    }
                }

                "constitution" -> {
                    if (constitutionPoints < maxPoints) {
                        constitutionPoints++
                        totalPoints--
                        constitutionValue.text = constitutionPoints.toString()
                    }
                }

                "intelligence" -> {
                    if (intelligencePoints < maxPoints) {
                        intelligencePoints++
                        totalPoints--
                        intelligenceValue.text = intelligencePoints.toString()
                    }
                }

                "wisdom" -> {
                    if (wisdomPoints < maxPoints) {
                        wisdomPoints++
                        totalPoints--
                        wisdomValue.text = wisdomPoints.toString()
                    }
                }

                "charisma" -> {
                    if (charismaPoints < maxPoints) {
                        charismaPoints++
                        totalPoints--
                        charismaValue.text = charismaPoints.toString()
                    }
                }
            }
            updatePointsLeft()
        }
    }

    private fun decreaseAttribute(attribute: String) {
        when (attribute) {
            "strength" -> {
                if (strengthPoints > minPoints) {
                    strengthPoints--
                    totalPoints++
                    strengthValue.text = strengthPoints.toString()
                }
            }

            "dexterity" -> {
                if (dexterityPoints > minPoints) {
                    dexterityPoints--
                    totalPoints++
                    dexterityValue.text = dexterityPoints.toString()
                }
            }

            "constitution" -> {
                if (constitutionPoints > minPoints) {
                    constitutionPoints--
                    totalPoints++
                    constitutionValue.text = constitutionPoints.toString()
                }
            }

            "intelligence" -> {
                if (intelligencePoints > minPoints) {
                    intelligencePoints--
                    totalPoints++
                    intelligenceValue.text = intelligencePoints.toString()
                }
            }

            "wisdom" -> {
                if (wisdomPoints > minPoints) {
                    wisdomPoints--
                    totalPoints++
                    wisdomValue.text = wisdomPoints.toString()
                }
            }

            "charisma" -> {
                if (charismaPoints > minPoints) {
                    charismaPoints--
                    totalPoints++
                    charismaValue.text = charismaPoints.toString()
                }
            }
        }
        updatePointsLeft()
    }

    private fun updatePointsLeft() {
        val pointsLeft = totalPoints - usedPoints
        textPointsLeft.text = "Points left: $pointsLeft"
    }
}
