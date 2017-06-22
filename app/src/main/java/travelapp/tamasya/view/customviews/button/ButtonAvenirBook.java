package travelapp.tamasya.view.customviews.button;

import android.content.Context;
import android.util.AttributeSet;

import travelapp.tamasya.view.customviews.FontMethods;

/**
 * Created by Nirav-Dangi on 16/09/16.
 */
public class ButtonAvenirBook extends android.support.v7.widget.AppCompatButton {
    public ButtonAvenirBook(Context context) {
        super(context);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }

    public ButtonAvenirBook(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }

    public ButtonAvenirBook(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }
}
