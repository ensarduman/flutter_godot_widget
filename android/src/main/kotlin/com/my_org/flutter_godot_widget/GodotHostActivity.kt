package com.my_org.flutter_godot_widget

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import org.godotengine.godot.GodotFragment

class GodotHostActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onGodotHostActivityCreatedEvent(this)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val godotFragment = GodotFragment()
        transaction.replace(android.R.id.content, godotFragment)
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private lateinit var godotHostActivityCreatedEvent : (GodotHostActivity) -> Unit

        fun setGodotHostActivityCreatedEvent(action: (GodotHostActivity) -> Unit){
            godotHostActivityCreatedEvent = action
        }

        fun onGodotHostActivityCreatedEvent(activity: GodotHostActivity){
            godotHostActivityCreatedEvent(activity)
        }
    }
}
