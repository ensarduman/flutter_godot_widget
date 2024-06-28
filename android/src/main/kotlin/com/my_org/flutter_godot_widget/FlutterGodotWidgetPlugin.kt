package com.my_org.flutter_godot_widget

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterGodotWidgetPlugin */
class FlutterGodotWidgetPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_godot_widget")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}


/*

CLASS FLUTTERWIDGETPLUGIN() {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  private val networkEventChannel = "kaiyo.ezgodot/generic"
  private val METHOD_CHANNEL_NAME = "com.kaiyo.ezgodot/method/start"
  private var methodChannel: MethodChannel? = null
  //private var eventSink: EventChannel.EventSink? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_godot_widget")
    channel.setMethodCallHandler(this)

    
    //methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, METHOD_CHANNEL_NAME)
    //    methodChannel?.setMethodCallHandler { call, result ->
    //        handleMethodCall(call, result)
    //    }
     

    
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    //if (call.method == "getPlatformVersion") {
     // result.success("Android ${Build.VERSION.RELEASE}")
    //}
    if (call.method== "openGame") {
        print("2 handlemethodcalls");
        val intent = Intent(this, GodotStarter::class.java)
        startActivity(intent)
        
        result.success("Game opened")

      }
    if (call.method== "sendData2Godot") {
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
}

 */