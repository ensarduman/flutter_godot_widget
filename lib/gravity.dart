enum Gravity {
  NO_GRAVITY,
  AXIS_SPECIFIED,
  AXIS_PULL_BEFORE,
  AXIS_PULL_AFTER,
  AXIS_CLIP,
  AXIS_X_SHIFT,
  AXIS_Y_SHIFT,
  TOP,
  BOTTOM,
  LEFT,
  RIGHT,
  CENTER_VERTICAL,
  FILL_VERTICAL,
  CENTER_HORIZONTAL,
  FILL_HORIZONTAL,
  CENTER,
  FILL,
  CLIP_VERTICAL,
  CLIP_HORIZONTAL,
  RELATIVE_LAYOUT_DIRECTION,
  HORIZONTAL_GRAVITY_MASK,
  VERTICAL_GRAVITY_MASK,
  DISPLAY_CLIP_VERTICAL,
  DISPLAY_CLIP_HORIZONTAL,
  START,
  END,
  RELATIVE_HORIZONTAL_GRAVITY_MASK,
}

extension GravityExtension on Gravity {
  int get value {
    switch (this) {
      case Gravity.NO_GRAVITY:
        return 0x0000;
      case Gravity.AXIS_SPECIFIED:
        return 0x0001;
      case Gravity.AXIS_PULL_BEFORE:
        return 0x0002;
      case Gravity.AXIS_PULL_AFTER:
        return 0x0004;
      case Gravity.AXIS_CLIP:
        return 0x0008;
      case Gravity.AXIS_X_SHIFT:
        return 0;
      case Gravity.AXIS_Y_SHIFT:
        return 4;
      case Gravity.TOP:
        return (Gravity.AXIS_PULL_BEFORE.value | Gravity.AXIS_SPECIFIED.value) << Gravity.AXIS_Y_SHIFT.value;
      case Gravity.BOTTOM:
        return (Gravity.AXIS_PULL_AFTER.value | Gravity.AXIS_SPECIFIED.value) << Gravity.AXIS_Y_SHIFT.value;
      case Gravity.LEFT:
        return (Gravity.AXIS_PULL_BEFORE.value | Gravity.AXIS_SPECIFIED.value) << Gravity.AXIS_X_SHIFT.value;
      case Gravity.RIGHT:
        return (Gravity.AXIS_PULL_AFTER.value | Gravity.AXIS_SPECIFIED.value) << Gravity.AXIS_X_SHIFT.value;
      case Gravity.CENTER_VERTICAL:
        return Gravity.AXIS_SPECIFIED.value << Gravity.AXIS_Y_SHIFT.value;
      case Gravity.FILL_VERTICAL:
        return Gravity.TOP.value | Gravity.BOTTOM.value;
      case Gravity.CENTER_HORIZONTAL:
        return Gravity.AXIS_SPECIFIED.value << Gravity.AXIS_X_SHIFT.value;
      case Gravity.FILL_HORIZONTAL:
        return Gravity.LEFT.value | Gravity.RIGHT.value;
      case Gravity.CENTER:
        return Gravity.CENTER_VERTICAL.value | Gravity.CENTER_HORIZONTAL.value;
      case Gravity.FILL:
        return Gravity.FILL_VERTICAL.value | Gravity.FILL_HORIZONTAL.value;
      case Gravity.CLIP_VERTICAL:
        return Gravity.AXIS_CLIP.value << Gravity.AXIS_Y_SHIFT.value;
      case Gravity.CLIP_HORIZONTAL:
        return Gravity.AXIS_CLIP.value << Gravity.AXIS_X_SHIFT.value;
      case Gravity.RELATIVE_LAYOUT_DIRECTION:
        return 0x00800000;
      case Gravity.HORIZONTAL_GRAVITY_MASK:
        return (Gravity.AXIS_SPECIFIED.value | Gravity.AXIS_PULL_BEFORE.value | Gravity.AXIS_PULL_AFTER.value) << Gravity.AXIS_X_SHIFT.value;
      case Gravity.VERTICAL_GRAVITY_MASK:
        return (Gravity.AXIS_SPECIFIED.value | Gravity.AXIS_PULL_BEFORE.value | Gravity.AXIS_PULL_AFTER.value) << Gravity.AXIS_Y_SHIFT.value;
      case Gravity.DISPLAY_CLIP_VERTICAL:
        return 0x10000000;
      case Gravity.DISPLAY_CLIP_HORIZONTAL:
        return 0x01000000;
      case Gravity.START:
        return Gravity.RELATIVE_LAYOUT_DIRECTION.value | Gravity.LEFT.value;
      case Gravity.END:
        return Gravity.RELATIVE_LAYOUT_DIRECTION.value | Gravity.RIGHT.value;
      case Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK:
        return Gravity.START.value | Gravity.END.value;
      default:
        return 0;
    }
  }
}
