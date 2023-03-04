package com.example.testappemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testappemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FragmentCloseInterface {
    lateinit var rootElement: ActivityMainBinding
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)

        val view = rootElement.root
        setContentView(view)

        rootElement.tvLogin.setOnClickListener {
            loginFragment = LoginFragment(this)
            rootElement.mainLinearLayout.visibility = View.GONE
            val fm = this.supportFragmentManager.beginTransaction()
            fm.replace(R.id.placeholder, loginFragment)
            fm.commit()
        }
    }

    override fun onFragClose() {
        rootElement.mainLinearLayout.visibility = View.VISIBLE
    }

}