package com.example.selectpoints;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String changeCatType(String type) {
        switch (type.toUpperCase()) {
            case "DOOR":
                return "Door";
            case "WINDOW":
                return "Window";
            case "WALL":
                return "Wall";
            case "ROOF":
                return "Roof";
            case "OTHER":
                return "Other";

        }
        return "Door";
    }


    //Converts device pixels to regular pixels to draw
    public static float dpToPixel(float dp, Activity activity) {
        return 24f;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void implementSpringAnimationTrait(View view) {


       /* SpringAnimation scaleXAnim = new SpringAnimation(view, DynamicAnimation.SCALE_X, 0.90f);
        SpringAnimation scaleYAnim = new SpringAnimation(view, DynamicAnimation.SCALE_Y, 0.90f);

        view.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    scaleXAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                    scaleXAnim.getSpring().setStiffness(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                    scaleXAnim.start();

                    scaleYAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                    scaleYAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                    scaleYAnim.start();

                    break;
                case MotionEvent.ACTION_UP &
                        MotionEvent.ACTION_CANCEL:

                    scaleXAnim.cancel();
                    scaleYAnim.cancel();

                    SpringAnimation reverseScaleXAnim = new SpringAnimation(this, DynamicAnimation.SCALE_X, 1f);
                    reverseScaleXAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                    reverseScaleXAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                    reverseScaleXAnim.start();

                    SpringAnimation reverseScaleYAnim = new SpringAnimation(this, DynamicAnimation.SCALE_Y, 1f);
                    reverseScaleYAnim.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
                    reverseScaleYAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
                    reverseScaleYAnim.start();

                    break;
            }

            return false;
        });
*/
    }

    public static List<Point> setStandardPoints(View imageSizeView , float imageSizeW , float imageSizeH , List<Point> pointList){


        float Ax = imageSizeView.getMeasuredWidth() / imageSizeW;
        float Ay = imageSizeView.getMeasuredHeight() / imageSizeH;

        List<Point> standardPoints = new ArrayList<>();
        standardPoints.add(new Point(Ax * pointList.get(0).getX() , Ay * pointList.get(0).getY()));
        standardPoints.add(new Point(Ax * pointList.get(1).getX() , Ay * pointList.get(1).getY()));
        standardPoints.add(new Point(Ax * pointList.get(2).getX() , Ay * pointList.get(2).getY()));
        standardPoints.add(new Point(Ax * pointList.get(3).getX() , Ay * pointList.get(3).getY()));

        return standardPoints;
    }

    public static List<Point> reversePoints(View imageSizeView , View imageSize , List<Point> pointList){

        float Ax = imageSizeView.getMeasuredWidth() / imageSize.getMeasuredWidth();
        float Ay = imageSizeView.getMeasuredHeight() / imageSize.getMeasuredHeight();

        List<Point> standardPoints = new ArrayList<>();
        standardPoints.add(new Point(Ax / pointList.get(0).getX() , Ay / pointList.get(0).getY()));
        standardPoints.add(new Point(Ax / pointList.get(1).getX() , Ay / pointList.get(1).getY()));
        standardPoints.add(new Point(Ax / pointList.get(2).getX() , Ay / pointList.get(2).getY()));
        standardPoints.add(new Point(Ax / pointList.get(3).getX() , Ay / pointList.get(3).getY()));

        return standardPoints;
    }





}
