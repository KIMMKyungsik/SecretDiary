package org.techtown.secretdiary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)


        val diaryEdit = findViewById<EditText>(R.id.diaryEdit)
        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEdit.setText(detailPreferences.getString("detail", ""))

        val runnable = Runnable {
            getSharedPreferences("diary",Context.MODE_PRIVATE).edit {
                putString("detail",diaryEdit.text.toString())

            }

        }

        diaryEdit.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }


    }

}

