package com.example.klitaviy.multiplebottomsheetstest

import android.os.Bundle
import android.support.annotation.DimenRes
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var firstBottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var secondBottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var thirdBottomSheet: BottomSheetBehavior<LinearLayout>

    private var isActive = false
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        initSheets()
    }

    override fun onNavigateUp(): Boolean {
        return if (isActive) {
            setSheetsToDefault()
            false
        } else {
            super.onNavigateUp()
        }
    }

    override fun onBackPressed() {
        if (isActive) {
            setSheetsToDefault()
        } else {
            super.onBackPressed()
        }
    }

    private fun setSheetsToDefault() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        isActive = false
        firstBottomSheet.peekHeight = getSheetHeight(R.dimen.first_peek_height, offset)
        secondBottomSheet.peekHeight = getSheetHeight(R.dimen.second_peek_height, offset)
        thirdBottomSheet.peekHeight = getSheetHeight(R.dimen.third_peek_height, offset)

        firstBSH.bringToFront()
        secondBSH.bringToFront()
        thirdBSH.bringToFront()

        firstBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        secondBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        thirdBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initSheets() {
        contentView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                contentView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val contentHeight = contentView.height

                firstBottomSheet = BottomSheetBehavior.from(firstBSH)
                secondBottomSheet = BottomSheetBehavior.from(secondBSH)
                thirdBottomSheet = BottomSheetBehavior.from(thirdBSH)

                offset = contentHeight - getSheetHeight(R.dimen.first_peek_height)
                setSheetsToDefault()

                firstTitle.setOnClickListener {
                    toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
                    isActive = true
                    secondBSH.bringToFront()
                    thirdBSH.bringToFront()

                    firstBottomSheet.peekHeight = getSheetHeight(R.dimen.first_peek_height, offset)
                    secondBottomSheet.peekHeight = getSheetHeight(R.dimen.second_peek_height, offset)
                    thirdBottomSheet.peekHeight = getSheetHeight(R.dimen.third_peek_height, offset)

                    firstBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                    secondBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    thirdBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                secondTitle.setOnClickListener {
                    toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
                    isActive = true
                    firstBSH.bringToFront()
                    thirdBSH.bringToFront()

                    firstBottomSheet.peekHeight = getSheetHeight(R.dimen.second_peek_height, offset)
                    thirdBottomSheet.peekHeight = getSheetHeight(R.dimen.third_peek_height, offset)

                    firstBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    secondBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                    thirdBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                }
                thirdTitle.setOnClickListener {
                    toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
                    isActive = true
                    firstBSH.bringToFront()
                    secondBSH.bringToFront()

                    firstBottomSheet.peekHeight = getSheetHeight(R.dimen.second_peek_height, offset)
                    secondBottomSheet.peekHeight = getSheetHeight(R.dimen.third_peek_height, offset)

                    firstBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    secondBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    thirdBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        })


    }

    private fun getSheetHeight(@DimenRes id: Int, offset: Int = 0) = resources.getDimensionPixelSize(id) + if (isActive) {
        0
    } else {
        offset
    }
}
