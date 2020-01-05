package dev.iamfoodie.constraintlayoutcompoundcomponents

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentColor: Int = Color.GRAY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        color_selector.colorSelectedListener = { currentColor = it }
        color_selector.selectedColor = currentColor
        check_view.setBackgroundColor(currentColor)
        check_button.setOnClickListener { check_view.setBackgroundColor(currentColor) }
    }
}
