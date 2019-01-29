package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildLinearLayout extends LinearLayout implements BuilderWidget {
  public WildLinearLayout(Context ctx) {
    super(ctx);
  }
  @NonNull public View getView() { return this; }
}
