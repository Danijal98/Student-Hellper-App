package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.R
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.extensions.toPx
import timber.log.Timber

class Statistics : View{

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs) {
        getColor(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        getColor(context, attrs)
    }

    private var rect: Rect = Rect()
    private lateinit var paint: Paint

    var heights = listOf<Int>()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var max = 0
        heights.forEach {
            if(it > max){
                max = it
            }
        }

        if(max == 0){ return }

        var begin = 0
        var height_offset = height / max
        val right_offset = width / 5 - 4
        val bottom = height

        heights.forEach {
            val left = begin
            val top = height - height_offset * it
            val right = left + right_offset
            begin = right + 5
            rect.set(left, top, right, bottom)
            canvas?.drawRect(rect, paint)
        }
    }

    private fun getColor(context: Context, attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Statistics)
        val barColor = typedArray.getColor(R.styleable.Statistics_barColor, 0)
        typedArray.recycle()
        paint = Paint().also {
            it.isAntiAlias = true
            //it.color = ContextCompat.getColor(context, R.color.secondaryColor)
            it.color = barColor
            it.style = Paint.Style.FILL
        }
    }

}