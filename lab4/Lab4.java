import java.io.*;
import java.util.*;

/**
 * Lab4.java
 * 
 * @author Surya Dantuluri
 * @version 1.0
 * @since 2/12/2019
 */

public class Lab4 
{
	public static void main(String[] args) 
	{
		Lab4 four = new Lab4();
		four.run();
	}

	public void run() {
		try (Scanner reader = new Scanner(new FileReader("Customer.csv"))) 
		{
			reader.useDelimiter(",");
			while (reader.hasNextLine()) 
			{
                // Input
				String inputInitial = reader.nextLine();
				String[] tempArray = inputInitial.split(",");
				String fname = tempArray[0];
				String lname = tempArray[1];
				String tempId = tempArray[2];
				tempId = tempId.substring(0, tempId.indexOf("-") - 2);
				int id = Integer.parseInt(tempId);

				Customer visitor = new Customer(fname, lname, id);

				mod.HashInsert(visitor);
				mid.HashInsert(visitor);
				mul.HashInsert(visitor);
			}
			reader.close();
		}
		catch (IOException | InputMismatchException ex) 
		{
			ex.printStackTrace();
		}
	}

	class TreeNode<T>
	T data
	TreeNode<T> left
	TreeNode<T> right