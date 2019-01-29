package fr.wildcodeschool.xmlparser.builder;

import android.support.annotation.NonNull;
import android.view.View;

@SuppressWarnings("unused")
public interface BuilderWidget {
  @NonNull View getView();

  // default methods
  default void setText(CharSequence text) {}
  default void setInputType(int pInputType) {}
  default void setEms(int ems) {}
  default void setHint(CharSequence hint) {}
  default void setOrientation(int orientation) {}
  default void setWeightSum(float weightSum) {}
  default void setBackgroundColor(int color) {}
  default void setTextColor(int color) {}
  default void setTextSize(float size) {}
}
