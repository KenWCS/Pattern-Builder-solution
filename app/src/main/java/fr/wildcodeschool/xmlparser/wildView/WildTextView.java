package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildTextView extends AppCompatTextView implements BuilderWidget {
  public WildTextView(Context ctx) { super(ctx); }
  @NonNull public View getView() { return this; }
}
