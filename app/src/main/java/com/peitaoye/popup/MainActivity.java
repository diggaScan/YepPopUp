package com.peitaoye.popup;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Integer> imgs;
    List<String> name;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgs = new ArrayList<>(3);
        name = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            imgs.add(R.drawable.badge);
            name.add("hello");
        }

        button = ((Button) findViewById(R.id.hello));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpWindow();
            }
        });
    }

    public void showPopUpWindow(){
        View view=getLayoutInflater().inflate(R.layout.popup_layout,null,false);
        ShortcutLayout shortcutLayout=(ShortcutLayout)view.findViewById(R.id.layout);
        shortcutLayout.setImgAndLabelSet(imgs,name);


        PopupWindow popupWindow=new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=1f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
        PopupWindowCompat.showAsDropDown(popupWindow,button,100,-100, Gravity.CENTER);
        shortcutLayout.startAnim();
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

}

