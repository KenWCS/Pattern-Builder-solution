package fr.wildcodeschool.xmlparser.builder;

import android.content.Context;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BuilderTool {
  /**
   * Convert from unit to pixel
   * @param value Entry value to convert
   * @param unit Unit of entry value
   * @return The converted numerical value to pixel unit
   */
  static int convertToPixel(Context ctx, String value, String unit) {
    DisplayMetrics lDisplayMetrics = ctx.getResources().getDisplayMetrics();
    int pixel = 0;
    try {
      int num = Integer.valueOf(value);
      switch (unit) {
        case "px":
          pixel = num;
          break;
        case "dp":
          pixel = Math.round(num * (lDisplayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
          break;
        case "sp":
          pixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, num, lDisplayMetrics);
          break;
        default:
          break;
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }

    return pixel;
  }

  /**
   * Convert string representation of InputType to its int value
   * @param pInputType String representation of input type
   * @return int: InputType value
   */
  static int getInputType(String pInputType) {
    switch (pInputType) {
      case "textPersonName":
        return InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
      default:
        return InputType.TYPE_NULL;
    }
  }

  /**
   * Convert string representation of gravity to its int value
   * @param pGravity String representation of gravity
   * @return int: Gravity value
   */
  static int getLayoutGravity(String pGravity) {
    int value;
    switch (pGravity) {
      case "center_horizontal":
        value = Gravity.CENTER_HORIZONTAL;
        break;
      case "center_vertical":
        value = Gravity.CENTER_VERTICAL;
        break;
      case "center":
        value = Gravity.CENTER;
        break;
      default:
        value = Gravity.NO_GRAVITY;
        break;
    }
    return value;
  }

  /**
   * Return a list of elements matches with the regex
   * @param value String: character sequence to analyse
   * @param regex String: regex
   * @return A list with the patterns matches with the regex
   */
  static List<String> tokenize(String value, String regex) {
    List<String> list = new ArrayList<>();
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(value);
    if (matcher.find()) {
      int count = matcher.groupCount();
      for (int i = 1; i <= count; i++)
        list.add(matcher.group(i));
    }
    return list;
  }
}
