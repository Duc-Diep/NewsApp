package com.ducdiep.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ducdiep.newsapp.IS_BACKGROUND
import com.ducdiep.newsapp.R
import com.ducdiep.newsapp.viewmodels.LockScreenViewModel
import kotlinx.android.synthetic.main.activity_lock_screen.*

class LockScreenActivity : AppCompatActivity() {
    lateinit var viewModel: LockScreenViewModel
    lateinit var ani: Animation
    var isBackground:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        isBackground = intent.getBooleanExtra(IS_BACKGROUND,false)
        ani = AnimationUtils.loadAnimation(this,R.anim.shake_animation)
        initViewModel()
        setClick()
    }

    private fun setClick() {
        btn0.setOnClickListener{
            viewModel.updateValue("0")
        }
        btn1.setOnClickListener{
            viewModel.updateValue("1")
        }
        btn2.setOnClickListener{
            viewModel.updateValue("2")
        }
        btn3.setOnClickListener{
            viewModel.updateValue("3")
        }
        btn4.setOnClickListener{
            viewModel.updateValue("4")
        }
        btn5.setOnClickListener{
            viewModel.updateValue("5")
        }
        btn6.setOnClickListener{
            viewModel.updateValue("6")
        }
        btn7.setOnClickListener{
            viewModel.updateValue("7")
        }
        btn8.setOnClickListener{
            viewModel.updateValue("8")
        }
        btn9.setOnClickListener{
            viewModel.updateValue("9")
        }
        btn_reset.setOnClickListener{
            viewModel.resetData()
            viewModel.enterValue = ""
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LockScreenViewModel::class.java]
        if (viewModel.isFirstTime.value == true) {
            tv_title.text = getString(R.string.create_pass)
        } else {
            tv_title.text = getString(R.string.enter_pass)
        }
        viewModel.stateCircle1.observe(this) {
            if (it == true) {
                circle1.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_white)
            } else {
                circle1.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_black)
            }
        }
        viewModel.stateCircle2.observe(this) {
            if (it == true) {
                circle2.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_white)
            } else {
                circle2.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_black)
            }
        }
        viewModel.stateCircle3.observe(this) {
            if (it == true) {
                circle3.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_white)
            } else {
                circle3.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_black)
            }
        }
        viewModel.stateCircle4.observe(this) {
            if (it == true) {
                circle4.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_white)
            } else {
                circle4.background = ContextCompat.getDrawable(this, R.drawable.border_small_circle_black)
            }
        }

        viewModel.isFirstTime.observe(this){
            if (it==true){
                tv_title.text = getString(R.string.create_pass)
            }else{
                tv_title.text = getString(R.string.enter_pass)
            }
        }
        viewModel.isSuccess.observe(this){
            if (it==true){
                if (isBackground){
                    finish()
                }else{
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
            }else{
                startAnimation()
                Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.isCreate.observe(this){
            if (it==true){
                Toast.makeText(this, "Create Success", Toast.LENGTH_SHORT).show()
                viewModel.resetData()
            }
        }

    }

    private fun startAnimation() {
        circle1.startAnimation(ani)
        circle2.startAnimation(ani)
        circle3.startAnimation(ani)
        circle4.startAnimation(ani)
    }

    override fun onBackPressed() {

    }

}