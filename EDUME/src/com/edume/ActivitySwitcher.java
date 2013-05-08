package com.edume;

import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;


public class ActivitySwitcher {


	private final static int DURATION = 2500;
	private final static float DEPTH = 3500.0f;
 
	/* ----------------------------------------------- */
 
	public interface AnimationFinishedListener {
		/**
		 * Called when the animation is finished.
		 */
		public void onAnimationFinished();
	}
 
	/* ----------------------------------------------- */
 
	public static void animationIn(View container, WindowManager windowManager) {
		animationIn(container, windowManager, null);
	}
 
	public static void animationIn(View container, WindowManager windowManager, AnimationFinishedListener listener) {
		apply3DRotation(180, 0, false, container, windowManager, listener);
	}
 
	public static void animationOut(View container, WindowManager windowManager) {
		animationOut(container, windowManager, null);
	}
 
	public static void animationOut(View container, WindowManager windowManager, AnimationFinishedListener listener) {
		apply3DRotation(0, -180, true, container, windowManager, listener);
	}
 
	/* ----------------------------------------------- */
 
	private static void apply3DRotation(float fromDegree, float toDegree, boolean reverse, View container, WindowManager windowManager, final AnimationFinishedListener listener) {
		Display display = windowManager.getDefaultDisplay();
		final float centerX = display.getWidth() / 2.0f;
		final float centerY = display.getHeight() / 2.0f;
 
		final Rotate3d a = new Rotate3d(fromDegree, toDegree, centerX, centerY, DEPTH, reverse);
		a.reset();
		a.setDuration(DURATION);
		a.setFillAfter(true);
		a.setInterpolator(new AccelerateInterpolator());
		if (listener != null) {
			a.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
				}
 
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
 
				@Override
				public void onAnimationEnd(Animation animation) {
					listener.onAnimationFinished();
				}
			});
		}
		container.clearAnimation();
		container.startAnimation(a);
	}
}