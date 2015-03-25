import java.io.Serializable;


public class blood implements Serializable
{
	private int stock,price;
	String type;
	
	blood(String t,int s,int p)
	{
		type=t;
		stock=s;
		price=p;
	}
	
	String gettype()
	{
		return type;
	}
	
	int getstock()
	{
		return stock;
	}
	
	int getprice()
	{
		return price;
	}
	
	void setstock(int r)
	{
		stock=stock-r;
	}
}
