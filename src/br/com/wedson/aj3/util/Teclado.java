/*
 * Wedson - "The Developers Company"
 * 
 */
package br.com.wedson.aj3.util;

import java.util.Scanner;

public class Teclado {

	public static String le() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
