package fr.wildcodeschool.xmlparser.builder;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import java.util.List;

public class BuilderDirector {
  // TAG
  private static final String TAG = "BuilderDirector";
  // Activity context
  private Context       mContext;
  // Pattern builder representation
  private BuilderWidget mWidget;
  // Layout
  private int mWidth   = LinearLayout.LayoutParams.MATCH_PARENT;
  private int mHeight  = LinearLayout.LayoutParams.MATCH_PARENT;
  private int mGravity = Gravity.NO_GRAVITY;
  // Weight
  private float mWeight = 0.f;
  // Margin
  private int mMarginBottom = 0;
  private int mMarginRight = 0;
  private int mMarginLeft = 0;
  private int mMarginTop = 0;
  // Padding
  private int mPaddingBottom;
  private int mPaddingRight;
  private int mPaddingLeft;
  private int mPaddingTop;

  public BuilderDirector(Context ctx, BuilderWidget pWidget) {
    mWidget  = pWidget;
    mContext = ctx;

    mPaddingTop = mWidget.getView().getPaddingTop();
    mPaddingLeft = mWidget.getView().getPaddingLeft();
    mPaddingRight = mWidget.getView().getPaddingRight();
    mPaddingBottom = mWidget.getView().getPaddingBottom();
  }

  public void setParams(XmlPullParser pParser) {
    String name, value;
    List<String> token;

    int lCount = pParser.getAttributeCount();
    // Loop on each attribute
    for (int lIndex = 0; lIndex < lCount; lIndex++) {
      // Get the name and the value of the attribute
      name  = pParser.getAttributeName(lIndex);
      value = pParser.getAttributeValue(lIndex);

      // Check attribute name prefix
      if (name.startsWith("android:")) {
        // Remove the prefix and compare the key with predefined attributes
        String key = name.substring(8);
        switch (key) {
          case "id":
            /* Nothing to do */
            break;
          case "inputType":
            mWidget.setInputType(BuilderTool.getInputType(value));
            break;
          case "ems":
              mWidget.setEms(Integer.valueOf(value));
            break;
          case "text":
            mWidget.setText(value);
            break;
          case "hint":
            mWidget.setHint(value);
            break;
          case "textSize":
            token = BuilderTool.tokenize(value, "([0-9]*)sp");
            if (token.size() == 1)
              mWidget.setTextSize(Float.parseFloat(token.get(0)));
            break;
          case "textColor":
            mWidget.setTextColor(Color.parseColor(value));
            break;
          case "background":
            mWidget.setBackgroundColor(Color.parseColor(value));
            break;
          case "orientation":
            mWidget.setOrientation( value.equals("horizontal") ?
              LinearLayout.HORIZONTAL : LinearLayout.VERTICAL );
            break;
          case "weightSum":
            mWidget.setWeightSum(Float.parseFloat(value));
            break;
          case "paddingHorizontal":
          case "paddingVertical":
            token = BuilderTool.tokenize(value, "([0-9]*)([a-z]*)");
            if (token.size() == 2) {
              int px = BuilderTool.convertToPixel(mContext, token.get(0), token.get(1));
              if (key.equals("paddingVertical"))
                mPaddingTop = mPaddingBottom = px;
              else
                mPaddingLeft = mPaddingRight = px;
            }
            break;
          case "layout_width":
            if ("wrap_content".equals(value)) {
              mWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
            break;
          case "layout_height":
            if ("wrap_content".equals(value)) {
              mHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
            break;
          case "layout_marginHorizontal":
          case "layout_marginVertical":
            token = BuilderTool.tokenize(value, "([0-9]*)([a-z]*)");
            if (token.size() == 2) {
              int px = BuilderTool.convertToPixel(mContext, token.get(0), token.get(1));
              if (key.equals("layout_marginVertical"))
                mMarginTop = mMarginBottom = px;
              else
                mMarginLeft = mMarginRight = px;
            }
            break;
          case "layout_gravity":
            mGravity = BuilderTool.getLayoutGravity(value);
            break;
          case "layout_weight":
            mWeight = Float.valueOf(value);
            break;
          default:
            Log.i(TAG, "Unknown Attribute ["+key+"]");
            break;
        }
      }
    }

    // Set padding
    mWidget.getView().setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
    // Set layout parameters
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mWidth, mHeight);
    layoutParams.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
    layoutParams.weight = mWeight;
    layoutParams.gravity = mGravity;
    mWidget.getView().setLayoutParams(layoutParams);
  }
}
