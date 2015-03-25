import java.util.*;
import java.io.*;
public class Acess_File
{

	void write_to_file(String file_location, ArrayList object) throws Exception
	{
		File f=new File(file_location);
		
		FileOutputStream fout= new FileOutputStream(f);
		ObjectOutputStream oout=new ObjectOutputStream(fout);
		
		oout.writeObject(object);
		
		oout.close();
		fout.close();

	}
	
	ArrayList read_from_file(String file_location) throws Exception
	{
		File f=new File(file_location);

		FileInputStream fin= new FileInputStream(f);
		ObjectInputStream oin=new ObjectInputStream(fin);
		
		ArrayList object = ((ArrayList)oin.readObject());
		
		oin.close();
		fin.close();
		return (object);
	}
}
