package com.example.selectpoints;

import androidx.lifecycle.ViewModel;

public class PointViewModel extends ViewModel{


    private Point point1 , point2 , point3 , point4;
    private Point pointLasted1 , pointLasted2 , pointLasted3 , pointLasted4;


    public PointViewModel() {

        point1 = new Point(0f,0f);
        point2 = new Point(0f,0f);
        point3 = new Point(0f,0f);
        point4 = new Point(0f,0f);
    }


    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    public Point getPoint4() {
        return point4;
    }

    public void setPoint4(Point point4) {
        this.point4 = point4;
    }


    public Point getPointLasted1() {
        return pointLasted1;
    }

    public void setPointLasted1(Point pointLasted1) {
        this.pointLasted1 = pointLasted1;
    }

    public Point getPointLasted2() {
        return pointLasted2;
    }

    public void setPointLasted2(Point pointLasted2) {
        this.pointLasted2 = pointLasted2;
    }

    public Point getPointLasted3() {
        return pointLasted3;
    }

    public void setPointLasted3(Point pointLasted3) {
        this.pointLasted3 = pointLasted3;
    }

    public Point getPointLasted4() {
        return pointLasted4;
    }

    public void setPointLasted4(Point pointLasted4) {
        this.pointLasted4 = pointLasted4;
    }
}
