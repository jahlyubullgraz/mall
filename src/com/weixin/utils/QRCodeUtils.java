package com.weixin.utils;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtils {
	public static void main(String []args)throws Exception{   
		String text = "http://www.baidu.com";   
		int width = 140;   
		int height = 140;   
		String format = "png";
		String filePath = "C:\\new.png";
//		Hashtable hints= new Hashtable();   
//		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
//		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
//		File outputFile = new File("C:\\new.png");   
//		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

		generateQRCode(text, width, height, format, filePath);
    }   
	
	public static void generateQRCode(String text, int width, int height, 
			String format, String filePath) throws Exception {
		Hashtable hints= new Hashtable();   
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
		File outputFile = new File(filePath);   
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	}
}
