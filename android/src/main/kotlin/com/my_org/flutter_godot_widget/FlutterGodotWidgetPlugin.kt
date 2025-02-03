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
            val displayMetrics = Resources.getSystem().displayMetrics
            val screenWidthPx = displayMetrics.widthPixels // Cihazın tam genişliği (px)
            val screenHeightPx = displayMetrics.heightPixels // Cihazın tam yüksekliği (px)

            val xDp = call.argument<Double>("x")?.toFloat() ?: 0f  // X konumu (DP)
            val yDp = call.argument<Double>("y")?.toFloat() ?: 0f  // Y konumu (DP)
            val widthDp = call.argument<Double>("width")?.toFloat() // Genişlik (DP) (nullable)
            val heightDp = call.argument<Double>("height")?.toFloat() // Yükseklik (DP) (nullable)

            // DP -> PX dönüşümü
            val xPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, xDp, displayMetrics).toInt()
            val yPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, yDp, displayMetrics).toInt()

            // Eğer width null ise, tam ekran genişliği kullan
            val widthPx = if (widthDp != null) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthDp, displayMetrics).toInt()
            } else {
                screenWidthPx // Tam ekran genişliği (px)
            }

            // Eğer height null ise, tam ekran yüksekliği kullan
            val heightPx = if (heightDp != null) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightDp, displayMetrics).toInt()
            } else {
                screenHeightPx // Tam ekran yüksekliği (px)
            }

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
