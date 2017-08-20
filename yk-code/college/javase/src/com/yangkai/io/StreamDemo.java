package com.yangkai.io;
import java.io.*;
public class StreamDemo{
public static void main(String[] args) throws Exception
{
	/*
	FileOutputStream fos=new FileOutputStream("ff.txt");
	fos.write("Ñî¿ª".getBytes());
	fos.close();
	FileInputStream fis=new FileInputStream("ff.txt");
	byte[] buff=new byte[100];
	int len=fis.read(buff);
	System.out.println(new String(buff,0,len));
	fis.close();
	*/
	//int data;
	/*
	data=System.in.read();
	System.out.write(data);
	System.out.flush();
	*/
	/*
	while((data=System.in.read())!=-1)
	{
		System.out.write(data);
	}
	*/
	/*
	FileOutputStream fos=new FileOutputStream("1.txt");
	BufferedOutputStream bos=new BufferedOutputStream(fos);
	DataOutputStream dos=new DataOutputStream(bos);
	int a=16;
	byte b=1;
	float c=5.20f;
	char d='m';
	dos.writeInt(a);
	dos.writeByte(b);
	dos.writeFloat(c);
	dos.writeChar(d);
	dos.close();
	*/
	FileInputStream fis=new FileInputStream("1.txt"); 
	BufferedInputStream bis=new BufferedInputStream(fis);
	DataInputStream dis=new DataInputStream(bis);
	System.out.println(dis.readInt());
	System.out.println(dis.readByte());
	System.out.println(dis.readFloat());
	System.out.println(dis.readChar());
	dis.close();
	
}
}