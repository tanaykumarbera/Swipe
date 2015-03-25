import java.io.Serializable;

class Oxy implements Serializable

	{
		private String ox,price;
		Oxy(String o,String pr)
		{
			ox=o;
			price=pr;
		}

	String getox()
        {
                return(ox);

        }
	String getprice()
        {
                return(price);
        }
	void setox(int n)
	{
		int temp=Integer.parseInt(ox);
		temp=temp-n;
		ox=String.valueOf(temp);
	}
	
}