package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildButton extends AppCompatButton implements BuilderWidget {
  public WildButton(Context pCtx) {
    super(pCtx);
  }
  @NonNull public View getView() { return this; }
}