package com.example.task6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.task6.data.Constants
import com.example.task6.databinding.ActivityMainBinding
import com.example.task6.fragments.DownFragment
import com.example.task6.interfaces.Communicator
import io.reactivex.rxjava3.core.Observable

class MainActivity : AppCompatActivity() , Communicator{

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun sendData(text: String) {
        val downFragment = DownFragment()
        val bundle = Bundle()
        bundle.putString(Constants.VALUE,text)
        downFragment.arguments= bundle
        getData(downFragment)
    }
    private fun getData(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
            .remove(fragment)
            .add(R.id.down_fragment, fragment)
            .commit()
    }
}