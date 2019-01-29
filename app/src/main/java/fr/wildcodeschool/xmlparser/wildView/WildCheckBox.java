package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildCheckBox extends AppCompatCheckBox implements BuilderWidget {
  public WildCheckBox(Context ctx) { super(ctx); }
  @NonNull public View getView() { return this; }
}
