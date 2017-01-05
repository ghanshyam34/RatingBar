package com.gs.myratingbarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * Created by Ghanshyam on 12/24/2016.
 */
public class MyRatingBar extends LinearLayout implements View.OnClickListener {

    public static final int STYLE_SMALL = 1;
    public static final int STYLE_SMALL_MORE = 2;
    public static final int STYLE_MEDIUM = 3;
    public static final int STYLE_MEDIUM_LARGE = 4;
    public static final int STYLE_LARGE = 5;
    private int ratingStyle = STYLE_LARGE;

    private int maxCount = 5;
    private float ratingCount = 0f;
    private int activeColor = Color.YELLOW;
    private int normalColor = Color.LTGRAY;
    private int space = 2;
    private boolean isTouchable = true;
    private int backgroundColor = Color.BLACK;


    private Context mcoContext;
    private ArrayList<TextContent> textViewsList = new ArrayList<>();

    public MyRatingBar(Context context) {
        super(context);
        mcoContext = context;
    }

    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcoContext = context;

        initRating(attrs);
    }

    public MyRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        mcoContext = context;

        initRating(attrs);
    }

    private void initRating(AttributeSet attrs) {

        if (attrs != null) {

            TypedArray typedArray = mcoContext.obtainStyledAttributes(attrs, R.styleable.myCustomRatingBar);
            ratingStyle = typedArray.getInt(R.styleable.myCustomRatingBar_myRatingbarStyle, 0);
            maxCount = typedArray.getInteger(R.styleable.myCustomRatingBar_maxCount, 5);
            ratingCount = typedArray.getFloat(R.styleable.myCustomRatingBar_rating, 0);
            activeColor = typedArray.getInteger(R.styleable.myCustomRatingBar_activeColor, Color.YELLOW);
            normalColor = typedArray.getInteger(R.styleable.myCustomRatingBar_emptyColor, Color.LTGRAY);
            space = typedArray.getDimensionPixelSize(R.styleable.myCustomRatingBar_space, 0);
            isTouchable = typedArray.getBoolean(R.styleable.myCustomRatingBar_touchable, true);
            typedArray.recycle();
        }

        backgroundColor = getSolidColor();

        updateRating(false, false);
    }

    private void updateRating(boolean isActivayed, boolean isDeActivated) {

        if (getChildCount() > 0) {
            removeAllViews();
        }

        textViewsList = new ArrayList<>();
        for (int i = 0; i < maxCount; i++) {

            TextContent textContent = new TextContent();
            textContent.tv = getCircle();
            textContent.status = false;
            textContent.color = normalColor;

            if (i < (ratingCount - 0.5f)) {

                textContent.status = true;
                textContent.color = activeColor;
                textContent.tv.halfPartActiveColorColor = textContent.color;
                textContent.tv.halfPartNormalColorColor = textContent.color;

            } else if (i == (ratingCount - 0.5f)) {

                textContent.status = true;
                textContent.color = activeColor;
                textContent.tv.halfPartActiveColorColor = textContent.color;
                textContent.tv.halfPartNormalColorColor = normalColor;
            }

            if (isTouchable) {

                textContent.tv.setTag(i);

                textContent.tv.setOnTouchListener(new OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent event) {

                        int index = (int) view.getTag();
                        int left = view.getLeft();
                        int right = view.getRight();
                        int top = view.getTop();
                        int bottom = view.getBottom();
                        int width = view.getWidth();
                        int height = view.getHeight();
                        int wlayout = view.getLayoutParams().width;
                        int hlayout = view.getLayoutParams().height;

                        float evx = event.getX();
                        float evy = event.getY();

                        float rawx = event.getRawX();
                        float viewy = view.getY();

                        Rect rect = new Rect();
                        view.getGlobalVisibleRect(rect);
                        MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
                        int leftMargin = mlp.leftMargin;
                        int rightMargin = mlp.rightMargin;

                        if (rawx > rect.left && rawx < (rect.left + (width / 2))) {

                            ratingCount = index + 0.5f;
                            updateRating(false, false);

                        } else {

                            TextContent textContent = textViewsList.get(index);
                            if (index == 0 && (index + 1) < textViewsList.size() && !textViewsList.get(index + 1).status) {

                                if (!textContent.status) {

                                    textContent.color = activeColor;
                                    textContent.status = true;
                                    textContent.tv.halfPartActiveColorColor = textContent.color;
                                    textContent.tv.halfPartNormalColorColor = textContent.color;
                                    ratingCount = 1f;
                                    updateRating(false, false);

                                } else {

                                    textContent.color = normalColor;
                                    textContent.status = false;
                                    textContent.tv.halfPartActiveColorColor = textContent.color;
                                    textContent.tv.halfPartNormalColorColor = textContent.color;
                                    ratingCount = 0f;
                                    updateRating(false, false);
                                }
                                return true;
                            }

                            updateRatingCount(index + 1);

                        }
                        return false;
                    }
                });
            }

            textContent.tv.setTextColor(textContent.color);
            textViewsList.add(textContent);
            addView(textContent.tv);
        }
    }

    public void setTouchable(boolean touchable) {
        isTouchable = touchable;
        updateRating(false, false);
    }

    public void setRatingStyle(int ratingStyle) {
        this.ratingStyle = ratingStyle;
        updateRating(false, false);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        updateRating(false, false);
    }

    public void setRatingCount(float ratingCount) {
        this.ratingCount = ratingCount;
        updateRating(false, false);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        updateRating(false, false);
    }

    private void updateRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        updateRating(false, false);
    }

    public int getMaxCount() {
        return maxCount;
    }

    public float getRatingCount() {
        return ratingCount;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
        updateRating(false, false);
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        updateRating(false, false);
    }

    public void setSpace(int space) {
        this.space = space;
        updateRating(false, false);
    }

    private TextViewWithoutPaddings getCircle() {

        TextViewWithoutPaddings cursor = new TextViewWithoutPaddings(mcoContext);
        cursor.setText("\u2605");
        cursor.halfPartActiveColorColor = normalColor;
        cursor.halfPartNormalColorColor = normalColor;
        cursor.setPadding(space, space, space, space);

        switch (ratingStyle) {

            case STYLE_SMALL:
                cursor.setTextSize(20);
                break;

            case STYLE_SMALL_MORE:
                cursor.setTextSize(10);
                break;

            case STYLE_MEDIUM:
                cursor.setTextSize(30);
                break;

            case STYLE_MEDIUM_LARGE:
                cursor.setTextSize(37);
                break;

            case STYLE_LARGE:
                cursor.setTextSize(60);
                break;

        }
        cursor.invalidate();
        return cursor;
    }


    @Override
    public void onClick(View view) {

        int index = (int) view.getTag();
        int left = view.getLeft();
        int right = view.getRight();
        int top = view.getTop();
        int bottom = view.getBottom();

        TextContent textContent = textViewsList.get(index);
        if (index == 0 && (index + 1) < textViewsList.size() && !textViewsList.get(index + 1).status) {

            if (!textContent.status) {

                textContent.color = activeColor;
                textContent.status = true;
                textContent.tv.halfPartActiveColorColor = textContent.color;
                textContent.tv.halfPartNormalColorColor = textContent.color;
                ratingCount = 1f;
                updateRating(false, false);

            } else {

                textContent.color = normalColor;
                textContent.status = false;
                textContent.tv.halfPartActiveColorColor = textContent.color;
                textContent.tv.halfPartNormalColorColor = textContent.color;
                ratingCount = 0f;
                updateRating(false, false);
            }
            return;
        }

        updateRatingCount(index + 1);
    }

    private class TextContent/* extends RelativeLayout*/ {

        TextViewWithoutPaddings tv;
        boolean status = false;
        int color;
    }


    private class TextViewWithoutPaddings extends TextView {

        private final Paint mPaint = new Paint();
        private final Rect mBounds = new Rect();
        int halfPartNormalColorColor = 0;
        int halfPartActiveColorColor = 0;

        public TextViewWithoutPaddings(Context context) {
            super(context);
//            setIncludeFontPadding(false); //remove the font padding
//            setGravity(getGravity() | Gravity.TOP);
        }

//        public TextViewWithoutPaddings(Context context, AttributeSet attrs) {
//            super(context, attrs);
//            setIncludeFontPadding(false); //remove the font padding
//            setGravity(getGravity() | Gravity.TOP);
//        }
//
//        public TextViewWithoutPaddings(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//            setIncludeFontPadding(false); //remove the font padding
//            setGravity(getGravity() | Gravity.TOP);
//        }

        public int getTextWidth(String text) {

            Rect bounds = new Rect();
            Paint paint = new Paint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int height = bounds.height();
            int width = bounds.width();
            return width;
        }

        public int getTextHeight(String text) {

            Rect bounds = new Rect();
            Paint paint = new Paint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int height = bounds.height();
            int width = bounds.width();
            return height;
        }


        @Override
        protected void onDraw(@NonNull Canvas canvas) {

            canvas.drawColor(backgroundColor);

            final String text = calculateTextParams();

            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(6);
            mPaint.setTextSize(getTextSize());
//            mPaint.setTextAlign(Paint.Align.RIGHT);

            canvas.clipRect(0, 0, mBounds.width(), mBounds.height());
            canvas.drawColor(backgroundColor);

            mPaint.setColor(halfPartActiveColorColor);
            canvas.drawText(text, -mBounds.left, -mBounds.top, mPaint);

            canvas.clipRect(mBounds.width() / 2, 0, mBounds.width(), mBounds.height());
            canvas.drawColor(backgroundColor);

            mPaint.setColor(halfPartNormalColorColor);
            canvas.drawText(text, -mBounds.left, -mBounds.top, mPaint);

        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            calculateTextParams();
            setMeasuredDimension(mBounds.width() + 1, -mBounds.top + 1);
        }

        private String calculateTextParams() {
            final String text = getText().toString();
            final int textLength = text.length();
            mPaint.setTextSize(getTextSize());
            mPaint.getTextBounds(text, 0, textLength, mBounds);
            if (textLength == 0) {
                mBounds.right = mBounds.left;
            }
            return text;
        }
    }
}

//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.support.annotation.NonNull;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.support.annotation.NonNull;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import java.util.ArrayList;
///**
// * Created by Ghanshyam on 12/24/2016.
// */
//public class MyRatingBar extends LinearLayout implements View.OnClickListener {
//
//    public MyRatingBar(Context context) {
//        super(context);
//    }
//
//    public MyRatingBar(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//    }
//}