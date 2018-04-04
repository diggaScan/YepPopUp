package com.peitaoye.popup;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by PeitaoYe on 2018/4/3.
 */
public class ShortcutItem extends LinearLayout {


    ImageView imageView;

    TextView textView;
    Context context;


    private float width;
    private float height;
    public ShortcutItem(Context context) {
        this(context, null);
    }

    public ShortcutItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShortcutItem(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.context = context;
        init();
    }

    //    public void setDataSet(List<View> viewSet,List<String> nameSet){
//        this.viewSet=viewSet;
//        this.nameSet=nameSet;
//    }
    //initiate button and TextView
    public void setImgAndLabelRes(int img_res,String label) {
        imageView.setImageResource(img_res);
        textView.setText(label);
        addView(imageView);
        addView(textView);
        width= imageView.getMeasuredWidth();
        height= imageView.getMeasuredHeight();

    }


    private void init() {
        //initiate layout params of Shortcut Item.
        setWillNotDraw(false);
        setOrientation(VERTICAL);
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);

        //inflate the imageView and TextView
        imageView = new ImageView(context);
        LayoutParams img_lp = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img_lp.gravity= Gravity.CENTER_HORIZONTAL;
        img_lp.setMargins(8,18,8,8);
        if(Build.VERSION.SDK_INT>21){
            imageView.setElevation(2.0f);
        }
        imageView.setLayoutParams(img_lp);
        //set up listener
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        textView=new TextView(context);
        LayoutParams txt_lp=new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt_lp.gravity= Gravity.CENTER_HORIZONTAL;
        textView.setLayoutParams(txt_lp);
    }


}