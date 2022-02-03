package com.example.hsdemosoft.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hsdemosoft.R
import com.example.hsdemosoft.databinding.ActivityMainBinding
import com.example.hsdemosoft.ui.main.ListFragment
import com.example.hsdemosoft.utils.Utilities
import java.sql.ClientInfoStatus

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        if(resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.detailsFragment.visibility = View.GONE
        } else {
            binding.detailsFragment.visibility = View.VISIBLE
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.listFragment, ListFragment())
            .addToBackStack("item")
            .commit()


    }

    fun changeFragment(
        fm: Fragment,
        bundls: HashMap<String, String>?
    ) {
        bundls?.let {
            val bndl = Bundle()
            for(i in bundls){
                bndl.putString(i.key, i.value)
            }
            fm.arguments = bndl
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.detailsFragment, fm)
            .addToBackStack("item")
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.detailsFragment.visibility = View.GONE
            binding.listFragment.visibility = View.VISIBLE
        } else {
            binding.detailsFragment.visibility = View.VISIBLE
            binding.listFragment.visibility = View.VISIBLE
        }
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount == 0 || resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
        }
    }



    
}

