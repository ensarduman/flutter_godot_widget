
import 'flutter_godot_widget_platform_interface.dart';

class FlutterGodotWidget {
  Future<String?> getPlatformVersion() {
    return FlutterGodotWidgetPlatform.instance.getPlatformVersion();
  }
  Future<String?> openGame() {
    return FlutterGodotWidgetPlatform.instance.getPlatformVersion();
  }
  Future<String?> sendData2Game() {
    return FlutterGodotWidgetPlatform.instance.getPlatformVersion();
  }
}
