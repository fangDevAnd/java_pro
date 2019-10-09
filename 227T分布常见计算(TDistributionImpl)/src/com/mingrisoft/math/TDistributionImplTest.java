package com.mingrisoft.math;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistributionImpl;

public class TDistributionImplTest {
    public static void main(String[] args) throws MathException {
        TDistributionImpl t = new TDistributionImpl(5);// 新建一个自由度为5的T分布
        System.out.println("当前T分布的自由度：" + t.getDegreesOfFreedom());
        double upperTail = t.cumulativeProbability(0.7267);
        System.out.println("计算域大于0.7267的置信度：" + upperTail);
        System.out.println("计算0点的概率密度：" + t.density(0));
        double domain = t.inverseCumulativeProbability(0.75);
        System.out.println("计算置信度大于0.75的域值：" + domain);
    }
}
