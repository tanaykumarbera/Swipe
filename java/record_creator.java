import java.util.*;
import java.io.*;


public class record_creator
{
File f;
Scanner sc=new Scanner(System.in);
ArrayList<Login_Info> rec;
Login_Info r;
record_creator()
{
	System.out.print("Enter directory:");
	String s=sc.next();
	sc.nextLine();
	System.out.print("\nUSERNAME: "); String u=sc.nextLine();
	System.out.print("\n\nPASSWORD: "); String p=sc.nextLine();
	
	
	try
	{
		
		File f=new File(s);
		FileOutputStream fout=new FileOutputStream(f);
		ObjectOutputStream oout=new ObjectOutputStream(fout);
		r=new Login_Info();
		r.set_userid(u);	r.set_password(p);
		rec=new ArrayList<Login_Info>();
		rec.add(r);
		
		oout.writeObject(rec);
		
		oout.close();
		fout.close();
	}catch(Exception er)
	{
		System.out.print("\nERROR: "+er.getMessage());
	}
	
	System.out.print("\nWritten....Sucessfully");
		

}


public static void main(String s[])
{
	new record_creator();
}
	
	
}
