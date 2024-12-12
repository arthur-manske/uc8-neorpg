package com.manske.neorpg.utils;

import java.io.Console;
import java.io.IOException;

import java.util.Scanner;

public class Terminal
{
	private static final Console console = System.console();
	private static final Scanner scanner = new Scanner(Terminal.console.reader());

	private static int rows = 0;
	private static int cols = 0;

	public static int getRows() { return Terminal.rows; }
	public static int getCols() { return Terminal.cols; }

	public static void saveState()
	{
		print("\u001b[?1049h");
	}

	public static void restoreState()
	{
		print("\u001b[?1049l");
	}

	public static void clear()
	{
		print("\u001b[H\u001b[2J");
	}

	/* public static void grid(List<List<String>> s) */

	public static void putChar(char ch)
	{
		System.out.append(ch);
	}

	public static void print(String s)
	{
		System.out.print(s);
		System.out.flush();
	}

	public static void println(String s)
	{
		System.out.println(s);	
		System.out.flush();
	}

	public static String getString(String s)
	{
		print(s);
		return scanner.nextLine();		
	}
	
	public static String getStringHidden(String s)
	{
		print(s);
		return console.readPassword().toString();
	}

	public static int getInt(String s)
	{
		int val;

		print(s);

		while (!scanner.hasNextInt()) {
			/* MEU println, porque ele dá flush sozinho na tela */
			println("Entrada inválida. Por favor, insira um número inteiro.");
			scanner.next();
		}

		val = scanner.nextInt();
		scanner.nextLine(); /* Joga fora o '\n', evita aquele bug lá */

		return val;
	}
	
	public static long getLong(String s)
	{
		long val;

		print(s);
		
		while (!scanner.hasNextLong()) {
			println("Entrada inválida. Por favor, insira um número inteiro.");
			scanner.next();
		}
		
		val = scanner.nextLong();
		scanner.nextLine(); /* Joga fora o '\n' */

		return val;
	}

	public static float getFloat(String s)
	{
		float val;

		print(s);
		
		while (!scanner.hasNextFloat()) {
			println("Entrada inválida. Por favor, insira um número decimal.");
			scanner.next();
		}
		
		val = scanner.nextFloat();
		scanner.nextLine(); /* Joga fora o '\n' */

		return val;
	}
	
	public static double getDouble(String s)
	{
		double val;

		print(s);
		
		while (!scanner.hasNextDouble()) {
			println("Entrada inválida. Por favor, insira um número decimal.");
			scanner.next();
		}
		
		val = scanner.nextDouble();
		scanner.nextLine(); /* Joga fora o '\n' */

		return val;
	}

	public static void fillLine(char ch)
	{
		/* imprime o caractere specificado até encher uma linha */
		for (int i = 0; i < cols; ++i) putChar(ch);
		System.out.flush();
	}

	public static void setTerminalSize() {
		try {
			int ch;
			StringBuilder response = new StringBuilder();
		
			/* Solicita o tamanho do terminal */
			print("\u001b[18t");
			System.out.flush();
		
			while ((ch = System.in.read()) != -1) {
				if (ch == 't') break; /* Fim da sequência */
				response.append((char) ch);
			}

			String[] parts = response.toString().split(";");

			Terminal.rows = Integer.parseInt(parts[1]);
			Terminal.cols = Integer.parseInt(parts[2]);

			Terminal.clear(); /* Limpa a tela de códigos que podem aparecer */
		} catch (IOException | NumberFormatException e) {
			println("Erro ao obter tamanho do terminal: " + e.getMessage());

			Terminal.rows = 0;
			Terminal.cols = 0;
		}
	}
};
