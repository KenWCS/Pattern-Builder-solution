package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildEditText extends AppCompatEditText implements BuilderWidget {
  public WildEditText(Context ctx) {
    super(ctx);
  }
  @NonNull public View getView() { return this; }
}
