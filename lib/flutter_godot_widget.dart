
import 'flutter_godot_widget_platform_interface.dart';

class FlutterGodotWidget {
  Future<String?> getPlatformVersion() {
    return FlutterGodotWidgetPlatform.instance.getPlatformVersion();
  }
}
