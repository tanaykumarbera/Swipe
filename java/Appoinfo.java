import java.io.Serializable;

class Appoinfo implements Serializable
{       
	
	
   			 
   	private int id,icost; String n,mob,sec,doc,time,date;
   	Appoinfo(int i, String nm, String mobi,String s,String d,String e,String f,int ic)
		{
			id=i;
			n=nm;
			mob=mobi;
			sec=s;
			doc=d;
			icost=ic;
			date=e;
			time=f;
		}

	 int getid()
		{
			return(id);
		}
	String getn()
		{
			return(n);
		}
	String getmob()
		{
			return(mob);
		}
	String getsec()
		{
			return(sec);
		}
	String getdoc()
		{
			return(doc);
		}
	String gettime()
	{
		return time;
	}
	
	String getdate()
	{
		return date;
	}
	
	int geticost()
		{
			return(icost);
		}
        
}