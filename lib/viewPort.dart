import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';


class ViewportGame extends StatefulWidget {
  const ViewportGame({super.key});

  @override
  State<ViewportGame> createState() => _ViewPortState();
}
class _ViewPortState extends State<ViewportGame> {
  final String _platformVersion = 'Unknown';
  //final _flutterGodotWidgetPlugin = FlutterGodotWidget();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // This is used in the platform side to register the view.
    const String viewType = 'platform-view-type';
    // Pass parameters to the platform side.
    const Map<String, dynamic> creationParams = <String, dynamic>{};
    final gamz=[];
    var zz = SizedBox(
      width: 300, // Set the desired width
      height: 300, // Set the desired height
      child: PlatformViewLink(
        viewType: viewType,
        surfaceFactory: (context, controller) {
          return AndroidViewSurface(
            controller: controller as AndroidViewController,
            gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
          );
        },
        onCreatePlatformView: (params) {
          return PlatformViewsService.initSurfaceAndroidView(
            id: params.id,
            viewType: viewType,
            layoutDirection: TextDirection.ltr,
            creationParams: creationParams,
            creationParamsCodec: const StandardMessageCodec(),
            onFocus: () {
              params.onFocusChanged(true);
            },
          )
            ..addOnPlatformViewCreatedListener(params.onPlatformViewCreated)
            ..create();
        },
      ),
    );

    return Center(
      child: Padding(
        padding: const EdgeInsets.all(16.0), // Add padding around the view
        child: zz,
      ),
    );
  }
}

