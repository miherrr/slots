package com.example.prime.testapplication.game

import android.content.res.AssetFileDescriptor
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.prime.testapplication.R
import kotlinx.android.synthetic.main.new_activity.*
import org.jetbrains.anko.toast
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter1: ImagesAdapter
    private lateinit var adapter2: ImagesAdapter
    private lateinit var adapter3: ImagesAdapter
    private lateinit var adapter4: ImagesAdapter
    private lateinit var adapter5: ImagesAdapter

    private val list1: ArrayList<SlotModel> = arrayListOf()
    private val list2: ArrayList<SlotModel> = arrayListOf()
    private val list3: ArrayList<SlotModel> = arrayListOf()
    private val list4: ArrayList<SlotModel> = arrayListOf()
    private val list5: ArrayList<SlotModel> = arrayListOf()

    private val weel_1_ValueList: ArrayList<Int> = arrayListOf()
    private val weel_2_ValueList: ArrayList<Int> = arrayListOf()
    private val weel_3_ValueList: ArrayList<Int> = arrayListOf()
    private val weel_4_ValueList: ArrayList<Int> = arrayListOf()
    private val weel_5_ValueList: ArrayList<Int> = arrayListOf()

    private lateinit var mp: MediaPlayer

    private var font: Typeface? = null

    override fun onStart() {
        super.onStart()

        fillList1()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_activity)

        font = Typeface.createFromAsset(this.assets, "font/12885.otf")
        balanceText.typeface = font
        lineValueText.typeface = font
        betValueText.typeface = font
        lineDown.typeface = font
        lineUp.typeface = font
        betDown.typeface = font
        betUp.typeface = font
        btnSpin.typeface = font
        betReitText.typeface = font

        line1_1.typeface = font
        line1_2.typeface = font
        line2_1.typeface = font
        line2_2.typeface = font
        line3_1.typeface = font
        line3_2.typeface = font
        line4_1.typeface = font
        line4_2.typeface = font
        line5_1.typeface = font
        line5_2.typeface = font
        line6_1.typeface = font
        line6_2.typeface = font
        line7_1.typeface = font
        line7_2.typeface = font
        line8_1.typeface = font
        line8_2.typeface = font
        line9_1.typeface = font
        line9_2.typeface = font
        line10_1.typeface = font
        line10_2.typeface = font
        line11_1.typeface = font
        line11_2.typeface = font
        line12_1.typeface = font
        line12_2.typeface = font
        line13_1.typeface = font
        line13_2.typeface = font
        line14_1.typeface = font
        line14_2.typeface = font
        line15_1.typeface = font
        line15_2.typeface = font
        text.typeface = font
        text2.typeface = font
        text3.typeface = font
        text4.typeface = font


        balanceText.text = Constants.BALANCE.toString()
        lineValueText.text = Constants.LINE_COUNT.toString()
        betValueText.text = Constants.BET_VALUE.toString()

        checkBetValue(lineValueText.text.toString(), betValueText.text.toString())

        lineDown.setOnClickListener {
            when(lineValueText.text){
                "15"-> { lineValueText.text = "14" }
                "14"-> { lineValueText.text = "13" }
                "13"-> { lineValueText.text = "12" }
                "12"-> { lineValueText.text = "11" }
                "11"-> { lineValueText.text = "10" }
                "10"-> { lineValueText.text = "9" }
                "9"-> { lineValueText.text = "8" }
                "8"-> { lineValueText.text = "7" }
                "7"-> { lineValueText.text = "6" }
                "6"-> { lineValueText.text = "5" }
                "5"-> { lineValueText.text = "4" }
                "4"-> { lineValueText.text = "3" }
                "3"-> { lineValueText.text = "2" }
                "2"-> { lineValueText.text = "1" }
                "1"-> { return@setOnClickListener }
            }
            checkBetValue(lineValueText.text.toString(), betValueText.text.toString())
            showLines(lineValueText.text.toString())
        }

        lineUp.setOnClickListener {
            when(lineValueText.text){
                "15"-> { return@setOnClickListener }
                "14"-> { lineValueText.text = "15" }
                "13"-> { lineValueText.text = "14" }
                "12"-> { lineValueText.text = "13" }
                "11"-> { lineValueText.text = "12" }
                "10"-> { lineValueText.text = "11" }
                "9"-> { lineValueText.text = "10" }
                "8"-> { lineValueText.text = "9" }
                "7"-> { lineValueText.text = "8" }
                "6"-> { lineValueText.text = "7" }
                "5"-> { lineValueText.text = "6" }
                "4"-> { lineValueText.text = "5" }
                "3"-> { lineValueText.text = "4" }
                "2"-> { lineValueText.text = "3" }
                "1"-> { lineValueText.text = "2" }
            }
            checkBetValue(lineValueText.text.toString(), betValueText.text.toString())
            showLines(lineValueText.text.toString())
        }

        betDown.setOnClickListener {
            when(betValueText.text){
                "50"-> { betValueText.text = "25" }
                "25"-> { betValueText.text = "20" }
                "20"-> { betValueText.text = "15" }
                "15"-> { betValueText.text = "10" }
                "10"-> { betValueText.text = "5" }
                "5"-> { betValueText.text = "4" }
                "4"-> { betValueText.text = "3" }
                "3"-> { betValueText.text = "2" }
                "2"-> { betValueText.text = "1" }
                "1"-> { betValueText.text = "0.5" }
                "0.5"-> { return@setOnClickListener }
            }
            checkBetValue(lineValueText.text.toString(), betValueText.text.toString())
        }

        betUp.setOnClickListener {
            when(betValueText.text){
                "50"-> { return@setOnClickListener }
                "25"-> { betValueText.text = "50" }
                "20"-> { betValueText.text = "25" }
                "15"-> { betValueText.text = "20" }
                "10"-> { betValueText.text = "15" }
                "5"-> { betValueText.text = "10" }
                "4"-> { betValueText.text = "5" }
                "3"-> { betValueText.text = "4" }
                "2"-> { betValueText.text = "3" }
                "1"-> { betValueText.text = "2" }
                "0.5"-> { betValueText.text = "1" }
            }
            checkBetValue(lineValueText.text.toString(), betValueText.text.toString())
        }

        val disabler = RecyclerViewDisabler()
        mp = MediaPlayer()

        adapter1 = ImagesAdapter()
        val layoutManager = SpeedyLinearLayoutManager(this, SpeedyLinearLayoutManager.VERTICAL, true)
        rv1.addOnItemTouchListener(disabler)
        rv1.layoutManager = layoutManager
        rv1.hasFixedSize()
        rv1.adapter = adapter1

        adapter2 = ImagesAdapter()
        val layoutManager2 = SpeedyLinearLayoutManager2(this, SpeedyLinearLayoutManager2.VERTICAL, true)
        rv2.addOnItemTouchListener(disabler)
        rv2.layoutManager = layoutManager2
        rv2.hasFixedSize()
        rv2.adapter = adapter2

        adapter3 = ImagesAdapter()
        val layoutManager3 = SpeedyLinearLayoutManager3(this, SpeedyLinearLayoutManager3.VERTICAL, true)
        rv3.addOnItemTouchListener(disabler)
        rv3.layoutManager = layoutManager3
        rv3.hasFixedSize()
        rv3.adapter = adapter3

        adapter4 = ImagesAdapter()
        val layoutManager4 = SpeedyLinearLayoutManager4(this, SpeedyLinearLayoutManager4.VERTICAL, true)
        rv4.addOnItemTouchListener(disabler)
        rv4.layoutManager = layoutManager4
        rv4.hasFixedSize()
        rv4.adapter = adapter4

        adapter5 = ImagesAdapter()
        val layoutManager5 = SpeedyLinearLayoutManager5(this, SpeedyLinearLayoutManager5.VERTICAL, true)
        rv5.addOnItemTouchListener(disabler)
        rv5.layoutManager = layoutManager5
        rv5.hasFixedSize()
        rv5.adapter = adapter5


        btnSpin.setOnClickListener {

            if (balanceText.text.toString().toDouble() < betReitText.text.toString().toDouble()){
                toast("Low balance!!!")
                return@setOnClickListener
            }

            lastWinText.text = ""

            getFromBalance(balanceText.text.toString(), betReitText.text.toString())

            btnSpin.isClickable = false

            Handler().postDelayed({
                btnSpin.isClickable = true
            }, 2700)

            val position1 = adapter1.itemCount / 3 - 4
            rv1.smoothScrollToPosition(position1)

            val position2 = adapter2.itemCount / 3 - 3
            rv2.smoothScrollToPosition(position2)

            val position3 = adapter3.itemCount / 3 - 2
            rv3.smoothScrollToPosition(position3)

            val position4 = adapter4.itemCount / 3 - 1
            rv4.smoothScrollToPosition(position4)

            val position5 = adapter5.itemCount / 3
            rv5.smoothScrollToPosition(position5)


            if(mp.isPlaying){
                mp.stop()
            }

            try {
                mp.reset()
                val afd: AssetFileDescriptor = assets.openFd("sound.mp3")
                mp.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                mp.prepare()
                mp.start()

            } catch (e: Throwable) {
                e.printStackTrace()

            } catch (e: IOException) {
                e.printStackTrace()

            }

            fillList1()

            weel_1_ValueList.clear()
            weel_2_ValueList.clear()
            weel_3_ValueList.clear()
            weel_4_ValueList.clear()
            weel_5_ValueList.clear()

            weel_1_ValueList.add(0, list1[position1].value)
            weel_1_ValueList.add(1, list1[position1-1].value)
            weel_1_ValueList.add(2, list1[position1-2].value)

            weel_2_ValueList.add(0, list2[position2].value)
            weel_2_ValueList.add(1, list2[position2-1].value)
            weel_2_ValueList.add(2, list2[position2-2].value)

            weel_3_ValueList.add(0, list3[position3].value)
            weel_3_ValueList.add(1, list3[position3-1].value)
            weel_3_ValueList.add(2, list3[position3-2].value)

            weel_4_ValueList.add(0, list4[position4].value)
            weel_4_ValueList.add(1, list4[position4-1].value)
            weel_4_ValueList.add(2, list4[position4-2].value)

            weel_5_ValueList.add(0, list5[position5].value)
            weel_5_ValueList.add(1, list5[position5-1].value)
            weel_5_ValueList.add(2, list5[position5-2].value)

            checkForWin(lineValueText.text.toString(), weel_1_ValueList, weel_2_ValueList, weel_3_ValueList, weel_4_ValueList, weel_5_ValueList, betValueText.text.toString())
        }
    }

    private fun fillList1(){

        for (i in 0..60) {

            val foo = Math.random() * 100
            when{
                foo < 2 -> {
                    list1.add(i, SlotModel(Constants.SEVEN_ID, Constants.SEVEN_TITLE,  Constants.SEVEN_AMOUNT, R.drawable.icon_seven))
                }
                foo < 5 -> {
                    list1.add(i, SlotModel(Constants.BAR_ID, Constants.BAR_TITLE, Constants.BAR_AMOUNT, R.drawable.icon_bar))
                }
                foo < 9 -> {
                    list1.add(i, SlotModel(Constants.LUCKY_ID,Constants.LUCKY_TITLE, Constants.LUCKY_AMOUNT, R.drawable.icon_lucky))
                }
                foo < 14 -> {
                    list1.add(i, SlotModel(Constants.CLEVER_ID,Constants.CLEVER_TITLE, Constants.CLEVER_AMOUNT, R.drawable.icon_clever))
                }
                foo < 20 -> {
                    list1.add(i, SlotModel(Constants.BELL_ID,Constants.BELL_TITLE, Constants.BELL_AMOUNT, R.drawable.icon_bell))
                }
                foo < 27 -> {
                    list1.add(i, SlotModel(Constants.WATERLEMON_ID,Constants.WATERLEMON_TITLE, Constants.WATERLEMON_AMOUNT, R.drawable.icon_watermalon))
                }
                foo < 35 -> {
                    list1.add(i, SlotModel(Constants.VINO_ID, Constants.VINO_TITLE, Constants.VINO_AMOUNT, R.drawable.icon_vino))
                }
                foo < 60 -> {
                    list1.add(i, SlotModel(Constants.SLIVA_ID,Constants.SLIVA_TITLE, Constants.SLIVA_AMOUNT, R.drawable.icon_sliva))
                }
                foo < 80 -> {
                    list1.add(i, SlotModel(Constants.LEMON_ID,Constants.LEMON_TITLE, Constants.LEMON_AMOUNT, R.drawable.icon_lemon))
                }
                foo < 100 -> {
                    list1.add(i, SlotModel(Constants.CHERRY_ID,Constants.CHERRY_TITLE, Constants.CHERRY_AMOUNT, R.drawable.icon_cherry))
                }
            }

//            list1.add(i, SlotModel(100, R.drawable.icon_cherry))
        }
        adapter1.setList(list1)

        for (i in 0..60){
            val foo = Math.random() * 100
            when{
                foo < 2 -> {
                    list2.add(i, SlotModel(Constants.SEVEN_ID, Constants.SEVEN_TITLE,  Constants.SEVEN_AMOUNT, R.drawable.icon_seven))
                }
                foo < 5 -> {
                    list2.add(i, SlotModel(Constants.BAR_ID, Constants.BAR_TITLE, Constants.BAR_AMOUNT, R.drawable.icon_bar))
                }
                foo < 9 -> {
                    list2.add(i, SlotModel(Constants.LUCKY_ID,Constants.LUCKY_TITLE, Constants.LUCKY_AMOUNT, R.drawable.icon_lucky))
                }
                foo < 14 -> {
                    list2.add(i, SlotModel(Constants.CLEVER_ID,Constants.CLEVER_TITLE, Constants.CLEVER_AMOUNT, R.drawable.icon_clever))
                }
                foo < 20 -> {
                    list2.add(i, SlotModel(Constants.BELL_ID,Constants.BELL_TITLE, Constants.BELL_AMOUNT, R.drawable.icon_bell))
                }
                foo < 27 -> {
                    list2.add(i, SlotModel(Constants.WATERLEMON_ID,Constants.WATERLEMON_TITLE, Constants.WATERLEMON_AMOUNT, R.drawable.icon_watermalon))
                }
                foo < 35 -> {
                    list2.add(i, SlotModel(Constants.VINO_ID, Constants.VINO_TITLE, Constants.VINO_AMOUNT, R.drawable.icon_vino))
                }
                foo < 60 -> {
                    list2.add(i, SlotModel(Constants.SLIVA_ID,Constants.SLIVA_TITLE, Constants.SLIVA_AMOUNT, R.drawable.icon_sliva))
                }
                foo < 80 -> {
                    list2.add(i, SlotModel(Constants.LEMON_ID,Constants.LEMON_TITLE, Constants.LEMON_AMOUNT, R.drawable.icon_lemon))
                }
                foo < 100 -> {
                    list2.add(i, SlotModel(Constants.CHERRY_ID,Constants.CHERRY_TITLE, Constants.CHERRY_AMOUNT, R.drawable.icon_cherry))
                }
            }
        }
        adapter2.setList(list2)

        for (i in 0..60){
            val foo = Math.random() * 100
            when{
                foo < 2 -> {
                    list3.add(i, SlotModel(Constants.SEVEN_ID, Constants.SEVEN_TITLE,  Constants.SEVEN_AMOUNT, R.drawable.icon_seven))
                }
                foo < 5 -> {
                    list3.add(i, SlotModel(Constants.BAR_ID, Constants.BAR_TITLE, Constants.BAR_AMOUNT, R.drawable.icon_bar))
                }
                foo < 9 -> {
                    list3.add(i, SlotModel(Constants.LUCKY_ID,Constants.LUCKY_TITLE, Constants.LUCKY_AMOUNT, R.drawable.icon_lucky))
                }
                foo < 14 -> {
                    list3.add(i, SlotModel(Constants.CLEVER_ID,Constants.CLEVER_TITLE, Constants.CLEVER_AMOUNT, R.drawable.icon_clever))
                }
                foo < 20 -> {
                    list3.add(i, SlotModel(Constants.BELL_ID,Constants.BELL_TITLE, Constants.BELL_AMOUNT, R.drawable.icon_bell))
                }
                foo < 27 -> {
                    list3.add(i, SlotModel(Constants.WATERLEMON_ID,Constants.WATERLEMON_TITLE, Constants.WATERLEMON_AMOUNT, R.drawable.icon_watermalon))
                }
                foo < 35 -> {
                    list3.add(i, SlotModel(Constants.VINO_ID, Constants.VINO_TITLE, Constants.VINO_AMOUNT, R.drawable.icon_vino))
                }
                foo < 60 -> {
                    list3.add(i, SlotModel(Constants.SLIVA_ID,Constants.SLIVA_TITLE, Constants.SLIVA_AMOUNT, R.drawable.icon_sliva))
                }
                foo < 80 -> {
                    list3.add(i, SlotModel(Constants.LEMON_ID,Constants.LEMON_TITLE, Constants.LEMON_AMOUNT, R.drawable.icon_lemon))
                }
                foo < 100 -> {
                    list3.add(i, SlotModel(Constants.CHERRY_ID,Constants.CHERRY_TITLE, Constants.CHERRY_AMOUNT, R.drawable.icon_cherry))
                }
            }
        }
        adapter3.setList(list3)

        for (i in 0..60){
            val foo = Math.random() * 100
            when{
                foo < 2 -> {
                    list4.add(i, SlotModel(Constants.SEVEN_ID, Constants.SEVEN_TITLE,  Constants.SEVEN_AMOUNT, R.drawable.icon_seven))
                }
                foo < 5 -> {
                    list4.add(i, SlotModel(Constants.BAR_ID, Constants.BAR_TITLE, Constants.BAR_AMOUNT, R.drawable.icon_bar))
                }
                foo < 9 -> {
                    list4.add(i, SlotModel(Constants.LUCKY_ID,Constants.LUCKY_TITLE, Constants.LUCKY_AMOUNT, R.drawable.icon_lucky))
                }
                foo < 14 -> {
                    list4.add(i, SlotModel(Constants.CLEVER_ID,Constants.CLEVER_TITLE, Constants.CLEVER_AMOUNT, R.drawable.icon_clever))
                }
                foo < 20 -> {
                    list4.add(i, SlotModel(Constants.BELL_ID,Constants.BELL_TITLE, Constants.BELL_AMOUNT, R.drawable.icon_bell))
                }
                foo < 27 -> {
                    list4.add(i, SlotModel(Constants.WATERLEMON_ID,Constants.WATERLEMON_TITLE, Constants.WATERLEMON_AMOUNT, R.drawable.icon_watermalon))
                }
                foo < 35 -> {
                    list4.add(i, SlotModel(Constants.VINO_ID, Constants.VINO_TITLE, Constants.VINO_AMOUNT, R.drawable.icon_vino))
                }
                foo < 60 -> {
                    list4.add(i, SlotModel(Constants.SLIVA_ID,Constants.SLIVA_TITLE, Constants.SLIVA_AMOUNT, R.drawable.icon_sliva))
                }
                foo < 80 -> {
                    list4.add(i, SlotModel(Constants.LEMON_ID,Constants.LEMON_TITLE, Constants.LEMON_AMOUNT, R.drawable.icon_lemon))
                }
                foo < 100 -> {
                    list4.add(i, SlotModel(Constants.CHERRY_ID,Constants.CHERRY_TITLE, Constants.CHERRY_AMOUNT, R.drawable.icon_cherry))
                }
            }
        }
        adapter4.setList(list4)

        for (i in 0..60){
            val foo = Math.random() * 100
            when{
                foo < 2 -> {
                    list5.add(i, SlotModel(Constants.SEVEN_ID, Constants.SEVEN_TITLE,  Constants.SEVEN_AMOUNT, R.drawable.icon_seven))
                }
                foo < 5 -> {
                    list5.add(i, SlotModel(Constants.BAR_ID, Constants.BAR_TITLE, Constants.BAR_AMOUNT, R.drawable.icon_bar))
                }
                foo < 9 -> {
                    list5.add(i, SlotModel(Constants.LUCKY_ID,Constants.LUCKY_TITLE, Constants.LUCKY_AMOUNT, R.drawable.icon_lucky))
                }
                foo < 14 -> {
                    list5.add(i, SlotModel(Constants.CLEVER_ID,Constants.CLEVER_TITLE, Constants.CLEVER_AMOUNT, R.drawable.icon_clever))
                }
                foo < 20 -> {
                    list5.add(i, SlotModel(Constants.BELL_ID,Constants.BELL_TITLE, Constants.BELL_AMOUNT, R.drawable.icon_bell))
                }
                foo < 27 -> {
                    list5.add(i, SlotModel(Constants.WATERLEMON_ID,Constants.WATERLEMON_TITLE, Constants.WATERLEMON_AMOUNT, R.drawable.icon_watermalon))
                }
                foo < 35 -> {
                    list5.add(i, SlotModel(Constants.VINO_ID, Constants.VINO_TITLE, Constants.VINO_AMOUNT, R.drawable.icon_vino))
                }
                foo < 60 -> {
                    list5.add(i, SlotModel(Constants.SLIVA_ID,Constants.SLIVA_TITLE, Constants.SLIVA_AMOUNT, R.drawable.icon_sliva))
                }
                foo < 80 -> {
                    list5.add(i, SlotModel(Constants.LEMON_ID,Constants.LEMON_TITLE, Constants.LEMON_AMOUNT, R.drawable.icon_lemon))
                }
                foo < 100 -> {
                    list5.add(i, SlotModel(Constants.CHERRY_ID,Constants.CHERRY_TITLE, Constants.CHERRY_AMOUNT, R.drawable.icon_cherry))
                }
            }
        }
        adapter5.setList(list5)
    }

    private fun checkBetValue(val1: String, val2: String){
        betReitText.text = (val1.toDouble() * val2.toDouble()).toString()
    }

    private fun getFromBalance(balance: String, bet: String){
        balanceText.text = (balance.toDouble() - bet.toDouble()).toString()
    }

    private fun updateBalance(balance: String, win: String){
        balanceText.text = (balance.toDouble() + win.toDouble()).toString()
    }

    private fun showLines(al: String){
        val count = al.toInt()
        when(count){
            1->{
                line2_1.visibility = View.INVISIBLE
                line2_2.visibility = View.INVISIBLE
            }
            2->{
                line2_1.visibility = View.VISIBLE
                line2_2.visibility = View.VISIBLE
                line3_1.visibility = View.INVISIBLE
                line3_2.visibility = View.INVISIBLE
            }
            3->{
                line3_1.visibility = View.VISIBLE
                line3_2.visibility = View.VISIBLE
                line4_1.visibility = View.INVISIBLE
                line4_2.visibility = View.INVISIBLE
            }
            4->{
                line4_1.visibility = View.VISIBLE
                line4_2.visibility = View.VISIBLE
                line5_1.visibility = View.INVISIBLE
                line5_2.visibility = View.INVISIBLE
            }
            5->{
                line5_1.visibility = View.VISIBLE
                line5_2.visibility = View.VISIBLE
                line6_1.visibility = View.INVISIBLE
                line6_2.visibility = View.INVISIBLE
            }
            6->{
                line6_1.visibility = View.VISIBLE
                line6_2.visibility = View.VISIBLE
                line7_1.visibility = View.INVISIBLE
                line7_2.visibility = View.INVISIBLE
            }
            7->{
                line7_1.visibility = View.VISIBLE
                line7_2.visibility = View.VISIBLE
                line8_1.visibility = View.INVISIBLE
                line8_2.visibility = View.INVISIBLE
            }
            8->{
                line8_1.visibility = View.VISIBLE
                line8_2.visibility = View.VISIBLE
                line9_1.visibility = View.INVISIBLE
                line9_2.visibility = View.INVISIBLE
            }
            9->{
                line9_1.visibility = View.VISIBLE
                line9_2.visibility = View.VISIBLE
                line10_1.visibility = View.INVISIBLE
                line10_2.visibility = View.INVISIBLE
            }
            10->{
                line10_1.visibility = View.VISIBLE
                line10_2.visibility = View.VISIBLE
                line11_1.visibility = View.INVISIBLE
                line11_2.visibility = View.INVISIBLE
            }
            11->{
                line11_1.visibility = View.VISIBLE
                line11_2.visibility = View.VISIBLE
                line12_1.visibility = View.INVISIBLE
                line12_2.visibility = View.INVISIBLE
            }
            12->{
                line12_1.visibility = View.VISIBLE
                line12_2.visibility = View.VISIBLE
                line13_1.visibility = View.INVISIBLE
                line13_2.visibility = View.INVISIBLE
            }
            13->{
                line13_1.visibility = View.VISIBLE
                line13_2.visibility = View.VISIBLE
                line14_1.visibility = View.INVISIBLE
                line14_2.visibility = View.INVISIBLE
            }
            14->{
                line14_1.visibility = View.VISIBLE
                line14_2.visibility = View.VISIBLE
                line15_1.visibility = View.INVISIBLE
                line15_2.visibility = View.INVISIBLE
            }
            15->{
                line15_1.visibility = View.VISIBLE
                line15_2.visibility = View.VISIBLE
            }
        }

    }

    private fun checkForWin(count: String, list1: ArrayList<Int>, list2: ArrayList<Int>,list3: ArrayList<Int>,list4: ArrayList<Int>,list5: ArrayList<Int>, betValue: String){

        when(count.toInt()){
            1->{

                var fullValue = 0.0

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    fullValue = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    fullValue = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    fullValue = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    fullValue = (list1[1] * 2) * betValue.toDouble()
                }

                if (fullValue != 0.0) {
                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }

            }

            2-> {

                var firstAmount = 0.0
                var secondAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }

            }

            3->{

                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            4->{

                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            5->{

                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            6->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            7->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            8->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            9->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            10->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            11->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var elevenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[0]){
                    elevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[0]){
                    elevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    elevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[1]){
                    elevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount + elevenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            12->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var elevenAmount = 0.0
                var tvelwAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[0]){
                    elevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[0]){
                    elevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    elevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[1]){
                    elevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] == list5[0]){
                    tvelwAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] != list5[0]){
                    tvelwAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] != list4[1]){
                    tvelwAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[0]){
                    tvelwAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount + elevenAmount + tvelwAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            13->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var elevenAmount = 0.0
                var tvelwAmount = 0.0
                var thirteenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[0]){
                    elevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[0]){
                    elevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    elevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[1]){
                    elevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] == list5[0]){
                    tvelwAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] != list5[0]){
                    tvelwAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] != list4[1]){
                    tvelwAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[0]){
                    tvelwAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]){
                    thirteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]){
                    thirteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] != list4[1]){
                    thirteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[2]){
                    thirteenAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount + elevenAmount +
                        tvelwAmount + thirteenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            14->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var elevenAmount = 0.0
                var tvelwAmount = 0.0
                var thirteenAmount = 0.0
                var fourteenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[0]){
                    elevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[0]){
                    elevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    elevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[1]){
                    elevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] == list5[0]){
                    tvelwAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] != list5[0]){
                    tvelwAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] != list4[1]){
                    tvelwAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[0]){
                    tvelwAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]){
                    thirteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]){
                    thirteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] != list4[1]){
                    thirteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[2]){
                    thirteenAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fourteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fourteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fourteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[0]) {
                    fourteenAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount + elevenAmount +
                        tvelwAmount + thirteenAmount + fourteenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }

            15->{
                var firstAmount = 0.0
                var secondAmount = 0.0
                var thirdAmount = 0.0
                var fourthAmount = 0.0
                var fifthAmount = 0.0
                var sixAmount = 0.0
                var sevenAmount = 0.0
                var eightAmount = 0.0
                var nineAmount = 0.0
                var tenAmount = 0.0
                var elevenAmount = 0.0
                var tvelwAmount = 0.0
                var thirteenAmount = 0.0
                var fourteenAmount = 0.0
                var fifteenAmount = 0.0
                var fullValue = 0.0

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] == list5[0]){
                    firstAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] == list4[0] && list4[0] != list5[0]) {
                    firstAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[0] && list3[0] != list4[0]) {
                    firstAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[0]) {
                    firstAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[1]){
                    secondAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[1]){
                    secondAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    secondAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[1]) {
                    secondAmount = (list1[1] * 2) * betValue.toDouble()
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] == list5[2]){
                    thirdAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] == list4[2] && list4[2] != list5[2]){
                    thirdAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[2] && list3[2] != list4[2]){
                    thirdAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[2]) {
                    thirdAmount = (list1[2] * 2) * betValue.toDouble()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fourthAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fourthAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]){
                    fourthAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[2]) {
                    fourthAmount = (list1[0] * 2) * betValue.toDouble()
                }

                ///////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fifthAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fifthAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fifthAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[0]) {
                    fifthAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[2]){
                    sixAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[2]){
                    sixAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    sixAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[0] && list2[0] != list3[1] ){
                    sixAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[0]){
                    sevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[0]){
                    sevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0]){
                    sevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[2] && list2[2] != list3[1]){
                    sevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] == list5[1]){
                    eightAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] == list4[2] && list4[2] != list5[1]){
                    eightAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[1] && list3[1] != list4[2]){
                    eightAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[1] ){
                    eightAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] == list5[1]) {
                    nineAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] == list4[0] && list4[0] != list5[1]) {
                    nineAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[1] && list3[1] != list4[0] ) {
                    nineAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[1]) {
                    nineAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[2]){
                    tenAmount = (list1[0] * 5) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[2]){
                    tenAmount = (list1[0] * 4) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    tenAmount = (list1[0] * 3) * betValue.toDouble()
                }

                if (list1[0] == list2[1] && list2[1] != list3[1]){
                    tenAmount = (list1[0] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] == list5[0]){
                    elevenAmount = (list1[2] * 5) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] == list4[1] && list4[1] != list5[0]){
                    elevenAmount = (list1[2] * 4) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] == list3[1] && list3[1] != list4[1]){
                    elevenAmount = (list1[2] * 3) * betValue.toDouble()
                }

                if (list1[2] == list2[1] && list2[1] != list3[1]){
                    elevenAmount = (list1[2] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] == list5[0]){
                    tvelwAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] == list4[1] && list4[1] != list5[0]){
                    tvelwAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] == list3[0] && list3[0] != list4[1]){
                    tvelwAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[0] && list2[0] != list3[0]){
                    tvelwAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]){
                    thirteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]){
                    thirteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] == list3[2] && list3[2] != list4[1]){
                    thirteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[2] && list2[2] != list3[2]){
                    thirteenAmount = (list1[1] * 2) * betValue.toDouble()
                }

                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] == list5[2]) {
                    fourteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] == list4[1] && list4[1] != list5[2]) {
                    fourteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[0] && list3[0] != list4[1]) {
                    fourteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[0]) {
                    fourteenAmount = (list1[1] * 2) * betValue.toDouble()
                }


                /////////////////////////////////////////////////////////////////////////////////////////////

                if (list1[1] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] == list5[0]) {
                    fifteenAmount = (list1[1] * 5) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[2] && list3[2] == list4[1] && list4[1] != list5[0]) {
                    fifteenAmount = (list1[1] * 4) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] == list3[2] && list3[2] != list4[1]) {
                    fifteenAmount = (list1[1] * 3) * betValue.toDouble()
                }

                if (list1[1] == list2[1] && list2[1] != list3[2]) {
                    fifteenAmount = (list1[1] * 2) * betValue.toDouble()
                }

                fullValue = firstAmount + secondAmount + thirdAmount + fourthAmount + fifthAmount + sixAmount + sevenAmount + eightAmount + nineAmount + tenAmount + elevenAmount +
                        tvelwAmount + thirteenAmount + fourteenAmount + fifteenAmount

                if (fullValue != 0.0){

                    Handler().postDelayed({
                        lastWinText.text = fullValue.toString()
                        updateBalance(balanceText.text.toString(), fullValue.toString())
                    }, 2000)
                }
            }
        }

    }
}
