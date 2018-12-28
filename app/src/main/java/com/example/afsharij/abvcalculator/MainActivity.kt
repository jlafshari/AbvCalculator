package com.example.afsharij.abvcalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view: View) {
        val originalGravity = getFloatFromEditText(original_gravity_value)
        val finalGravity = getFloatFromEditText(final_gravity_value)
        if (originalGravity != null && finalGravity != null) {
            val alcoholByVolume = getAlcoholByVolume(originalGravity, finalGravity)
            abv_heading.text = getString(R.string.abv_heading, alcoholByVolume.toString())
            abv_heading.visibility = View.VISIBLE
        }
        else {
            abv_heading.text = null
            abv_heading.visibility = View.INVISIBLE
        }

        if (originalGravity == null) original_gravity_value.error = getString(R.string.invalid_original_gravity)
        else original_gravity_value.error = null

        if (finalGravity == null) final_gravity_value.error = getString(R.string.invalid_final_gravity)
        else final_gravity_value.error = null
    }

    private fun getFloatFromEditText(editText: EditText) = editText.text.toString().trim().toFloatOrNull()

    private fun getAlcoholByVolume(originalGravity: Float, finalGravity: Float) : Float {
        val alcoholByVolume = (1.05f * (originalGravity - finalGravity)) / (finalGravity * 0.79f) * 100
        return String.format("%.2f", alcoholByVolume).toFloat()
    }
}
