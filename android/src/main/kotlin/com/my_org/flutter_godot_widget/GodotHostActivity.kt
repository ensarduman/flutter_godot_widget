package com.my_org.flutter_godot_widget

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import org.godotengine.godot.GodotFragment

class GodotHostActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val godotFragment = GodotFragment()
        transaction.replace(android.R.id.content, godotFragment)
        transaction.commit()
    }
}
