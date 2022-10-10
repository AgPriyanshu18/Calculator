package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
//import com.example.calculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastDot = false;
    var lastNumeric= false;
    var lastOperator = false;
    var lists = arrayListOf<String>()
    var check = 0
    var ans : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun calculate(value : String): Double {
        if (value.contains("-")){
            val splitv = value.split("-")
            var one = splitv[0]
            var two = splitv[1]
            return one.toDouble() - two.toDouble()
        }
        else if (value.contains("+")){
            val splitv = value.split("+")
            var one = splitv[0]
            var two = splitv[1]
            return one.toDouble() + two.toDouble()
        }
        else if (value.contains("*")){
            val splitv = value.split("*")
            var one = splitv[0]
            var two = splitv[1]
            return one.toDouble() * two.toDouble()
        }
        else if (value.contains("/")){
            val splitv = value.split("/")
            var one = splitv[0]
            var two = splitv[1]
            return one.toDouble() / two.toDouble()
        }
        else return 0.0
    }

    fun onEqual(view:View)
    {
        try {
            if (lastOperator) {
                return
            }
            var ind : Int = 0
            while (ind < lists.size){
                if (check==0){
                    var ch = 0
                    var num : String = ""
                    while(ind < lists.size)
                    {
                        if ((lists[ind] == "+" || lists[ind] == "-" || lists[ind] == "*" || lists[ind] == "/") && ch == 0){
                            num = num + lists[ind]
                            ch++;
                            ind++
                        }
                        else if ((lists[ind] == "+" || lists[ind] == "-" || lists[ind] == "*" || lists[ind] == "/")  && ch==1){
                           break
                        }
                        else {
                            num += lists[ind]
                            ind++
                        }
                    }
                    ans = calculate(num)
                    check++
                }
                else{
                    var num : String = ans.toString()
                    var ch :Int = 0
                    while(ind < lists.size)
                    {
                        if ((lists[ind] == "+" || lists[ind] == "-" || lists[ind] == "*" || lists[ind] == "/") && ch == 0){
                            num = num + lists[ind]
                            ch++;
                            ind++
                        }
                        else if ((lists[ind] == "+" || lists[ind] == "-" || lists[ind] == "*" || lists[ind] == "/") && ch==1){
                            break;
                        }
                        else
                        {
                            num += lists[ind]
                            ind++
                        }
                    }
                    ans = calculate(num)
                }
            }
            tvInput.text = ans.toString()
            lists.clear()
//            lists.add(ans.toString())
        }
        catch (e:ArithmeticException){
            e.printStackTrace()

        }

//        if (lastNumeric){
//            var tvValue = tvInput.text.toString()
//            var prefix= ""
//            try {
//                if (tvValue.startsWith("-"))
//                {
//                    prefix = "-"
//                    tvValue = tvValue.substring(1)
//                }
//                if(tvValue.contains("-")){
//                    val splitValue = tvValue.split("-")
//
//                    var one = splitValue[0]
//                    var two = splitValue[1]
//
//                    if (!prefix.isEmpty())
//                    {
//                        one = prefix + one;
//                    }
//                    tvInput.text = (one.toDouble() - two.toDouble()).toString()
//                }
//
//                if(tvValue.contains("+")){
//                    val splitValue = tvValue.split("+")
//
//                    var one = splitValue[0]
//                    var two = splitValue[1]
//
//                    if (!prefix.isEmpty())
//                    {
//                        one = prefix + one;
//                    }
//                    tvInput.text = (one.toDouble() + two.toDouble()).toString()
//                }
//
//                if(tvValue.contains("*")){
//                    val splitValue = tvValue.split("*")
//
//                    var one = splitValue[0]
//                    var two = splitValue[1]
//
//                    if (!prefix.isEmpty())
//                    {
//                        one = prefix + one;
//                    }
//                    tvInput.text = (one.toDouble() * two.toDouble()).toString()
//                }
//
//                if(tvValue.contains("/")){
//                    val splitValue = tvValue.split("/")
//
//                    var one = splitValue[0]
//                    var two = splitValue[1]
//
//                    if (!prefix.isEmpty())
//                    {
//                        one = prefix + one;
//                    }
//                    if (two == "0")
//                    {
//                        tvInput.text = "Chal Chutiya"
//                    }
//                    else {
//                        tvInput.text = (one.toDouble() / two.toDouble()).toString()
//                    }
//                }
//
//            }catch (e:ArithmeticException){
//                e.printStackTrace()
//            }
//        }
    }

    fun Ondigit(view:View){
        tvInput.append((view as Button).text)
        lists.add((view as Button).text.toString())
        lastNumeric=true
        lastOperator = false
    }

    fun OnClear(view:View){
        tvInput.text = ""
        lists.clear()
        lastDot = false
        lastNumeric = false
        check = 0
        lastOperator = false
    }

    fun OnDot(view:View){
        if (lastNumeric && !lastDot)
        {
            tvInput.append(".")
            lists.add(".")
            lastDot=true;
            lastNumeric=true
        }
    }

    fun onOperator(view : View){
        if (lastNumeric){
            tvInput.append((view as Button).text)
            lists.add((view as Button).text.toString())
            lastNumeric = false
            lastDot = false
            lastOperator = true
        }
    }

    private fun isOperatorAdded(value:String) : Boolean
    {
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("-") ||
                    value.contains("*") || value.contains("+")
        }
    }
    }


