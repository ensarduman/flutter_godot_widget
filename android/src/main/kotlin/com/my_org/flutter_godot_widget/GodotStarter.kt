package com.my_org.flutter_godot_widget

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.engine.dart.DartExecutor



import org.godotengine.godot.Godot
import org.godotengine.godot.GodotFragment
import org.godotengine.godot.GodotHost
import org.godotengine.godot.plugin.GodotPlugin

import java.util.concurrent.CompletableFuture


import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import io.flutter.plugin.platform.PlatformView

/**
 * Implements the [GodotHost] interface so it can access functionality from the [Godot] instance.
 */


class GodotStarter(context: Context, id: Int, creationParams: Map<String?, Any?>?) : GodotHost, PlatformView {
    private var godotFragment: GodotFragment = GodotFragment()

    private fun initializegodot(){

        godotFragment = GodotFragment()
    }

    override fun getActivity() = godotFragment.activity

    override fun getGodot() = godotFragment.godot!!

    override fun getView(): View { //! NATIVE

        initializegodot()
        print("Should be Working?")
        print(godotFragment.godot)
        print("Should be sending odd godot shit???")

        return godotFragment.view ?: throw IllegalStateException("Fragment view is not yet created")
    }

    override fun dispose() {}


}
