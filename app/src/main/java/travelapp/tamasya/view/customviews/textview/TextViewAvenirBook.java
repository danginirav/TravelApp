package travelapp.tamasya.view.customviews.textview;

import android.content.Context;
import android.util.AttributeSet;

import travelapp.tamasya.view.customviews.FontMethods;


/**
 * Created by Nirav-Dangi on 16/09/16.
 */
public class TextViewAvenirBook extends android.support.v7.widget.AppCompatTextView {

    public TextViewAvenirBook(Context context) {
        super(context);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }

    public TextViewAvenirBook(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }

    public TextViewAvenirBook(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontMethods.setTypeface(context,this,"Avenir-Book.ttf");
    }
}
