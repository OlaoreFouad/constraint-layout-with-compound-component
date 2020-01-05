package dev.iamfoodie.constraintlayoutcompoundcomponents


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.color_selector.view.*

class ColorSelector @JvmOverloads
    constructor(ctx: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    private var colors = listOf(Color.RED, Color.GRAY, Color.BLACK, Color.YELLOW, Color.BLUE, Color.MAGENTA)
    private var selectedColorIndex: Int
    var isChecked = true

    var colorSelectedListener: ((Int) -> Unit)? = null
    var selectedColor: Int? = null
        set(value) {
            if (colors.contains(value)) {
                selectedColorIndex = colors.indexOf(value)
                setColor()
            }
        }

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.color_selector, this)

        val typedArray = ctx.obtainStyledAttributes(attributeSet, R.styleable.ColorSelector)
        colors = typedArray.getTextArray(R.styleable.ColorSelector_colors).map { Color.parseColor(it.toString()) }
        val defaultIndex = typedArray.getInt(R.styleable.ColorSelector_defaultColorIndex, 0)
        typedArray.recycle()

        selectedColorIndex = defaultIndex
        setColor()
        colorSelectorCheckbox.setOnCheckedChangeListener { _, b ->
            isChecked = b
        }
        colorSelectorCheckbox.isChecked = true

        left_arrow.setOnClickListener { onLeftArrowSelected() }
        right_arrow.setOnClickListener { onRightArrowSelected() }
    }

    private fun onLeftArrowSelected() {
        if (selectedColorIndex == 0) {
            selectedColorIndex = colors.lastIndex
        } else {
            selectedColorIndex--
        }
        setColor()
    }

    private fun onRightArrowSelected() {
        if (selectedColorIndex == colors.lastIndex) {
            selectedColorIndex = 0
        } else {
            selectedColorIndex++
        }
        setColor()
    }

    private fun setColor() {
        if (isChecked) {
            colorSelector.setBackgroundColor(colors[selectedColorIndex])
            colorSelectedListener?.invoke(colors[selectedColorIndex])
        }
    }

}