package com.frogobox.ui.loadingindicator.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.frogobox.ui.loadingindicator.Indicator;

import java.util.ArrayList;

/**
 * Created by Jack on 2015/10/16.
 */
public class BallClipRotatePulseIndicator extends Indicator {

    float scaleFloat1, scaleFloat2, degrees;


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 12;
        float x = getWidth() / 2;
        float y = getHeight() / 2;

        //draw fill circle
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scaleFloat1, scaleFloat1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, x / 2.5f, paint);

        canvas.restore();

        canvas.translate(x, y);
        canvas.scale(scaleFloat2, scaleFloat2);
        canvas.rotate(degrees);

        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        //draw two arc
        float[] startAngles = new float[]{225, 45};
        for (int i = 0; i < 2; i++) {
            RectF rectF = new RectF(-x + circleSpacing, -y + circleSpacing, x - circleSpacing, y - circleSpacing);
            canvas.drawArc(rectF, startAngles[i], 90, false, paint);
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        addUpdateListener(scaleAnim, animation -> {
            scaleFloat1 = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator scaleAnim2 = ValueAnimator.ofFloat(1, 0.6f, 1);
        scaleAnim2.setDuration(1000);
        scaleAnim2.setRepeatCount(-1);
        addUpdateListener(scaleAnim2, animation -> {
            scaleFloat2 = (float) animation.getAnimatedValue();
            postInvalidate();
        });

        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        addUpdateListener(rotateAnim, animation -> {
            degrees = (float) animation.getAnimatedValue();
            postInvalidate();
        });
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        animators.add(scaleAnim);
        animators.add(scaleAnim2);
        animators.add(rotateAnim);
        return animators;
    }

}