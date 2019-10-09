package com.mingrisoft.math;

import java.util.Arrays;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class RealMatrixTest {
    public static void main(String[] args) {
        double[][] matrixData1 = { { 1, 2 }, { 3, 4 } };
        RealMatrix m = new Array2DRowRealMatrix(matrixData1);// 利用二维数组初始化矩阵
        System.out.println("矩阵m中的元素：");
        System.out.println(Arrays.deepToString(m.getData()));// 利用工具类输出矩阵中元素
        double[][] matrixData2 = { { 1, 2 }, { 3, 4 } };
        RealMatrix n = new Array2DRowRealMatrix(matrixData2);// 利用二维数组初始化矩阵
        System.out.println("矩阵n中的元素：");
        System.out.println(Arrays.deepToString(n.getData()));// 利用工具类输出矩阵中元素
        RealMatrix addition = m.add(n);// 进行矩阵加法运算
        System.out.println("矩阵addition中的元素：");
        System.out.println(Arrays.deepToString(addition.getData()));
        RealMatrix subtraction = m.subtract(n);// 进行矩阵减法运算
        System.out.println("矩阵subtraction中的元素：");
        System.out.println(Arrays.deepToString(subtraction.getData()));
        RealMatrix multiplication = m.multiply(n);// 进行矩阵乘法运算
        System.out.println("矩阵multiplication中的元素：");
        System.out.println(Arrays.deepToString(multiplication.getData()));
        RealMatrix transposition = m.multiply(n);// 进行矩阵转置运算
        System.out.println("矩阵m转置后新矩阵中的元素：");
        System.out.println(Arrays.deepToString(transposition.getData()));
    }
}
