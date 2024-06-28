package com.my_org.flutter_godot_widget

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Handler
import android.os.Looper


import android.util.Log
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.engine.plugins.FlutterPlugin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot


class GodotPluginMaster(godot: Godot?, ) :  GodotPlugin(godot), EventChannel.StreamHandler /*MethodChannel.MethodCallHandler*/{

    private var eventSink: EventChannel.EventSink? = null

    private val EVENT_CHANNEL_NAME = "kaiyo.ezgodot/generic"


    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        Log.d("GodotpluginMaster", "onListen called")
        eventSink = events
        Log.d("GodotpluginMaster", "EventSink value: $eventSink")
        eventSink?.success("GodotPluginMaster : intial Data")
    }
    override fun onCancel(arguments: Any?) {
        Log.d("GodotpluginMaster", "onCancel called")
        eventSink = null
    }

    @UsedByGodot
    public fun sendData(mydata: String) { // send to flutter

        println("OLD lord of Data: " + mydata)
        if (eventSink != null) {
            Handler(Looper.getMainLooper()).post {
                eventSink?.success(mydata)
            }
            
            println("Data sent successfully")
        } else {
            println("EventSink is null")
        }

    }
    fun is_event_sink_ready(): Boolean {
        return eventSink != null
    }

    fun setEventSink(eventSink: EventChannel.EventSink?) {
        this.eventSink = eventSink
    }

    override fun getPluginName(): String {
        return "godotpluginMaster"
    }

    override fun getPluginSignals() = setOf(SHOW_STRANG)


    companion object {
        val SHOW_STRANG = SignalInfo("get_stang", String::class.java)


        internal fun send2Godot(godot:Godot, ad: String) {
            //eventSink?.success("JONE")
            println ("Inside send2Godot: $ad")
            //var a = GodotpluginMaster(null).actuallySend2Godot("Test123")
            // doesnt this remove it self after it's loaded?
            emitSignal(godot, "godotpluginMaster", SHOW_STRANG, ad)


            //sendData(ad)
            //emitSignal(
            //    .SHOW_STRANG.name,ad
            //)
            //emitSignal(getGodot(), "CustomGodotAndroidPlugin", new SignalInfo("test_signal"));


        }
        
    }
    

    @UsedByGodot
    public fun takestring(ad: String) {
        println ("TakeString")
        //call function to implement string processing
        if (eventSink != null) {
            Handler(Looper.getMainLooper()).post {
                eventSink?.success("TakeString")
            }
        } else {
            println("EventSink is null")
        }
        //send2Godot("Processed String")
    }


    fun bysend(data: String) {
        print("we should have the data!!!!")
        //?.success(data)
        print("should have send the data")
    }


    private var methodCall: MethodChannel.Result? = null;




}
