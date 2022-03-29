/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.frogobox.sdk.widget.shimmer;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public final class FrogoShimmerDrawable extends Drawable {
    private final ValueAnimator.AnimatorUpdateListener mUpdateListener =
            new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    invalidateSelf();
                }
            };

    private final Paint mShimmerPaint = new Paint();
    private final Rect mDrawRect = new Rect();
    private final Matrix mShaderMatrix = new Matrix();

    private @Nullable
    ValueAnimator mValueAnimator;

    private @Nullable
    FrogoShimmer mFrogoShimmer;

    public FrogoShimmerDrawable() {
        mShimmerPaint.setAntiAlias(true);
    }

    public void setShimmer(@Nullable FrogoShimmer frogoShimmer) {
        mFrogoShimmer = frogoShimmer;
        if (mFrogoShimmer != null) {
            mShimmerPaint.setXfermode(
                    new PorterDuffXfermode(
                            mFrogoShimmer.alphaShimmer ? PorterDuff.Mode.DST_IN : PorterDuff.Mode.SRC_IN));
        }
        updateShader();
        updateValueAnimator();
        invalidateSelf();
    }

    public @Nullable
    FrogoShimmer getShimmer() {
        return mFrogoShimmer;
    }

    /**
     * Starts the shimmer animation.
     */
    public void startShimmer() {
        if (mValueAnimator != null && !isShimmerStarted() && getCallback() != null) {
            mValueAnimator.start();
        }
    }

    /**
     * Stops the shimmer animation.
     */
    public void stopShimmer() {
        if (mValueAnimator != null && isShimmerStarted()) {
            mValueAnimator.cancel();
        }
    }

    /**
     * Return whether the shimmer animation has been started.
     */
    public boolean isShimmerStarted() {
        return mValueAnimator != null && mValueAnimator.isStarted();
    }

    @Override
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mDrawRect.set(bounds);
        updateShader();
        maybeStartShimmer();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mFrogoShimmer == null || mShimmerPaint.getShader() == null) {
            return;
        }

        final float tiltTan = (float) Math.tan(Math.toRadians(mFrogoShimmer.tilt));
        final float translateHeight = mDrawRect.height() + tiltTan * mDrawRect.width();
        final float translateWidth = mDrawRect.width() + tiltTan * mDrawRect.height();
        final float dx;
        final float dy;
        final float animatedValue =
                mValueAnimator != null ? (float) mValueAnimator.getAnimatedValue() : 0f;
        switch (mFrogoShimmer.direction) {
            default:
            case FrogoShimmer.Direction.LEFT_TO_RIGHT:
                dx = offset(-translateWidth, translateWidth, animatedValue);
                dy = 0;
                break;
            case FrogoShimmer.Direction.RIGHT_TO_LEFT:
                dx = offset(translateWidth, -translateWidth, animatedValue);
                dy = 0f;
                break;
            case FrogoShimmer.Direction.TOP_TO_BOTTOM:
                dx = 0f;
                dy = offset(-translateHeight, translateHeight, animatedValue);
                break;
            case FrogoShimmer.Direction.BOTTOM_TO_TOP:
                dx = 0f;
                dy = offset(translateHeight, -translateHeight, animatedValue);
                break;
        }

        mShaderMatrix.reset();
        mShaderMatrix.setRotate(mFrogoShimmer.tilt, mDrawRect.width() / 2f, mDrawRect.height() / 2f);
        mShaderMatrix.postTranslate(dx, dy);
        mShimmerPaint.getShader().setLocalMatrix(mShaderMatrix);
        canvas.drawRect(mDrawRect, mShimmerPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        // No-op, modify the Shimmer object you pass in instead
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        // No-op, modify the Shimmer object you pass in instead
    }

    @Override
    public int getOpacity() {
        return mFrogoShimmer != null && (mFrogoShimmer.clipToChildren || mFrogoShimmer.alphaShimmer)
                ? PixelFormat.TRANSLUCENT
                : PixelFormat.OPAQUE;
    }

    private float offset(float start, float end, float percent) {
        return start + (end - start) * percent;
    }

    private void updateValueAnimator() {
        if (mFrogoShimmer == null) {
            return;
        }

        final boolean started;
        if (mValueAnimator != null) {
            started = mValueAnimator.isStarted();
            mValueAnimator.cancel();
            mValueAnimator.removeAllUpdateListeners();
        } else {
            started = false;
        }

        mValueAnimator =
                ValueAnimator.ofFloat(0f, 1f + (float) (mFrogoShimmer.repeatDelay / mFrogoShimmer.animationDuration));
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatMode(mFrogoShimmer.repeatMode);
        mValueAnimator.setStartDelay(mFrogoShimmer.startDelay);
        mValueAnimator.setRepeatCount(mFrogoShimmer.repeatCount);
        mValueAnimator.setDuration(mFrogoShimmer.animationDuration + mFrogoShimmer.repeatDelay);
        mValueAnimator.addUpdateListener(mUpdateListener);
        if (started) {
            mValueAnimator.start();
        }
    }

    void maybeStartShimmer() {
        if (mValueAnimator != null
                && !mValueAnimator.isStarted()
                && mFrogoShimmer != null
                && mFrogoShimmer.autoStart
                && getCallback() != null) {
            mValueAnimator.start();
        }
    }

    private void updateShader() {
        final Rect bounds = getBounds();
        final int boundsWidth = bounds.width();
        final int boundsHeight = bounds.height();
        if (boundsWidth == 0 || boundsHeight == 0 || mFrogoShimmer == null) {
            return;
        }
        final int width = mFrogoShimmer.width(boundsWidth);
        final int height = mFrogoShimmer.height(boundsHeight);

        final Shader shader;
        switch (mFrogoShimmer.shape) {
            default:
            case FrogoShimmer.Shape.LINEAR:
                boolean vertical =
                        mFrogoShimmer.direction == FrogoShimmer.Direction.TOP_TO_BOTTOM
                                || mFrogoShimmer.direction == FrogoShimmer.Direction.BOTTOM_TO_TOP;
                int endX = vertical ? 0 : width;
                int endY = vertical ? height : 0;
                shader = new LinearGradient(
                        0, 0, endX, endY, mFrogoShimmer.colors, mFrogoShimmer.positions, Shader.TileMode.CLAMP);
                break;
            case FrogoShimmer.Shape.RADIAL:
                shader = new RadialGradient(
                        width / 2f,
                        height / 2f,
                        (float) (Math.max(width, height) / Math.sqrt(2)),
                        mFrogoShimmer.colors,
                        mFrogoShimmer.positions,
                        Shader.TileMode.CLAMP);
                break;
        }

        mShimmerPaint.setShader(shader);
    }
}
