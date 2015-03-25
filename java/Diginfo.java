import java.io.Serializable;

class Diginfo implements Serializable
{
   private String name,doc,lab,reg,pri,t; private int ap1;
	
        Diginfo(String n,String d,String l,String r,String pr,String ti,int ap)
	{ name=n;
	  doc=d;
	  lab=l;
	  reg=r;
	  pri=pr;
	  t=ti;
                 ap1=ap;
	}
       
	
        
          
        String getnam()
        {
                return(name);

        }

        
        String getdoc()
        {
                return(doc);

        }

       

        String getlab()
        {
                return(lab);

        }
 
        
        String getreg()
        {
                return(reg);
        }
        
	String getprice()
	{
		return(pri);
	}
	 String gettim()
       	 {
                return(t);

        }int getap()
	  {
		return ap1;
	  }
	
        void setap(int a)
        {
        	ap1=a;
        }

}