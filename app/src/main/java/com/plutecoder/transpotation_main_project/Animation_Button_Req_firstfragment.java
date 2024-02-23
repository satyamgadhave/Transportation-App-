
package com.plutecoder.transpotation_main_project;

import android.content.Context;
        import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import com.airbnb.lottie.LottieAnimationView;

public class Animation_Button_Req_firstfragment extends AppCompatButton {

    private LottieAnimationView animationView;

    public Animation_Button_Req_firstfragment(Context context) {
        super(context);
        init(context);
    }

    public Animation_Button_Req_firstfragment(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Animation_Button_Req_firstfragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        animationView = new LottieAnimationView(context);
        animationView.setAnimation(R.raw.sign_up_truck);
        // Customize animation view properties if needed
        animationView.setVisibility(GONE);
        this.addView(animationView);
    }

    private void addView(LottieAnimationView animationView) {
    }

    public void playAnimation() {
        animationView.setVisibility(VISIBLE);
        animationView.playAnimation();
        // You can listen for animation end and hide the animation view if needed.
    }
}
