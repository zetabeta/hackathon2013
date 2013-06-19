package ch.checkbit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

    ValueAnimator bounceAnim = null;

    public MyAnimationView(Context context) {
        super(context);
    }

    private void createAnimation() {
        if (bounceAnim == null) {
            bounceAnim = ObjectAnimator.ofFloat(this, "x", this.getX(), getWidth() - 50f).setDuration(1500);
            bounceAnim.setInterpolator(new AccelerateInterpolator(2f));
            bounceAnim.addUpdateListener(this);
        }
    }

    public void startAnimation() {
        createAnimation();
        bounceAnim.start();
    }

    public void reverseAnimation() {
        createAnimation();
        bounceAnim.reverse();
    }

    public void seek(long seekTime) {
        createAnimation();
        bounceAnim.setCurrentPlayTime(seekTime);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(this.getX(), this.getY());
        this.draw(canvas);
        canvas.restore();
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
    }

}
