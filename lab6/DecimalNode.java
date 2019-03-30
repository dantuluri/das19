

public class DecimalNode implements Comparable {

	private int decimal;
	private char value;
	private String binary;
	
	public DecimalNode(int dec, char val, String bin)
	{
		decimal = dec;
		value = val;
		binary = bin;
	}
	
	public int getDecimal()
	{
		return decimal;
	}
	
	public char getValue()
	{
		return value;
	}
	
	public String getBinary()
	{
		return binary;
	}

	@Override
	public int compareTo(Object arg0) {
		DecimalNode node = (DecimalNode) arg0;
		if(this.getDecimal() > node.getDecimal())
		{
			return 1;
		}
		else if(this.getDecimal() < node.getDecimal())
		{
			return -1;
		}
		return 0;
	}
}
