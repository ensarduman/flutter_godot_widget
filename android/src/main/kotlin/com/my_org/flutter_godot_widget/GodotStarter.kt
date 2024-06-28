package com.my_org.flutter_godot_widget

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.content.Intent
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

class GodotStarter: AppCompatActivity(), GodotHost, EventChannel.StreamHandler {

    private val networkEventChannel = "kaiyo.ezgodot/generic"
    private lateinit var eventChannel: EventChannel
    private val METHOD_CHANNEL_NAME = "com.kaiyo.ezgodot/method/start"
    private var eventSink: EventChannel.EventSink? = null
    private var godotFragment: GodotFragment? = null
    private var appPlugin: GodotPluginMaster? = null
    private var methodChannel: MethodChannel? = null
    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.godot_app_layout) //! will this work?

        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, METHOD_CHANNEL_NAME)
        methodChannel?.setMethodCallHandler { call, result ->
            handleMethodCall(call, result)
        }

        eventChannel = EventChannel(flutterEngine.dartExecutor.binaryMessenger, networkEventChannel)
        eventChannel.setStreamHandler(this)

        println("yeah boi")
        println("shoulda Created dat nigga")

    }

    private fun initializegodot(){
        val currentGodotFragment = supportFragmentManager.findFragmentById(R.id.godot_fragment_container)
        if (currentGodotFragment is GodotFragment) {
            godotFragment = currentGodotFragment
        } else {
            godotFragment = GodotFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.godot_fragment_container, godotFragment!!)
                .commitNowAllowingStateLoss()
        }
        godotFragment?.godot?.let { initAppPluginIfNeeded(it) }
    }
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        eventSink = events
        println("MainActivitytwo : EventSink set in onListen")
        println("MainActivitytwo : EventSink: $eventSink")
        Handler(Looper.getMainLooper()).post {
            eventSink?.success("MainAcitivitytwo : Initial Data from onListen")
        }
        print("MainActivitytwo : Data sent via EventSink on onlisten")
        initializegodot()
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
        println("MainActivitytwo : EventSink canceled")
    }


    fun sendz(){
        eventSink?.success("send dat data")
        print("MainActivitytwo : Data sent via EventSink")
    }

    private fun initAppPluginIfNeeded(godot: Godot) {
        if (appPlugin == null) {
            appPlugin = GodotPluginMaster(godot)
            appPlugin?.setEventSink(eventSink)
        }
    }

    override fun getActivity() = this

    override fun getGodot() = godotFragment?.godot

    override fun getHostPlugins(godot: Godot): Set<GodotPlugin> {
        initAppPluginIfNeeded(godot)
        return setOf(appPlugin!!)
    }

    private fun handleMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {

            "sendData2Godot" ->{
                val data = call.argument<String>("data")
                println("Arguments java: ${call.arguments}")

                data?.let {

                    this.godot?.let { it1 -> GodotPluginMaster.send2Godot(it1,data) }
                    result.success("Data sent to Godot: $data")
                } ?: run {
                    result.error("MISSING_DATA", "Data argument is missing", null)
                }
            }
            else -> {
                result.notImplemented()
            }
        }
    }
}


