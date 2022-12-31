package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

     var result: TextView? =null
     var last_dig_numeric=false
     var last_dig_dec=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result=findViewById(R.id.result)

    }

    fun digitClicked(view: View) // view represents the btn which called this digitClicked method
    {
      result?.append((view as Button).text)
        last_dig_numeric=true
        last_dig_dec=false
    }

    fun acClicked(view: View){
        result?.text=""
    }
    
    fun decDigitClicked(view:View)
    {
        if(last_dig_numeric && last_dig_dec!=true)
        {
            result?.append(".")
            last_dig_numeric=false
            last_dig_dec=true
        }
    }

    fun opClicked(view:View){
        result?.text?.let{
            if(last_dig_numeric && !isOperatorPresent(it.toString()))
            {
                result?.append((view as Button).text)
                last_dig_numeric=false
                last_dig_dec=false
            }
        }
    }




    fun onEqual(view:View) {
        if (last_dig_numeric) {
            var res_value = result?.text.toString()
            var prefix = ""

            try {
                if (res_value.startsWith("-")) {
                    prefix = "-"
                    res_value = res_value.substring(1)
                }
                if (res_value.contains("-")) {
                    var split_val = res_value.split("-") // 99-1
                    var one = split_val[0] //99
                    var two = split_val[1] //1
                    if (prefix != "") {
                        one = prefix + one
                    }
                    result?.text = removeZeroafterDot((one.toDouble() - two.toDouble()).toString())


                } else if (res_value.contains("+")) {
                    var split_val = res_value.split("+") // 99-1
                    var one = split_val[0] //99
                    var two = split_val[1] //1
                    if (prefix != "") {
                        one = prefix + one
                    }
                    result?.text = removeZeroafterDot((one.toDouble() + two.toDouble()).toString())


                } else if (res_value.contains("*")) {
                    var split_val = res_value.split("*") // 99-1
                    var one = split_val[0] //99
                    var two = split_val[1] //1
                    if (prefix != "") {
                        one = prefix + one
                    }
                    result?.text =removeZeroafterDot( (one.toDouble() * two.toDouble()).toString())


                } else {
                    var split_val = res_value.split("/") // 99-1
                    var one = split_val[0] //99
                    var two = split_val[1] //1
                    if (prefix != "") {
                        one = prefix + one
                    }
                    result?.text = removeZeroafterDot((one.toDouble() / two.toDouble()).toString())


                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

        private fun removeZeroafterDot(ans:String):String{
            var value=ans
            if(ans.contains(".0"))
            { value=value.substring(0,ans.length-2)
        }
            return value}

    private fun isOperatorPresent(value:String):Boolean{
        return(
                if(value.startsWith("-"))
                {
                    false
                }
                else
                {
                    value.contains("+") ||
                            value.contains("*")
                            || value.contains("-")
                            || value.contains("/")
                }
                )
    }
    }





