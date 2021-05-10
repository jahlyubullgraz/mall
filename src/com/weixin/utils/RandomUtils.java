package com.weixin.utils;

import java.util.Random;

/**
 * 随机数生成工具类
 */
public class RandomUtils {
	
//	Random rand = new Random();
	
	public static void main (String args[]) {
		double bytP = 0.01;
		double sngBegin = 1;
		double sngEnd = 5;
		double sngPB = 2;
		double sngPE = 3;
//        for (int i = 0; i < 1000; i++) {
//        	System.out.println(GetRndNumP(sngBegin, sngEnd, sngPB, sngPE, bytP));
//        }
//		for (int i = 0; i < 1000; i++) {
//			double test = (20 - 5) * Math.random() + 5;
//			System.out.println(test);
//		}
		int k = 0;
		int g = 0;
		int l = 0;
		for (int i = 0; i < 100000; i++) {
			int j = (int) (Math.random() * 20);
			System.out.println("j : " + j);
			if (j==0 || j==1 || j==2 || j==3 || j==4 || j==5 || j== 6) {// 70%概率
				System.out.println(GetRandomNum(5, 20));
				k++;
			} else if (j == 7 || j ==8) {// 20%概率
				System.out.println(GetRandomNum(21, 60));
				g++;
			} else if (j == 9) {// 10%的概率
				System.out.println(GetRandomNum(61, 200));
				l++;
			}
		}
		System.out.println("获得数据的次数为 ： " + k);
		System.out.println("获得数据的次数为 ： " + g);
		System.out.println("获得数据的次数为 ： " + l);
	}
	
    /**
     * 按照一定概率进行随机<br>
     * <br>
     * @param pSngBegin 随机数范围的开始数字
     * @param pSngEnd 随机数范围结束数字
     * @param pSngPB 要随机的数字的开始数字
     * @param pSngPE 要随机的数字的结束数字
     * @param pBytP 要随机的数字随机概率
     * @return 按照一定概率随机的数字
     */
	public static double GetRndNumP(double pSngBegin, 
			                 double pSngEnd, 
			                 double pSngPB, 
			                 double pSngPE, 
			                 double pBytP) {
		double sngPLen;
		double sngTLen; //total length
		double sngIncreased; //需要缩放的长度
		double sngResult;
		sngPLen = pSngPE - pSngPB;
	    sngTLen = pSngEnd - pSngBegin;
	    if ((sngPLen / sngTLen) * 100 == pBytP ) {
	    	return GetRandomNum(pSngBegin, pSngEnd);
	    } else {
	    	// ((sngPLen + sngIncreased) / (sngTLen + sngIncreased)) * 100 = bytP
	    	sngIncreased = ((pBytP / 100) * sngTLen - sngPLen) / (1 - (pBytP / 100));
	    	// 缩放回原来区间
	        sngResult = GetRandomNum(pSngBegin, pSngEnd + sngIncreased);
	    	if (pSngBegin <= sngResult && sngResult <= pSngPB) {
	    		return sngResult;
	    	} else if (pSngPB <= sngResult && sngResult <= (pSngPE + sngIncreased)) {
	    		return pSngPB + (sngResult - pSngPB) * sngPLen / (sngPLen + sngIncreased);
	    	} else if ((pSngPE + sngIncreased) <= sngResult && sngResult <= (pSngEnd + sngIncreased )) {
	    		return sngResult - sngIncreased;
	    	}
	    }
	    return 0f;
	}
	
	/**
	 * 生成一段区间的随机数
	 * @param pSngBegin 开始数字
	 * @param pSngEnd 结束数字
	 * @return
	 */
	public static double GetRandomNum(double pSngBegin, double pSngEnd) {
		return (pSngEnd - pSngBegin) * Math.random() + pSngBegin;
	}
	
	
	
}
