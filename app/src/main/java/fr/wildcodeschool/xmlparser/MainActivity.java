package fr.wildcodeschool.xmlparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Parent Layout
    ViewGroup lParentView = findViewById(R.id.mainLayout);
    try {
      Inflater lXmlInflater = new Inflater(this);
      lXmlInflater.inflate(lParentView);
    } catch (IOException | XmlPullParserException  e) {
      Log.e("PARSER ERROR", e.getMessage());
    }
  }
}
