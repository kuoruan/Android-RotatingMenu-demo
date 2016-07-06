package com.kuoruan.rotatingmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.kuoruan.rotatingmenu.util.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;

    boolean isLevel1Display = true;
    boolean isLevel2Display = true;
    boolean isLevel3Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_menu).setOnClickListener(this);

        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }

    @Override
    public void onClick(View v) {
        if (AnimationUtils.currentAnimationCount > 0) { // 如果当前有动画正在执行
            return;
        }
        switch (v.getId()) {
            case R.id.ib_home:
                if (isLevel2Display) {
                    long delay = 0;
                    if (isLevel3Display) { // 如果 Level3 正显示，先转出去
                        AnimationUtils.rotateOut(rl_level3, delay);
                        isLevel3Display = false;
                        delay += 200;
                    }
                    AnimationUtils.rotateOut(rl_level2, delay);
                } else {
                    AnimationUtils.rotateIn(rl_level2, 0);
                }

                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:
                if (isLevel3Display) {
                    AnimationUtils.rotateOut(rl_level3, 0);
                } else {
                    AnimationUtils.rotateIn(rl_level3, 0);
                }
                isLevel3Display = !isLevel3Display;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (AnimationUtils.currentAnimationCount > 0) {
                // 当前有动画正在执行, 取消当前事件
                return true;
            }
            if (isLevel1Display) {
                long delay = 0;
                if (isLevel3Display) { // Level3 正显示
                    AnimationUtils.rotateOut(rl_level3, delay);
                    isLevel3Display = false;
                    delay += 200;
                }

                if (isLevel2Display) { // Level2 正显示
                    AnimationUtils.rotateOut(rl_level2, delay);
                    isLevel2Display = false;
                    delay += 200;
                }

                AnimationUtils.rotateOut(rl_level1, delay);
            } else {
                AnimationUtils.rotateIn(rl_level1, 0);
                AnimationUtils.rotateIn(rl_level2, 200);
                AnimationUtils.rotateIn(rl_level3, 400);

                isLevel2Display = true;
                isLevel3Display = true;
            }

            isLevel1Display = !isLevel1Display;
            return true; // 消费事件
        }

        return super.onKeyDown(keyCode, event);
    }
}
