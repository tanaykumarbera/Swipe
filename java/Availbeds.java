import java.io.Serializable;

class Availbeds implements Serializable
{
   private String icunum ,iccunum,gennum,icucost,iccucost,gencost;
   
      
         Availbeds(String a,String b,String c,String d,String e,String f)
           {
              icunum=a;
              iccunum=b;
              gennum=c;
              icucost=d;
              iccucost=e;
              gencost=f;
            }

         
         void seticunum()
         {
        	 icunum=""+(Integer.parseInt(icunum)-1);
         }
         
         void seticcunum()
         {
        	 iccunum=""+(Integer.parseInt(iccunum)-1);
         }
         
         void setgennum()
         {
        	 gennum=""+(Integer.parseInt(gennum)-1);
         }
          String geticunum()
             {
                return(icunum);
              }
          
          String geticcunum()
             {
                return(iccunum);
              }

          String getgennum()
             {
                return(gennum);
              }

          String geticucost()
             {
                return(icucost);
              }

          String geticcucost()
             {
                return(iccucost);
              }

          String getgencost()
             {
                return(gencost);
              }
}