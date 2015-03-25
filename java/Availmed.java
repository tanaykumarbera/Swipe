import java.io.Serializable;

class Availmed implements Serializable
{
   private String medgenre;
   private String medname;
   private String medstock;
   private String medprice;

      Availmed(String b,String n,String k,String p)
    {
     medgenre=b;
     medname=n;
     medstock=k;
     medprice=p; 
   }
     String getmedgenre()
        {
                return(medgenre);

        }

       String getmedname()
        {
                return(medname);
      }
      
        String getmedstock()
        {
                return(medstock);
      }
	 String getmedprice()
        {
                return(medprice);
      }
	 
	 void setmedstock(int r)
	 {
		 medstock=""+(Integer.parseInt(medstock)-r);
	 }
   
}
 