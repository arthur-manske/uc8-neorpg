package com.manske.neorpg.utils;

public class Timer
{
	public static int sleep(String s, long ms)
	{
		try {
			Terminal.print(s);

			Thread.sleep((int) (ms / 4));
			Terminal.print(".");

			Thread.sleep((int) (ms / 4));
			Terminal.print(".");

			Thread.sleep((int) (ms / 4));
			Terminal.print(".");

			Thread.sleep((int) (ms / 4));
			Terminal.print("\n");

			return 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}
};
