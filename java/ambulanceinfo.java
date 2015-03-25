import java.io.Serializable;

class ambulanceinfo implements Serializable

	{
		private String amb,cost;
		ambulanceinfo (String a,String b)
		{
			amb=a;
			cost=b;
		}

	String getamb()
        {
                return(amb);

        }

	String getcost()
        {
                return(cost);

        }
	void setamb()
	{
		amb=""+(Integer.parseInt(amb)-1);
	}
}