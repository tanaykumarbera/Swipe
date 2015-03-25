import java.io.Serializable;

class Login_Info implements Serializable
{
	private String userid,password,name;

	
	String get_userid()
	{
		return userid;
	}
	
	void set_userid(String s)
	{
		userid=s;
	}
	
	void set_password(String s)
	{
		password=s;
	}
	
	boolean password_check(String s)
	{
		if(password.equals(s))
			return true;
		else
			return false;
	}
	
	String get_name()
	{
		return (name);
	}
	
	
	
}


