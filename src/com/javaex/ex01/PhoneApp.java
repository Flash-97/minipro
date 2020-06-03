package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		List<Person> pList = new ArrayList<Person>();

		readFile(pList);

		boolean search = true;

		Scanner sc = new Scanner(System.in);

		System.out.println("**********************************************");
		System.out.println("*           전화번호 관리 프로그램           *");
		System.out.println("**********************************************");

		while (search) {
			System.out.println();
			System.out.println("1.리스트   2.등록   3.삭제   4.검색   5.종료");
			System.out.println("----------------------------------------------");
			System.out.print(">메뉴번호: ");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				showList(pList);
				break;

			case 2:
				addList(pList);
				break;

			case 3:
				deleteList(pList);
				break;

			case 4:
				search(pList);
				break;

			case 5:
				end(pList);
				search = false;
				break;

			default:
				System.out.println("[다시 입력해 주세요.]");
				break;
			} // switch

		} // while

		sc.close();
	}

	static Scanner sc = new Scanner(System.in);

	// 메소드

	public static void readFile(List<Person> pList) throws IOException {
		InputStream in = new FileInputStream("./PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}
			String[] callInfo = str.split(",");
			Person person = new Person(callInfo[0], callInfo[1], callInfo[2]);
			pList.add(person);
		}

		br.close();
	} // readFile

	public static void showList(List<Person> pList) throws IOException {
		System.out.println("<1.리스트>");
		for (Person p : pList) {
			System.out.println(pList.indexOf(p) + 1 + p.showList());
		}
	} // showList

	public static void addList(List<Person> pList) throws IOException {
		OutputStream os = new FileOutputStream("./PhoneDB.txt", true);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);

		System.out.println("<2.등록>");

		System.out.print(">이름: ");
		String name = sc.next();

		System.out.print(">휴대전화: ");
		String hp = sc.next();

		System.out.print(">회사전화: ");
		String company = sc.next();

		Person newP = new Person(name, hp, company);
		pList.add(newP);

		bw.write(name + "," + hp + "," + company);
		bw.newLine();

		System.out.println("[등록되었습니다.]");

		bw.close();
	} // addList

	public static void deleteList(List<Person> pList) throws IOException {
		OutputStream ost = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter osr = new OutputStreamWriter(ost);
		BufferedWriter bf = new BufferedWriter(osr);

		System.out.println("<3.삭제>");
		System.out.print(">번호 :");
		int num = sc.nextInt();
		pList.remove(num);

		for (Person p : pList) {
			bf.write(p.getName() + "," + p.getHp() + "," + p.getCompany());
			bf.newLine();
		}

		System.out.println("[삭제되었습니다.]");

		bf.close();
	} // delete

	public static void search(List<Person> pList) throws IOException {
		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String str = sc.next();

		for (Person pr : pList) {
			String sr = pr.getName();
			if (sr.contains(str)) {
				int num1 = pList.indexOf(pr);
				System.out.println(num1 + pr.showList());
			}

		}
	} // search

	public static void end(List<Person> pList) throws IOException {
		System.out.println("**********************************************");
		System.out.println("*                 감사합니다.                *");
		System.out.println("**********************************************");
	}

}