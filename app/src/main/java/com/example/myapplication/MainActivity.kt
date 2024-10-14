package com.example.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

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

    val CHANNEL_ID = "my_channel_id"
    var allowNotification: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        // Inicializa os Spinners de Raça e Classe
        val raceSpinner: Spinner = findViewById(R.id.spinner_race)
        val raceAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.race_options,
            android.R.layout.simple_spinner_item
        )
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        raceSpinner.adapter = raceAdapter

        val classSpinner: Spinner = findViewById(R.id.spinner_class)
        val classAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.class_options,
            android.R.layout.simple_spinner_item
        )
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        classSpinner.adapter = classAdapter

        // Preenche o Spinner de Raça com um ArrayAdapter
        ArrayAdapter.createFromResource(
            this,
            R.array.race_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            raceSpinner.adapter = adapter
        }

        raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedRace = parent.getItemAtPosition(position).toString()
                applyRaceBonus(selectedRace)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
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
        submitButton.isEnabled = false

        fun updateSubmitButtonState() {
            val selectedRace = raceSpinner.selectedItem.toString()
            val selectedClass = classSpinner.selectedItem.toString()
            submitButton.isEnabled = selectedRace != "Click here to choose" && selectedClass != "Click here to choose"
        }

        raceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedRace = parent.getItemAtPosition(position).toString()
                applyRaceBonus(selectedRace)
                updateSubmitButtonState() // Check if we can enable the button
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateSubmitButtonState() // Check if we can enable the button
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

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
            val selectedRace = raceSpinner.selectedItem.toString()
            val selectedClass = classSpinner.selectedItem.toString()

            // Check if all points are distributed
            if (usedPoints < totalPoints) {
                Toast.makeText(this, "Please distribute all points before submitting!", Toast.LENGTH_SHORT).show()
            } else if (selectedRace == "Click here to choose" || selectedClass == "Click here to choose") {
                // Check if both race and class are selected
                Toast.makeText(this, "Please select both race and class.", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed with submission if both checks pass
                Toast.makeText(this, "Success! Your character has been created.", Toast.LENGTH_SHORT).show()
                if(allowNotification){
                    showNotification()
                }
            }
        }
    }

    private fun applyRaceBonus(race: String) {
        // Reset points to minimum before applying race bonuses
        strengthPoints = minPoints
        dexterityPoints = minPoints
        constitutionPoints = minPoints
        intelligencePoints = minPoints
        wisdomPoints = minPoints
        charismaPoints = minPoints

        when (race) {
            "Human" -> {
                strengthPoints += 1
                dexterityPoints += 1
                constitutionPoints += 1
                intelligencePoints += 1
                wisdomPoints += 1
                charismaPoints += 1
                Toast.makeText(this, "Bonus points applied: All attributes +1", Toast.LENGTH_SHORT).show()
            }
            "Elf" -> {
                dexterityPoints += 2
                Toast.makeText(this, "Bonus points applied: Dexterity +2", Toast.LENGTH_SHORT).show()
            }
            "Dwarf" -> {
                constitutionPoints += 2
                Toast.makeText(this, "Bonus points applied: Constitution +2", Toast.LENGTH_SHORT).show()
            }
            "Orc" -> {
                strengthPoints += 2
                constitutionPoints += 1
                Toast.makeText(this, "Bonus points applied: Strength +1, Constitution +1", Toast.LENGTH_SHORT).show()
            }
        }
        updateAttributesDisplay()
    }

    private fun updateAttributesDisplay() {
        strengthValue.text = strengthPoints.toString()
        dexterityValue.text = dexterityPoints.toString()
        constitutionValue.text = constitutionPoints.toString()
        intelligenceValue.text = intelligencePoints.toString()
        wisdomValue.text = wisdomPoints.toString()
        charismaValue.text = charismaPoints.toString()
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

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("my_channel_id", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                allowNotification = true
            } else {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
        } else {
            allowNotification = true
        }

    }

    @SuppressLint("MissingPermission")
    fun showNotification(){
        val openIntent = Intent(this, MainActivity::class.java)
        val openPendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, openIntent, PendingIntent.FLAG_IMMUTABLE)

        val closeIntent = Intent(this, NotificationReceiver::class.java).apply {
            action = "CLOSE_NOTIFICATION"
        }
        val closePendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, closeIntent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Hello! Are you around?")
            .setContentText("Your character has been created and it's waiting for you to play. ;)")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Congratulations! Your character has been created with all attributes distributed. Enjoy your adventure!"))
            .addAction(R.drawable.ic_open, "Open", openPendingIntent)
            .addAction(R.drawable.ic_close, "Close", closePendingIntent)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(1, builder.build())
    }

}
