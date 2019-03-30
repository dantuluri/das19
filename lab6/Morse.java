

public class Morse {
	private char actualValue;
	private String morseValue;
	private String binary;
	private int decimalValue;
	
	public Morse(char a, String m)
	{
		actualValue = a;
		morseValue = m;
	}

	public char getActual()
	{
		return actualValue;
	}
	
	public String getMorse()
	{
		return morseValue;
	}
	
	public void setBinary(String b)
	{
		binary = b;
	}
	
	public String getBinary()
	{
		return binary;
	}
	
	public void setDecimal(int decimal)
	{
		decimalValue = decimal;
	}
	
	public int getDecimal()
	{
		return decimalValue;
	}
}
