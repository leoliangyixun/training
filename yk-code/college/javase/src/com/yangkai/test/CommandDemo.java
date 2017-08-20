package com.yangkai.test;
interface ICommandDemo {
	public void process(int[] arr);
}
public class CommandDemo {
	public void process(int[] arr,ICommandDemo command)
	{
		command.process(arr);
	}
	public static void main(String[] args)
	{
		int[] arr={1,2,3,4,5,6,7,8,9};
		CommandDemo commd=new CommandDemo();
		commd.process(arr, new CommandDemoImpl1());
		commd.process(arr, new CommandDemoImpl2());
	}

}
class CommandDemoImpl1 implements ICommandDemo{

	public void process(int[] arr) 
	{
		for(int i:arr)
		{
			System.out.print(i+",");
		}
		System.out.print("\n");
	}
}
class CommandDemoImpl2 implements ICommandDemo{

	public void process(int[] arr) 
	{
		int sum=0;
		for(int i:arr)
		{
			sum+=i;
		}
		System.out.println(sum);
	}
}