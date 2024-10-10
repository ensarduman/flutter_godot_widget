package com.my_org.flutter_godot_widget

import androidx.annotation.NonNull
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import androidx.fragment.app.FragmentActivity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import android.util.Log;



import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel




/** FlutterGodotWidgetPlugin */
class FlutterGodotWidgetPlugin: FlutterPlugin { //! VIEW REGISTORING!!!!


    
    private lateinit var channel: MethodChannel
    private var messageChannel: EventChannel? = null
    private var eventSink: EventChannel.EventSink? = null
    private val networkEventChannel = "kaiyo.ezgodot/generic"


    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        Log.d("CustomGodotPlayer", "onAttachedToEngine")
        println("in fluttergodotwidgetplugin")

        
        EventChannel(binding.binaryMessenger, networkEventChannel).setStreamHandler(godotpluginMaster(null))
        
        binding.platformViewRegistry.registerViewFactory(
                "platform-view-type",
                godotpluginMaster.GodotPluginMaster()
        )

    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {}
}
  