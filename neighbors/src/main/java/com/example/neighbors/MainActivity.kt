package com.example.neighbors

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.neighbors.fragments.AddNeighbourFragment
import com.example.neighbors.fragments.ListNeighborsFragment
import java.nio.file.Files.list

class MainActivity : AppCompatActivity(), NavigationListener{

    private lateinit var toolbar: Toolbar
    @SuppressLint("SupportAnnotationUsage")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment(ListNeighborsFragment())


    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()

    }
    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }


}