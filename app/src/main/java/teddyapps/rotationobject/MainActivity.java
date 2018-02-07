package teddyapps.rotationobject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity implements GestureDetector.OnGestureListener {
    GestureDetector gestureDetector;
    private int total_rotation=0;
    private int degrees=45;
    private int distance=50;
    private ProgressBar circularProgressbar;

    private ImageView imageView;
    private RelativeLayout relmain;
    private TextView text;
    private boolean mirror=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);
        relmain=(RelativeLayout)findViewById(R.id.relmain);
        circularProgressbar=(ProgressBar)findViewById(R.id.circularProgressbar);
        imageView = (ImageView) findViewById(R.id.imageView1);
        text=(TextView)findViewById(R.id.text1);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if(motionEvent1.getY() - motionEvent2.getY() > distance){
            applyRotation(0, 135,imageView , 1, 1, 1000);
            Toast.makeText(MainActivity.this , " Swipe Up " , Toast.LENGTH_SHORT).show();
            return true;
        }

        if(motionEvent2.getY() - motionEvent1.getY() > distance){
            applyRotation(0, -135,imageView , 1, 1, 1000);
            Toast.makeText(MainActivity.this , " Swipe Down " , Toast.LENGTH_SHORT).show();
            return true;
        }

        if(motionEvent1.getX() - motionEvent2.getX() > distance){
            total_rotation=total_rotation-degrees;
            if (total_rotation<-360){
                total_rotation=360+total_rotation;
            }

            rotateAnimation(imageView,-degrees);
            circularProgressbar.setProgress(total_rotation);
            text.setText(total_rotation + "°");
            Toast.makeText(MainActivity.this , " Swipe Left " , Toast.LENGTH_SHORT).show();
            return true;
        }

        if(motionEvent2.getX() - motionEvent1.getX() > distance) {
            total_rotation=total_rotation+degrees;
            if (total_rotation>360){
                total_rotation=total_rotation-360;
            }
            rotateAnimation(imageView,degrees);
            circularProgressbar.setProgress(total_rotation);
            text.setText(total_rotation + "°");
            Toast.makeText(MainActivity.this, " Swipe Right ", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {

            return true ;
        }
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // TODO Auto-generated method stub

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;
    }
    private void rotateAnimation(ImageView view,int degress){

        view.animate()
                .rotationBy(degress)
                .setDuration(1000)
                .start();
    }

    private void applyRotation(float start, float end, final View view, final int a, final int b, int dur) {
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;
        final Flip3dAnimation rotation = new Flip3dAnimation(start, end, centerX, centerY);
        rotation.setDuration(1100);
        rotation.setInterpolator(new AccelerateInterpolator());

        rotation.setAnimationListener(new Animation.AnimationListener() {

                                          @Override
                                          public void onAnimationStart(Animation animation) {
                                          }

                                          @Override
                                          public void onAnimationRepeat(Animation animation) {
                                          }

                                          @Override
                                          public void onAnimationEnd(Animation animation) {
                                          }
                                      }
        );
        view.startAnimation(rotation);
    }
}