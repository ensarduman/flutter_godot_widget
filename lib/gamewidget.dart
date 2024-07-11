import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';



class Gamewidget extends StatefulWidget {
  @override
  _gamewidget createState() => _gamewidget();
}

class _gamewidget extends State<Gamewidget> {
  bool _showNativeView = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('PlatformView Example'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () {
                setState(() {
                  _showNativeView = true;
                });
              },
              child: Text('Show Native View'),
            ),
            if (_showNativeView)
              Container(
                width: 300,
                height: 300,
                child: PlatformViewLink(
                  viewType: 'platform-view-type',
                  surfaceFactory: (context, controller) {
                    //Future.delayed(const Duration(seconds:9 ),(){

                    return AndroidViewSurface(
                      controller: controller as AndroidViewController,
                      gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
                      hitTestBehavior: PlatformViewHitTestBehavior.opaque,
                    );
                   // });
                  }, onCreatePlatformView: (PlatformViewCreationParams params) {
                    return PlatformViewsService.initSurfaceAndroidView(id: params.id,
                      viewType: 'platform-view-type',
                      layoutDirection: TextDirection.ltr);

                  },
                  //nCreatePlatformView: (params) {
                    /*return PlatformViewsService.initSurfaceAndroidView(
                      id: params.id,
                      viewType: 'platform-view-type',
                      layoutDirection: TextDirection.ltr,
                      creationParams: null,
                      creationParamsCodec: StandardMessageCodec(),
                    )
                      ..create();*/
                  //    print("jh");
                  //},
                ),
              ),
          ],
        ),
      ),
    );
  }
}