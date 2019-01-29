package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Space;

import fr.wildcodeschool.xmlparser.builder.BuilderWidget;

public class WildSpace implements BuilderWidget {
  private final Space mSpace;

  public WildSpace(Context ctx) {
    mSpace = new Space(ctx);
  }
  @NonNull public View getView() { return mSpace; }
}
