package com.my_org.flutter_godot_widget

import androidx.annotation.NonNull
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.EventChannel

/** FlutterGodotWidgetPlugin */
class FlutterGodotWidgetPlugin: FlutterPlugin, MethodCallHandler {

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
            val xDp = call.argument<Double>("x")?.toFloat() ?: 0f  // X konumu (DP)
            val yDp = call.argument<Double>("y")?.toFloat() ?: 0f  // Y konumu (DP)
            val widthDp = call.argument<Double>("width")?.toFloat() ?: 0f  // Genişlik (DP)
            val heightDp = call.argument<Double>("height")?.toFloat() ?: 0f // Yükseklik (DP)

            // DP -> PX dönüşümü
            val displayMetrics = Resources.getSystem().displayMetrics
            val xPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, xDp, displayMetrics).toInt()
            val yPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, yDp, displayMetrics).toInt()
            val widthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthDp, displayMetrics).toInt()
            val heightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightDp, displayMetrics).toInt()

            // Godot View'in yeni boyut ve konumunu ayarla
            godotView?.apply {
                translationX = xPx.toFloat()
                translationY = yPx.toFloat()
                layoutParams = FrameLayout.LayoutParams(widthPx, heightPx)
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
