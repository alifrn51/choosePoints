package com.example.selectpoints;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.selectpoints.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float dX;
    float dY;
    int lastAction;
    ActivityMainBinding binding;
    PointViewModel pointViewModel;
    private float sizeWith;
    private float sizeHeight;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sizeHeight = binding.frame.getMeasuredHeight();
        sizeWith = binding.frame.getMeasuredWidth();

        pointViewModel = new ViewModelProvider(this).get(PointViewModel.class);

        List<Point> pointListApi = new ArrayList<>();
        pointListApi.add(new Point(120.0f, 160.0f));
        pointListApi.add(new Point(120.0f, 480.0f));
        pointListApi.add(new Point(360.0f, 480.0f));
        pointListApi.add(new Point(360.0f, 160.0f));


        binding.imgPic.post(() -> {

            //[[120.0, 160.0], [120.0, 480.0], [360.0, 480.0], [360.0, 160.0]]
            List<Point> pointListStandard = Utils.setStandardPoints(binding.frame, 480, 640, pointListApi);
            pointViewModel.setPoint1(pointListStandard.get(0));
            pointViewModel.setPoint2(pointListStandard.get(1));
            pointViewModel.setPoint3(pointListStandard.get(2));
            pointViewModel.setPoint4(pointListStandard.get(3));


            binding.lineRectangle1.setPointStart(pointViewModel.getPoint1());
            binding.lineRectangle1.setColorLine(getResources().getColor(R.color.line1));
            binding.lineRectangle1.setPointEnd(pointViewModel.getPoint2());
            binding.lineRectangle1.draw();

            binding.lineRectangle2.setPointStart(pointViewModel.getPoint2());
            binding.lineRectangle2.setColorLine(getResources().getColor(R.color.line2));
            binding.lineRectangle2.setPointEnd(pointViewModel.getPoint3());
            binding.lineRectangle2.draw();

            binding.lineRectangle3.setPointStart(pointViewModel.getPoint3());
            binding.lineRectangle3.setColorLine(getResources().getColor(R.color.line3));
            binding.lineRectangle3.setPointEnd(pointViewModel.getPoint4());
            binding.lineRectangle3.draw();

            binding.lineRectangle4.setPointStart(pointViewModel.getPoint4());
            binding.lineRectangle4.setColorLine(getResources().getColor(R.color.line4));
            binding.lineRectangle4.setPointEnd(pointViewModel.getPoint1());
            binding.lineRectangle4.draw();

            binding.point1.setX(pointViewModel.getPoint1().getX() - 24);
            binding.point1.setY(pointViewModel.getPoint1().getY() - 24);

            binding.point2.setX(pointViewModel.getPoint2().getX() - 24);
            binding.point2.setY(pointViewModel.getPoint2().getY() - 24);

            binding.point3.setX(pointViewModel.getPoint3().getX() - 24);
            binding.point3.setY(pointViewModel.getPoint3().getY() - 24);

            binding.point4.setX(pointViewModel.getPoint4().getX() - 24);
            binding.point4.setY(pointViewModel.getPoint4().getY() - 24);

            binding.point1.setOnTouchListener(this);
            binding.point2.setOnTouchListener(this);
            binding.point3.setOnTouchListener(this);
            binding.point4.setOnTouchListener(this);


        });


    }


    public boolean checkPointPosition1(Point pointPosition1, Point pointPosition2, Point pointPosition3, Point pointPosition4) {

        if (pointPosition1.getX() > pointPosition4.getX() ||
                pointPosition1.getX() > pointPosition3.getX()) return true;
        else return pointPosition1.getY() > pointPosition2.getY() ||
                pointPosition1.getY() > pointPosition3.getY();

    }

    public boolean checkPointPosition2(Point pointPosition1, Point pointPosition2, Point pointPosition3, Point pointPosition4) {

        if (pointPosition2.getX() > pointPosition4.getX() ||
                pointPosition2.getX() > pointPosition3.getX()) return true;
        else return pointPosition2.getY() < pointPosition1.getY() ||
                pointPosition2.getY() < pointPosition4.getY();

    }

    public boolean checkPointPosition3(Point pointPosition1, Point pointPosition2, Point pointPosition3, Point pointPosition4) {

        if (pointPosition3.getX() < pointPosition1.getX() ||
                pointPosition3.getX() < pointPosition2.getX()) return true;
        else return pointPosition3.getY() < pointPosition1.getY() ||
                pointPosition3.getY() < pointPosition4.getY();

    }

    public boolean checkPointPosition4(Point pointPosition1, Point pointPosition2, Point pointPosition3, Point pointPosition4) {

        if (pointPosition4.getX() < pointPosition1.getX() ||
                pointPosition4.getX() < pointPosition2.getX()) return true;
        else if (pointPosition4.getY() > pointPosition2.getY() ||
                pointPosition4.getY() > pointPosition3.getY()) {
            return true;
        } else return false;

    }


    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {


            case MotionEvent.ACTION_DOWN:

                Log.i(TAG, "ACTION_DOWN: View.gex: " + view.getX());
                Log.i(TAG, "ACTION_DOWN: Event.getRaw: " + event.getRawX());
                dX = (view.getX()) - event.getRawX();
                dY = (view.getY()) - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;

                pointViewModel.setPointLasted1(pointViewModel.getPoint1());
                pointViewModel.setPointLasted2(pointViewModel.getPoint2());
                pointViewModel.setPointLasted3(pointViewModel.getPoint3());
                pointViewModel.setPointLasted4(pointViewModel.getPoint4());

                break;

            case MotionEvent.ACTION_MOVE:


                view.setY((event.getRawY() + dY));
                view.setX((event.getRawX() + dX));
                Log.i(TAG, "ACTION_MOVE: View.gex: " + view.getX());
                Log.i(TAG, "ACTION_MOVE: Event.getRaw: " + event.getRawX());


                lastAction = MotionEvent.ACTION_MOVE;

                if (view.getId() == R.id.point1) {

                    //zard
                    pointViewModel.setPoint1(new Point((event.getRawX() + dX) + Utils.dpToPixel(12, this), (event.getRawY() + dY) + Utils.dpToPixel(12, this)));

                    binding.lineRectangle1.setPointStart(pointViewModel.getPoint1());
                    binding.lineRectangle1.setPointEnd(pointViewModel.getPoint2());
                    binding.lineRectangle1.draw();


                    binding.lineRectangle4.setPointStart(pointViewModel.getPoint1());
                    binding.lineRectangle4.setPointEnd(pointViewModel.getPoint4());
                    binding.lineRectangle4.draw();

                } else if (view.getId() == R.id.point2) {

                    //sabze
                    pointViewModel.setPoint2(new Point((event.getRawX() + dX) + Utils.dpToPixel(12, this), (event.getRawY() + dY) + Utils.dpToPixel(12, this)));

                    binding.lineRectangle2.setPointStart(pointViewModel.getPoint2());
                    binding.lineRectangle2.setPointEnd(pointViewModel.getPoint3());
                    binding.lineRectangle2.draw();


                    binding.lineRectangle1.setPointStart(pointViewModel.getPoint1());
                    binding.lineRectangle1.setPointEnd(pointViewModel.getPoint2());
                    binding.lineRectangle1.draw();

                } else if (view.getId() == R.id.point3) {

                    //zard
                    pointViewModel.setPoint3(new Point((event.getRawX() + dX) + Utils.dpToPixel(12, this), (event.getRawY() + dY) + Utils.dpToPixel(12, this)));

                    binding.lineRectangle3.setPointStart(pointViewModel.getPoint3());
                    binding.lineRectangle3.setPointEnd(pointViewModel.getPoint4());
                    binding.lineRectangle3.draw();


                    binding.lineRectangle2.setPointStart(pointViewModel.getPoint2());
                    binding.lineRectangle2.setPointEnd(pointViewModel.getPoint3());
                    binding.lineRectangle2.draw();


                } else if (view.getId() == R.id.point4) {

                    //narangi
                    pointViewModel.setPoint4(new Point((event.getRawX() + dX) + Utils.dpToPixel(12, this), (event.getRawY() + dY) + Utils.dpToPixel(12, this)));

                    binding.lineRectangle4.setPointStart(pointViewModel.getPoint1());
                    binding.lineRectangle4.setPointEnd(pointViewModel.getPoint4());
                    binding.lineRectangle4.draw();


                    binding.lineRectangle3.setPointStart(pointViewModel.getPoint4());
                    binding.lineRectangle3.setPointEnd(pointViewModel.getPoint3());
                    binding.lineRectangle3.draw();

                }
                break;

            case MotionEvent.ACTION_UP:


                if (view.getId() == R.id.point1) {
                    if (checkPointPosition1(pointViewModel.getPoint1(), pointViewModel.getPoint2(), pointViewModel.getPoint3(), pointViewModel.getPoint4())) {

                        pointViewModel.setPoint1(pointViewModel.getPointLasted1());
                        pointViewModel.setPoint2(pointViewModel.getPointLasted2());
                        pointViewModel.setPoint4(pointViewModel.getPointLasted4());

                        binding.lineRectangle1.setPointStart(pointViewModel.getPoint1());
                        binding.lineRectangle1.setPointEnd(pointViewModel.getPoint2());
                        binding.lineRectangle1.draw();


                        binding.lineRectangle4.setPointStart(pointViewModel.getPoint1());
                        binding.lineRectangle4.setPointEnd(pointViewModel.getPoint4());
                        binding.lineRectangle4.draw();


                        view.setY(pointViewModel.getPoint1().getY()- 24);
                        view.setX(pointViewModel.getPoint1().getX()- 24);


                    }
                } else if (view.getId() == R.id.point2) {
                    if (checkPointPosition2(pointViewModel.getPoint1(), pointViewModel.getPoint2(), pointViewModel.getPoint3(), pointViewModel.getPoint4())) {


                        pointViewModel.setPoint1(pointViewModel.getPointLasted1());
                        pointViewModel.setPoint3(pointViewModel.getPointLasted3());
                        pointViewModel.setPoint2(pointViewModel.getPointLasted2());

                        binding.lineRectangle2.setPointStart(pointViewModel.getPoint2());
                        binding.lineRectangle2.setPointEnd(pointViewModel.getPoint3());
                        binding.lineRectangle2.draw();


                        binding.lineRectangle1.setPointStart(pointViewModel.getPoint1());
                        binding.lineRectangle1.setPointEnd(pointViewModel.getPoint2());
                        binding.lineRectangle1.draw();


                        view.setY(pointViewModel.getPoint2().getY()- 24);
                        view.setX(pointViewModel.getPoint2().getX()- 24);

                    }
                } else if (view.getId() == R.id.point3) {

                    if (checkPointPosition3(pointViewModel.getPoint1(), pointViewModel.getPoint2(), pointViewModel.getPoint3(), pointViewModel.getPoint4())) {
                        //zard
                        pointViewModel.setPoint3(pointViewModel.getPointLasted3());
                        pointViewModel.setPoint2(pointViewModel.getPointLasted2());
                        pointViewModel.setPoint4(pointViewModel.getPointLasted4());

                        binding.lineRectangle3.setPointStart(pointViewModel.getPoint3());
                        binding.lineRectangle3.setPointEnd(pointViewModel.getPoint4());
                        binding.lineRectangle3.draw();


                        binding.lineRectangle2.setPointStart(pointViewModel.getPoint2());
                        binding.lineRectangle2.setPointEnd(pointViewModel.getPoint3());
                        binding.lineRectangle2.draw();

                        view.setY(pointViewModel.getPoint3().getY()- 24);
                        view.setX(pointViewModel.getPoint3().getX()- 24);
                    }

                } else if (view.getId() == R.id.point4) {

                    if (checkPointPosition4(pointViewModel.getPoint1(), pointViewModel.getPoint2(), pointViewModel.getPoint3(), pointViewModel.getPoint4())) {
                        //narangi
                        pointViewModel.setPoint4(pointViewModel.getPointLasted4());
                        pointViewModel.setPoint1(pointViewModel.getPointLasted1());
                        pointViewModel.setPoint3(pointViewModel.getPointLasted3());

                        binding.lineRectangle4.setPointStart(pointViewModel.getPoint1());
                        binding.lineRectangle4.setPointEnd(pointViewModel.getPoint4());
                        binding.lineRectangle4.draw();


                        binding.lineRectangle3.setPointStart(pointViewModel.getPoint4());
                        binding.lineRectangle3.setPointEnd(pointViewModel.getPoint3());
                        binding.lineRectangle3.draw();

                        view.setY(pointViewModel.getPoint4().getY()- 24);
                        view.setX(pointViewModel.getPoint4().getX()- 24);
                    }
                }

                if (lastAction == MotionEvent.ACTION_DOWN)
                    Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }


}