package com.example.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val boggleViewModel: BoggleViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generate_alphabets();

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = Alphabets()

        val mBundle = Bundle()
        mBundle.putStringArrayList("alpha", ArrayList<String>(boggleViewModel.alpha))
        mFragment.arguments = mBundle
        mFragmentTransaction.add(R.id.fragment1, mFragment).commit()
    }

    fun generate_alphabets(){
        if(boggleViewModel.alpha.isEmpty()){
            var rand=' ';
            var alpha= mutableListOf<String>()
            for (i in 1..16) {
                rand = ('A'..'Z').random()
                alpha.add(rand.toString())
            }
            boggleViewModel.addAlpha(alpha.toList())
        }
    }


}