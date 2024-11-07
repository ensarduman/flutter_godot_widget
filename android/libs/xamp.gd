extends Node
var singleton
#signal flutterData (ourTextExample)

# Called when the node enters the scene tree for the first time.
func _ready():
	#var q = load("res://addons/HelloWorld/HelloWorld.gd")
	#var l = q.new()
	#Engine.register_singleton("godotpluginMaster",Object)
	if Engine.has_singleton("godotpluginMaster"):
		singleton = Engine.get_singleton("godotpluginMaster")
		singleton.connect("get_stang", examp("data sent")) # this is what our get_stang is actually calling

		print("Yo we STARTED godot")

	else: 
		print("couldnt find the plugin. THATS NOT GOOOD!!!!")
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass


func _on_button_button_down():
	print("btn was pressed")
	singleton.connect("get_stang", examp("data sent"))
	pass # Replace with function body.


# Function for handling BackButton pressed action to close the game view
func _on_BackButton_pressed():
	print("Back button pressed! Sending command to close the game view and return to Flutter.")
	# Send a message to Flutter to close Godot and return to the Flutter view
	if singleton:
		singleton.GoBack("close_godot_view")

func examp(ourExampleTxt: String):
	#emit_signal("FlutterData", ourExampleTxt)
	#we send our flutter data to here

	print(ourExampleTxt)
	pass
