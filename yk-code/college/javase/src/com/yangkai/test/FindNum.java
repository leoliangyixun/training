package com.yangkai.test;

public class FindNum {

	public static void main(String[] args) 
	{
		int i;
		int j;
		int k;
		// label:
		for (i = 1; i < 36; i++)
			for (j = 1; j < 36; j++) 
			{
				k = 36 - j - i;
				if (k % 2 == 0) 
				{
					if (k > 0 && k < 36 && ((4 * i + 3 * j + k / 2) == 36)) 
					{
						System.out.println("i=" + i);
						System.out.println("j=" + j);
						System.out.println("k=" + k);
						// break label;
					}
				}
			}

	}

}
