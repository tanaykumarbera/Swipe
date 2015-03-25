import java.io.Serializable;


class Doctorinfo implements Serializable
{
   private String name,dept,degr,price,timing; private int app;
	 
        Doctorinfo(String n,String d,String deg,String pr,String t,int ap)
	{ name=n;
	  dept=d;
	  degr=deg;
	  price=pr;
	  timing=t;
	  app=ap;
	}

	
	

	String getname()
        {
                return(name);

        }

       String getdept()
        {
                return(dept);

        }

	String getdegr()
        {
                return(degr);

        }

	String getprice()
        {
                return(price);

        }

	String gettiming()
        {
                return(timing);

        }

	int getapp()
	{
		return(app);
	}
	
	void setapp()
	{
		++app;
	}

}