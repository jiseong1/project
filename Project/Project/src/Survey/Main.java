package Survey;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int su;
		String rel = null;
		int age = 0;
		int index = 0;
		InfoDao id = new InfoDao();
		
		
		while(true) {
			
			System.out.println("종교 조사 프로그램");
			System.out.println("1.정보 입력");
			System.out.println("2.정보 보기");
			System.out.println("3.프로그램 종료");
			System.out.print("입력 : ");
			try {
				su = sc.nextInt();				
			}catch(InputMismatchException e) {
				su = 0;
				sc.nextLine();
				System.out.println("잘못 입력!");
			}
			
			switch(su) {
			
			case 1: 
				List<String> ls = id.viewRel();
				for(int i=0; i<ls.size();i++) {
					System.out.println(i+1+"."+ls.get(i).toString());
				}
				System.out.println(ls.size()+1+".기타");
				System.out.print("입력 : ");	
				try {
					index = sc.nextInt();				
				}catch(InputMismatchException e) {
					index = 0;
					sc.nextLine();
					System.out.println("잘못 입력!");
					break;
				}
				if(index == ls.size()+1) {
					System.out.print("종교를 입력해 : ");
					rel = sc.next();
					if(id.checkRel(rel)) {
						System.out.println("이미있는 종교임");
						break;
					}
				}
				if(index != ls.size()+1) {
					try {
						rel = ls.get(index-1);											
					}catch(IndexOutOfBoundsException e) {
						System.out.println("잘못 입력!");
						break;
					}
				}
				
				System.out.print("나이를 입력 : ");
				try {
					age = sc.nextInt();
				}catch(InputMismatchException e) {
					sc.nextLine();
					System.out.println("잘못 입력!");
					break;
				}
				
				
				InfoVo iv = new InfoVo(0l, age, rel);
				id.insertInfo(iv);
				break;
			case 2:
				System.out.println("1.전체적인 종교 통계");
				System.out.println("2.나이별 종교 통계");
				System.out.println("3.뒤로가기");
				System.out.print("입력 : ");
				su = sc.nextInt();
				switch(su) {
				case 1:List<Count> lc = id.viewCount();
				for(int i=0; i<lc.size(); i++) {
					System.out.println(lc.get(i).toString());
				}
				break;
				case 2:System.out.println("1.10대 미만");
				for(int i=1; i<10; i++) {
					System.out.println(i+1+"."+i+"0대");
				}
				System.out.print("입력 : ");
				try {
					age = sc.nextInt();				
				}catch(InputMismatchException e) {
					sc.nextLine();
					System.out.println("잘못 입력!");
					break;
				}
				lc = id.ageCount(age);
				for(int i=0; i<lc.size(); i++) {
					System.out.println(lc.get(i).toString());
				}
				case 3:break;
				}break;
			case 3:
				System.exit(0);
			}
		}
	}
}

