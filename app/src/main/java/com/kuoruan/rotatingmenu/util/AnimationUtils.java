package com.kuoruan.rotatingmenu.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * 各种动画
 */

public final class AnimationUtils {

    public static final long DURATION = 500;
    public static int currentAnimationCount = 0;

    private AnimationUtils() {
    }

    public static void rotateOut(View view, long delay) {
        Animation animation = new RotateAnimation(
                0f, -180f, // 开始角度，结束角度
                Animation.RELATIVE_TO_SELF, .5f, // 旋转 x 中心
                Animation.RELATIVE_TO_SELF, 1f // 选择 y 中心
        );
        animation.setDuration(DURATION);
        animation.setFillAfter(true);
        animation.setStartOffset(delay);
        animation.setAnimationListener(new OnAnimationListener());

        view.startAnimation(animation);
    }

    public static void rotateIn(View view, long delay) {
        Animation animation = new RotateAnimation(
                -180f, 0f, // 开始角度，结束角度
                Animation.RELATIVE_TO_SELF, .5f, // 旋转 x 中心
                Animation.RELATIVE_TO_SELF, 1f // 选择 y 中心
        );
        animation.setDuration(DURATION);
        animation.setFillAfter(true);
        animation.setStartOffset(delay);
        animation.setAnimationListener(new OnAnimationListener());

        view.startAnimation(animation);
    }

    /**
     * 监听当前动画执行状态
     */
    static class OnAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            currentAnimationCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            currentAnimationCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
