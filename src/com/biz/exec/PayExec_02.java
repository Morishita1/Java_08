package com.biz.exec;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.exec.model.PayMentVO;

public class PayExec_02 {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		System.out.print("급여금액>>");
		String strPay=scan.nextLine();
		int intPay=Integer.valueOf(strPay);
		int intMyPay=intPay;
		
		List<PayMentVO> paperList=new ArrayList<PayMentVO>();
		
		int intMonedy= 50000;
		int sw=1;
		while(intMonedy>5) {
			PayMentVO payVO=new PayMentVO();
			int intPaper=(int)(intPay/intMonedy); // 액면가에 대한 화폐매수 계산
			
			// setter를 사용하지만 변수를 public 선언했기 때문에
			// 직접 대입이 가능하다
			payVO.intMoney=intMonedy;
			payVO.paper=intPaper;
			paperList.add(payVO);
			
			// 액면가 만큼 금액을 제외한 급여금액 생성
			intPay -=(intMonedy*intPaper);
			
			//if(sw>0) intMonedy=intMonedy/5;
			//else intMonedy=intMonedy/2;
			if(sw>0)intMonedy/=5;
			else intMonedy/=2;
			
			//sw=sw*(-1);
			sw*=(-1);
		}
		
		// 총 급여액을 다시 계산하는 방법
		int intTotalPay=0;
		for(PayMentVO vo: paperList) {
			intTotalPay+=(vo.intMoney*vo.paper);
		}
		
		String payReport="src/com/biz/exec/급여명세서.txt";
		try {
			FileWriter fileWriter=new FileWriter(payReport);
			PrintWriter printWriter=new PrintWriter(fileWriter);
			
			printWriter.println("=========================================");
			printWriter.println("총급여액 : "+intTotalPay);
			printWriter.println("-----------------------------------------");
			printWriter.println("액면가\t\t매수");
			printWriter.println("-----------------------------------------");
			for(PayMentVO vo:paperList) {
				printWriter.printf("%7d\t\t%d3\n",vo.intMoney,vo.paper);
			}
			printWriter.println("=========================================");
			
			printWriter.flush();
			printWriter.close();
			
			System.out.println("급여명세서 파일 저장완료!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
