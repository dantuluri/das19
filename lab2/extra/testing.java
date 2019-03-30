public class testing
{
	public static void main(String[]args)
	{
		testing cool = new testing();
		cool.run();
	}

	public void run()
	{
		// System.out.println(alphaMap("a"));

		String value = "Surya";
		// System.out.println(getCharacterAt(value,0));
		System.out.println("ASDASD "+value.substring(0, value.length() - 1));
	}

	public String getCharacterAt(String str, int n)
	{
		return ""+ str.charAt(str.length()-(n+1));
	}



}