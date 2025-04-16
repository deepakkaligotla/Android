package in.kaligotla.day04;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import in.kaligotla.day04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        activityMainBinding.txtView.startAnimation(animation);

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator);
        animator.setTarget(activityMainBinding.txtView);
        animator.start();

        // Detect and handle the custom <animator> block (ValueAnimator)
        if (animator instanceof AnimatorSet) {
            for (Animator child : ((AnimatorSet) animator).getChildAnimations()) {
                if (child instanceof ValueAnimator && !(child instanceof ObjectAnimator)) {
                    ((ValueAnimator) child).addUpdateListener(valueAnimator -> {
                        int animatedSize = (int) valueAnimator.getAnimatedValue();
                        activityMainBinding.txtView.setTextSize(animatedSize);
                    });
                }
            }
        }

        Log.e("Color Resource","Color Resource "+ getResources().getColor(R.color.light_purple));
        Log.e("Dimensions Resource","Dimensions Resource "+ getResources().getDimension(R.dimen.height));
        Log.e("String Resource","String Resource "+ getResources().getString(R.string.course_name));
        Log.e("Integer Resource","Integer Resource "+ getResources().getInteger(R.integer.one));
        for(String language: getResources().getStringArray(R.array.languages)) {
            Log.e("StringArray Resource","StringArray Resource "+ language);
        }
        for(int number: getResources().getIntArray(R.array.numbers)) {
            Log.e("IntegerArray Resource","IntegerArray Resource "+ number);
        }
        for(int i=0; i < getResources().obtainTypedArray(R.array.colors).length(); i++) {
            Log.e("HeterogenoeusTypedArray Value","HeterogenoeusTypedArray "+getResources().obtainTypedArray(R.array.colors).getText(i));
        }
    }
    //xml - extensible markup language
}