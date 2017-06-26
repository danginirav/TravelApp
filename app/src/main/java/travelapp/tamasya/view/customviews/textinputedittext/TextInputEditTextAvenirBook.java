package travelapp.tamasya.view.customviews.textinputedittext;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

import travelapp.tamasya.view.customviews.FontMethods;

/**
 * Created by nirav on 26/6/17.
 */

public class TextInputEditTextAvenirBook extends TextInputEditText {

    public TextInputEditTextAvenirBook(Context context) {
        super(context);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }

    public TextInputEditTextAvenirBook(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }

    public TextInputEditTextAvenirBook(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }
}
