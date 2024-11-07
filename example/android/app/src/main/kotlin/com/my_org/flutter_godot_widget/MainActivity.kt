package com.my_org.flutter_godot_widget


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.my_org.flutter_godot_widget.godotpluginMaster
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot

/*import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot*/


class MainActivity: FlutterFragmentActivity()/*,GodotPlugin(godot)*/, EventChannel.StreamHandler{
    private var faf: String = ""

    private var eventSink: EventChannel.EventSink? = null

    private val EVENT_CHANNEL_NAME = "kaiyo.ezgodot/generic"

    init {
        /*instance= this*/ //set the static refernce
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        println("in mainactivity")
        super.onCreate(savedInstanceState)

    }

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
        faf = mydata
        println("OLD lord of Data: MA " + faf)
        if (eventSink != null) {
            Handler(Looper.getMainLooper()).post {
                eventSink?.success(faf)
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

    
    companion object {
       /* private var instance: GodotpluginMaster? = null*/

        val SHOW_STRANG = SignalInfo("get_stang", String::class.java)

        fun send2Godot(ad: String) {
            //eventSink?.success("JONE")
            println("Inside send2Godot: $ad")
            

            //sendData(ad)
        }
    }
        
      /*  @UsedByGodot*/
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
}
