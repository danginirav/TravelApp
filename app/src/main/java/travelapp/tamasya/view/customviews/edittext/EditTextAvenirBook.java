package travelapp.tamasya.view.customviews.edittext;

import android.content.Context;
import android.util.AttributeSet;

import travelapp.tamasya.view.customviews.FontMethods;

/**
 * Created by Nirav-Dangi on 16/09/16.
 */
public class EditTextAvenirBook extends android.support.v7.widget.AppCompatEditText {
    public EditTextAvenirBook(Context context) {
        super(context);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }

    public EditTextAvenirBook(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }

    public EditTextAvenirBook(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontMethods.setTypeface(context, this, "Avenir-Book.ttf");
    }
}
