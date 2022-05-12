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
			
			System.out.println("���� ���� ���α׷�");
			System.out.println("1.���� �Է�");
			System.out.println("2.���� ����");
			System.out.println("3.���α׷� ����");
			System.out.print("�Է� : ");
			try {
				su = sc.nextInt();				
			}catch(InputMismatchException e) {
				su = 0;
				sc.nextLine();
				System.out.println("�߸� �Է�!");
			}
			
			switch(su) {
			
			case 1: 
				List<String> ls = id.viewRel();
				for(int i=0; i<ls.size();i++) {
					System.out.println(i+1+"."+ls.get(i).toString());
				}
				System.out.println(ls.size()+1+".��Ÿ");
				System.out.print("�Է� : ");	
				try {
					index = sc.nextInt();				
				}catch(InputMismatchException e) {
					index = 0;
					sc.nextLine();
					System.out.println("�߸� �Է�!");
					break;
				}
				if(index == ls.size()+1) {
					System.out.print("������ �Է��� : ");
					rel = sc.next();
					if(id.checkRel(rel)) {
						System.out.println("�̹��ִ� ������");
						break;
					}
				}
				if(index != ls.size()+1) {
					try {
						rel = ls.get(index-1);											
					}catch(IndexOutOfBoundsException e) {
						System.out.println("�߸� �Է�!");
						break;
					}
				}
				
				System.out.print("���̸� �Է� : ");
				try {
					age = sc.nextInt();
				}catch(InputMismatchException e) {
					sc.nextLine();
					System.out.println("�߸� �Է�!");
					break;
				}
				
				
				InfoVo iv = new InfoVo(0l, age, rel);
				id.insertInfo(iv);
				break;
			case 2:
				System.out.println("1.��ü���� ���� ���");
				System.out.println("2.���̺� ���� ���");
				System.out.println("3.�ڷΰ���");
				System.out.print("�Է� : ");
				su = sc.nextInt();
				switch(su) {
				case 1:List<Count> lc = id.viewCount();
				for(int i=0; i<lc.size(); i++) {
					System.out.println(lc.get(i).toString());
				}
				break;
				case 2:System.out.println("1.10�� �̸�");
				for(int i=1; i<10; i++) {
					System.out.println(i+1+"."+i+"0��");
				}
				System.out.print("�Է� : ");
				try {
					age = sc.nextInt();				
				}catch(InputMismatchException e) {
					sc.nextLine();
					System.out.println("�߸� �Է�!");
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

