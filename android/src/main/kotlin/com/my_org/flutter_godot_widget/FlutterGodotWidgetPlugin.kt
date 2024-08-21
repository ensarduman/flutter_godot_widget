package com.my_org.flutter_godot_widget

import androidx.annotation.NonNull
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import androidx.fragment.app.FragmentActivity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding



import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** FlutterGodotWidgetPlugin */
class FlutterGodotWidgetPlugin: FlutterPlugin { //! VIEW REGISTORING!!!!
    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        println("in fluttergodotwidgetplugin")

        binding.platformViewRegistry.registerViewFactory(
                "platform-view-type",
                GodotPluginMaster()
        )

    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {}
}
  /*private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_godot_widget")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      //result.success("Android ${android.os.Build.VERSION.RELEASE}")
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
      
    }
    if (call.method == "openGame") {
      print("2 handlemethodcalls");
      //val intent = Intent(this, GodotStarter::class.java)
      //startActivity(intent)
      val intent = Intent(applicationContext, GodotStarter::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK // Make sure to add this flag
      }
      applicationContext.startActivity(intent)
      result.success("Game opened")
    }
    if (call.method == "sendData2Godot") {
      val data = call.argument<String>("data")
      println("Arguments kotlin: ${call.arguments}")
      data?.let {
          //GodotpluginMaster.send2Godot(data)
          //GodotpluginMaster.sendData2Flut()
          result.success("Data sent to Godot: $data")
      } ?: run {
          result.error("MISSING_DATA", "Data argument is missing", null)
      }
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}*/