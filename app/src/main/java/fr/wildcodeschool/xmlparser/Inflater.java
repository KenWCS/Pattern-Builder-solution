package fr.wildcodeschool.xmlparser;

import android.content.Context;
import android.util.Xml;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import fr.wildcodeschool.xmlparser.builder.BuilderDirector;
import fr.wildcodeschool.xmlparser.builder.BuilderWidget;
import fr.wildcodeschool.xmlparser.wildView.WildButton;
import fr.wildcodeschool.xmlparser.wildView.WildCheckBox;
import fr.wildcodeschool.xmlparser.wildView.WildEditText;
import fr.wildcodeschool.xmlparser.wildView.WildLinearLayout;
import fr.wildcodeschool.xmlparser.wildView.WildSpace;
import fr.wildcodeschool.xmlparser.wildView.WildTextView;

class Inflater {
  // Activity context
  private Context ctx;

  // Constructor should only contains initialisation
  Inflater(Context ctx) {
    this.ctx = ctx;
  }

  /**
   * This method parse the xml layout to populate the activity screen
   * @param pParent Parent layout
   */
  void inflate(ViewGroup pParent) throws IOException, XmlPullParserException {
    // Store the parent
    ViewGroup lParentView = pParent;

    // INFO WCS - Here is how to keep a file from the assets directory
    InputStream lXmlStream = this.ctx.getAssets().open("content_assets.xml");

    // XML parser initialization
    XmlPullParser parser = Xml.newPullParser();
    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
    parser.setInput(lXmlStream, null);

    // Loop on XML nodes
    int eventType = parser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      if(eventType == XmlPullParser.START_TAG) {
        BuilderWidget lWidget = null;

        switch (parser.getName()) {
          case "LinearLayout":
            lWidget = new WildLinearLayout(ctx);
            break;
          case "EditText":
            lWidget = new WildEditText(ctx);
            break;
          case "Button":
            lWidget = new WildButton(ctx);
            break;
          case "TextView":
            lWidget = new WildTextView(ctx);
            break;
          case "CheckBox":
            lWidget = new WildCheckBox(ctx);
            break;
          case "Space":
            lWidget = new WildSpace(ctx);
            break;
          default:
            break;
        }
        // Check widget validity
        if (null != lWidget) {
          BuilderDirector lBuilder = new BuilderDirector(ctx, lWidget);
          lBuilder.setParams(parser);
          lParentView.addView(lWidget.getView());
          if (lWidget instanceof ViewGroup) {
            lParentView = (ViewGroup) lWidget;
          }
        }
      }
      else if (eventType == XmlPullParser.END_TAG) {
        switch (parser.getName()) {
          case "LinearLayout":
            lParentView = (ViewGroup)lParentView.getParent();
            break;
          default:
            break;
        }
      }
      parser.next();
      eventType = parser.getEventType();
    }
  }
}
