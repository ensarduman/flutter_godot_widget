import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_godot_widget_platform_interface.dart';

/// An implementation of [FlutterGodotWidgetPlatform] that uses method channels.
class MethodChannelFlutterGodotWidget extends FlutterGodotWidgetPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_godot_widget');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
