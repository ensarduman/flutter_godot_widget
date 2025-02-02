package com.my_org.flutter_godot_widget

import androidx.annotation.NonNull
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import android.view.View
import android.widget.FrameLayout
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import androidx.fragment.app.FragmentActivity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import android.util.Log

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel

/** FlutterGodotWidgetPlugin */
class FlutterGodotWidgetPlugin: FlutterPlugin, MethodCallHandler { //! VIEW REGISTORING!!!!

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

        channel = MethodChannel(binding.binaryMessenger, "flutter_godot_widget_plugin")
        channel.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (call.method == "setGodotViewPositionAndSize") {
            val x = call.argument<Double>("x")?.toInt() ?: 0  // X konumu
            val y = call.argument<Double>("y")?.toInt() ?: 0  // Y konumu
            val width = call.argument<Double>("width")?.toInt() ?: 0  // Genişlik
            val height = call.argument<Double>("height")?.toInt() ?: 0  // Yükseklik

            // Godot View'in yeni boyut ve konumunu ayarla
            godotView?.apply {
                translationX = x.toFloat()
                translationY = y.toFloat()
                layoutParams = FrameLayout.LayoutParams(width, height)
            }

            result.success(null)
        } else {
            result.notImplemented()
        }
    }

    companion object {
        var godotView: View? = null
    }
}
