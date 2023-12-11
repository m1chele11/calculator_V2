package edu.iu.mbarrant.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.sin
import kotlin.math.sinh
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    lateinit var numView:TextView

    var prev = false
    var op = ""
    var op2 = ""
    var ans = 0.0

    val prev_key = "prev"
    val op_key = "op"
    val op2_key = "op2"
    val ans_key = "ans"
    val text_key = "text"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create buttons and button functionality
        numView = findViewById<TextView>(R.id.tvNumView)

        if (savedInstanceState!=null){
            prev = savedInstanceState.getBoolean(prev_key)
            op = savedInstanceState.getString(op_key).toString()
            op2 = savedInstanceState.getString(op2_key).toString()
            ans = savedInstanceState.getDouble(ans_key)
            if (numView!=null){
                numView.text = savedInstanceState.getString(text_key)
            }
        }

        //clears screen/inputs
        var bC = findViewById<Button>(R.id.bC)
        bC.setOnClickListener{
            numView.text = null
            prev = false
            op = ""
            op2 = ""
            ans = 0.0
            Log.i("Calculator", "user pressed 'C'")
        }
        //number clicks
        var b0 = findViewById<Button>(R.id.b0)
        b0.setOnClickListener {numClick(b0, numView)
            Log.i("Calculator", "user pressed '0'")}
        var b1 = findViewById<Button>(R.id.b1)
        b1.setOnClickListener {numClick(b1, numView)
            Log.i("Calculator", "user pressed '1'")}
        var b2 = findViewById<Button>(R.id.b2)
        b2.setOnClickListener {numClick(b2, numView)
            Log.i("Calculator", "user pressed '2'")}
        var b3 = findViewById<Button>(R.id.b3)
        b3.setOnClickListener {numClick(b3, numView)
            Log.i("Calculator", "user pressed '3'")}
        var b4 = findViewById<Button>(R.id.b4)
        b4.setOnClickListener {numClick(b4, numView)
            Log.i("Calculator", "user pressed '4'")}
        var b5 = findViewById<Button>(R.id.b5)
        b5.setOnClickListener {numClick(b5, numView)
            Log.i("Calculator", "user pressed '5'")}
        var b6 = findViewById<Button>(R.id.b6)
        b6.setOnClickListener {numClick(b6, numView)
            Log.i("Calculator", "user pressed '6'")}
        var b7 = findViewById<Button>(R.id.b7)
        b7.setOnClickListener {numClick(b7, numView)
            Log.i("Calculator", "user pressed '7'")}
        var b8 = findViewById<Button>(R.id.b8)
        b8.setOnClickListener {numClick(b8, numView)
            Log.i("Calculator", "user pressed '8'")}
        var b9 = findViewById<Button>(R.id.b9)
        b9.setOnClickListener {numClick(b9, numView)
            Log.i("Calculator", "user pressed '9'")}
        var eq = findViewById<Button>(R.id.bEqual)
        eq.setOnClickListener {
            equal(numView)
            prev = false
            ans = 0.0
            op = ""
            op2 = ""
            Log.i("Calculator", "user pressed '='")
        }
        //operation clicks
        var add = findViewById<Button>(R.id.bAdd)
        add.setOnClickListener {
            ans = numView.text.toString().toDouble()
            op = "+"
            op2 = "+"
            numClick(add, numView)
            prev = true
            Log.i("Calculator", "user pressed '+'")}
        var sub = findViewById<Button>(R.id.bSubtr)
        sub.setOnClickListener {
            ans = numView.text.toString().toDouble()
            op = "-"
            op2 = "-"
            numClick(sub, numView)
            prev = true
            Log.i("Calculator", "user pressed '-'")}
        var mul = findViewById<Button>(R.id.bMultip)
        mul.setOnClickListener {
            ans = numView.text.toString().toDouble()
            op = "x"
            op2 = "x"
            numClick(mul, numView)
            prev = true
            Log.i("Calculator", "user pressed 'X'")}
        var div = findViewById<Button>(R.id.bDivide)
        div.setOnClickListener {
            ans = numView.text.toString().toDouble()
            op = "/"
            op2 = "/"
            numClick(div, numView)
            prev = true
            Log.i("Calculator", "user pressed '/'")}
        //percent button
        var per = findViewById<Button>(R.id.bPercent)
        per.setOnClickListener {
            numView.text = (numView.text.toString().toDouble() / 100).toString()
            Log.i("Calculator", "user pressed '%'")
        }
        //decimal button
        var dec = findViewById<Button>(R.id.bDec)
        dec.setOnClickListener {
            numView.text = numView.text.toString() + dec.text.toString()
            Log.i("Calculator", "user pressed '.'")
        }
        //positive/negative button
        var pn = findViewById<Button>(R.id.bPosNeg)
        pn.setOnClickListener {
            Log.i("Calculator", "user pressed '-'")
            if (numView.text.toString().toInt()<0)
                numView.text = numView.text.toString().substring(0)
            else{
                numView.text = "-"+numView.text.toString()
            }
        }
    }
    //function for number clicks
    fun numClick(num: Button, view: TextView){

        if (prev.equals(true)&&op!=""){
            view.text = num.text
            // no operation yet, saving number info
            if (op.equals("+")){
                ans += view.text.toString().toDouble()
                op = ""
            }
            if (op.equals("-")){
                ans -= view.text.toString().toDouble()
                op=""
            }
            if (op.equals("x")){
                ans *= view.text.toString().toDouble()
                op=""
            }
            if (op.equals("/")){
                ans /= view.text.toString().toDouble()
                op=""
            }
        }
        else if (view.text.isNotEmpty()&&op=="") {
            //no operation yet, undo previous math, update math
            if (op2.equals("+")) {
                ans -= view.text.toString().toDouble()
                view.text = view.text.toString() + num.text.toString()
                ans += view.text.toString().toDouble()
            }
            else if (op2.equals("-")) {
                ans += view.text.toString().toDouble()
                view.text = view.text.toString() + num.text.toString()
                ans -= view.text.toString().toDouble()
            }
            else if (op2.equals("x")) {
                ans /= view.text.toString().toDouble()
                view.text = view.text.toString() + num.text.toString()
                ans *= view.text.toString().toDouble()
            }
            else if (op2.equals("/")) {
                ans *= view.text.toString().toDouble()
                view.text = view.text.toString() + num.text.toString()
                ans /= view.text.toString().toDouble()
            }
            else view.text = view.text.toString() + num.text.toString()

        }
        else if (op=="")
        //updates screen
            view.text = num.text
    }
    fun equal(view: TextView){
        //updates screen
        view.text = ans.toString()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(prev_key, prev)
        savedInstanceState.putString(op_key, op)
        savedInstanceState.putString(op2_key, op)
        savedInstanceState.putDouble(ans_key, ans)
        savedInstanceState.putString(text_key, numView.text.toString())
        super.onSaveInstanceState(savedInstanceState)
        Log.i("Calculator", "inputs were recovered")
    }

}