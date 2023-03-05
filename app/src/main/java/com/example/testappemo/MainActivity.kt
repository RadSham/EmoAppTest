package com.example.testappemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.testappemo.data.AppDatabase
import com.example.testappemo.databinding.ActivityMainBinding
import com.example.testappemo.fragment.FragmentCloseInterface
import com.example.testappemo.fragment.LoginFragment
import com.example.testappemo.model.User


class MainActivity : AppCompatActivity(), FragmentCloseInterface {
    lateinit var binding: ActivityMainBinding
    private lateinit var loginFragment: LoginFragment
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        //hide toolbar
        supportActionBar?.hide()

        //Database
        db = AppDatabase.getDb(this)
        db.userDao().getAllUsers().asLiveData().observe(this){
            Log.d("MyLog", "db.userDao().getAllUsers() $it")
        }

        //LogIn Link
        binding.tvLogin.setOnClickListener {
            loginFragment = LoginFragment(this)
            binding.mainLinearLayout.visibility = View.GONE
            val fm = this.supportFragmentManager.beginTransaction()
            fm.replace(R.id.placeholder, loginFragment)
            fm.commit()
        }


        //Sign In Button
        binding.btnSignIn.setOnClickListener{
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()){
                Toast.makeText(this, "Введите корректный email", Toast.LENGTH_LONG).show()}
            else {
                val user = User(
                    null,
                    binding.etUserName.text.toString(),
                    binding.etMainPassword.text.toString(),
                    binding.etEmail.text.toString()
                )
                Thread {
                    db.userDao().insertAll(user)
                }.start()
            }
            startActivity(Intent(this, ShopActivity::class.java))
        }
    }

    override fun onFragClose() {
        binding.mainLinearLayout.visibility = View.VISIBLE
    }

}