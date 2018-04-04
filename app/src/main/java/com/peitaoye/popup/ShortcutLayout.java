package com.peitaoye.popup;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PeitaoYe on 2018/4/3.
 */

public class ShortcutLayout extends LinearLayout {


    private List<Integer> imgSet;
    private List<String> labelSet;
    private int item_num;
    List<ShortcutItem> dataSet;

    private int padding=10;
    private Context context;

    private ValueAnimator mAnimator;
    private float mAnimation_value;
    private boolean isAnimated=false;
    public ShortcutLayout(Context context) {
        this(context, null);
    }

    public ShortcutLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShortcutLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.context = context;
        init();
    }

    public void setImgAndLabelSet(List<Integer> imgSet,List<String> labelSet) {
        this.imgSet = imgSet;
        this.labelSet = labelSet;
        createShortItems();
    }


    private void init() {
        setOrientation(HORIZONTAL);
        LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setPadding(padding,padding,padding,padding);
        setLayoutParams(lp);

        //set up ObjectAnimator
        mAnimator= ValueAnimator.ofFloat(0,1);
        mAnimator.setDuration(600);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isAnimated=true;
                float value=(float)animation.getAnimatedValue();
                mAnimation_value=value;
                requestLayout();
            }
        });
    }

    private void createShortItems() {
        if (labelSet.isEmpty() || imgSet.isEmpty()) {
            return;
        }
        // initiate all the shortCutItems
        item_num = Math.min(imgSet.size(), labelSet.size());
        dataSet = new ArrayList<>(item_num);
        for (int i = 0; i < item_num; i++) {
            ShortcutItem mItem = new ShortcutItem(context);
            mItem.setImgAndLabelRes(imgSet.get(i),labelSet.get(i));
            dataSet.add(mItem);
        }
        if(!dataSet.isEmpty()){
            inflateView();
        }
    }

    private void inflateView(){
        for(ShortcutItem mItem:dataSet){
            addView(mItem);
        }
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width = dataSet.get(0).getMeasuredWidth();
        int height = dataSet.get(0).getMeasuredHeight();
        if(isAnimated){
            for(int i=0;i<item_num;i++){
                int left=(int)(padding+width*i*mAnimation_value);
                int right=(int)(padding+width*i*mAnimation_value+width);

                dataSet.get(i).layout(left,padding,right,padding+height);
            }
        }else {
            for (ShortcutItem shortcutItem : dataSet) {
                shortcutItem.layout(padding, padding, padding + width, padding + height);
            }
        }
    }

    //trigger the animation
    public void startAnim(){
        mAnimator.start();
    }


}

