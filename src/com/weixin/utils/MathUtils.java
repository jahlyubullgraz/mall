package com.weixin.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MathUtils {
	
	public static void main(String []args)throws Exception{
		int size = 10000;
		BigDecimal[] newArgs = new BigDecimal[size];
//		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for (int i = 0; i < size; i++) {
			Double result = generateRandom();
			BigDecimal newResult = new BigDecimal(result);
//			list.add(newResult);
			newArgs[i] = newResult;
		}
		// 按照大小进行排序
		List<BigDecimal> newList = sort(newArgs);
		BigDecimal element = null;
		for (int i = 0;i < newList.size(); i++) {
			element = newList.get(i);
			System.out.print(element.setScale(2, BigDecimal.ROUND_HALF_UP) + " ");
			if (i%20 == 0) {// 遇到10的倍数就换行
				System.out.println();
			}
		}
    }   
	
	// 生成随机数
	public static Double generateRandom() throws Exception {
		Double result = Math.random()*199 + 1;
		return result;
	}
	
	// 冒泡排序
	public static List<BigDecimal> sort(BigDecimal[] args) {
		List<BigDecimal> newResult = new ArrayList<BigDecimal>();
		for(int i = 0 ; i < args.length-1 ; i++){
		   for(int j = i+1 ; j < args.length; j++){
			   BigDecimal temp ;
			   if(args[i].compareTo(args[j]) > 0){
				   temp = args[j];
				   args[j] = args[i];
				   args[i] = temp;
			   }
		   }
		}
		for (int i = 0; i < args.length; i++) {
			newResult.add(args[i]);
		}
		return newResult;
	}
	
	// 生成分数部分（直接采用随机数生成）
	public static BigDecimal generateRadix() {
		Double result = Math.random();
		return new BigDecimal(result);
	}
	
	// 生成整数部分，需要根据不同峰值进行变动（分为吞币和吐币的情况）
	public static BigDecimal generateInt(int startInt, int lastInt) {
		Double result = Math.random() * lastInt + startInt;
		return new BigDecimal(result);
	}
	
}
