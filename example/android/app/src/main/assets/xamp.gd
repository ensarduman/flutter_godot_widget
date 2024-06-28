extends Node
var singleton
#signal flutterData (ourTextExample)

# Called when the node enters the scene tree for the first time.
func _ready():
	
	if Engine.has_singleton("godotpluginMaster"):
		singleton = Engine.get_singleton("godotpluginMaster")
		singleton.connect("get_stang", examp) # this is what our get_stang is actually calling

		print("Yo we STARTED godot")

	else: 
		print("cloudnt find the plugin. THATS NOT GOOOD!!!!")
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass


func _on_button_button_down():
	print("btn was pressed")
	if singleton:
		singleton.sendData("got diz data")

	pass # Replace with function body.

func examp(ourExampleTxt: String):
	#emit_signal("FlutterData", ourExampleTxt)
	#we send our flutter data to here

	print(ourExampleTxt)
	pass
