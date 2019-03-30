import java.io.*;

public class Lab6 
{

	public ArrayList<Character> embedding = new ArrayList<Character>();
	public ArrayList<String> paths = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		PriorityQueue<TreeNode<DecimalNode>> q = new PriorityQueue<TreeNode<DecimalNode>>();
		LinkedList<Morse> morses = new LinkedList<Morse>();
		BinaryTree<DecimalNode> tree = new BinaryTree<DecimalNode>();
		morses.append(morses, new ListNode<Morse>(new Morse('a', ".-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('b', "-..."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('c', "-.-."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('d', "-.."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('e', "."), null, null)); 
		morses.append(morses, new ListNode<Morse>(new Morse('f', "..-."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('g', "--."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('h', "...."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('i', ".."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('j', ".---"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('k', ".-."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('l', ".-.."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('m', "--"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('n', "-."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('o', "---"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('p', ".--."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('q', "--.-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('r', ".-."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('s', "..."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('t', "-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('u', "..-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('v', "...-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('w', ".--"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('x', "-..-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('y', "-.--"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('z', "--.."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('0', ".----"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('1', "..---"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('2', "...--"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('3', "....-"), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('4', "....."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('5', "-...."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('6', "--..."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('7', "---.."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('8', "----."), null, null));
		morses.append(morses, new ListNode<Morse>(new Morse('9', "-----"), null, null));
		for(ListNode<Morse> i = morses.getHead(); i != null; i = i.getNext())
		{
			i.getValue().setBinary(bitify(i.getValue()));
		}
		for(ListNode<Morse> i = morses.getHead(); i != null; i = i.getNext())
		{
			int decimal = toDecimal(i.getValue().getBinary());
			i.getValue().setDecimal(decimal);
			DecimalNode node = new DecimalNode(decimal, i.getValue().getActual(), i.getValue().getBinary());
			q.add(new TreeNode<DecimalNode>(node), decimal);
		}
		
		while(q.size() != 1)
		{
			TreeNode<DecimalNode> A = q.remove();
			TreeNode<DecimalNode> B = q.remove();
			DecimalNode Av = A.getValue();
			DecimalNode Bv = B.getValue();
			TreeNode<DecimalNode> M = new TreeNode<DecimalNode>(
				new DecimalNode(Av.getDecimal() + Bv.getDecimal(), '/', null));
			q.add(M, M.getValue().getDecimal());
			tree.setRootNode(M);
			if(Av.compareTo(Bv) < 0)
			{
				tree.getRootNode().setLeft(A);
				tree.getRootNode().setRight(B);
			}
			else if(Av.compareTo(Bv) > 0)
			{
				tree.getRootNode().setLeft(B);
				tree.getRootNode().setRight(A);
			}
		}
		encode(tree.getRootNode(), new String());
		// Path files might have to be changed.
		BufferedReader f = new BufferedReader(new FileReader("Message.txt"));
		BufferedWriter out = new BufferedWriter(new FileWriter("Message.txt"));
		String s = f.readLine();
		while(s != null)
		{
			for(int i=0; i<s.length(); i++)
			{
				if(s.charAt(i) != ' ')
				{
					for(int j=0; j<embedding.getLength(); j++)
					{
						if(embedding.get(j) != null && embedding.get(j) == s.charAt(i))
						{
							System.out.println("logang: "+paths.toString());
							out.write(paths.get(j));
							break;
						}
					}
				}
				else if(s.charAt(i) == ' ')
				{
					out.write("00");
				}
				out.write(' ');
			}
			s = f.readLine();
			out.newLine();
		}
		f.close();
		out.close();
		// Path files might have to be changed.
		f = new BufferedReader(new FileReader("Encoded.txt"));
		out = new BufferedWriter(new FileWriter("Decoded.txt"));
		s = f.readLine();
		while(s != null)
		{
			String str = "";
			for(int i=0; i<s.length(); i++)
			{
				if(s.charAt(i) == ' ')
				{
					System.out.println("String: " + str);
					if(str.equals("00"))
					{
						out.write(" ");
					}
					else
					{
						out.write(embedding.get(toDecimal(str.trim()) + str.length()));
					}
					str = "";
				}
				else
				{
					str += s.charAt(i);
				}
			}
			s = f.readLine();
		}
		f.close();
		out.close();
	}

	public String bitify(Morse m)
	{
		String s = m.getMorse();
		String answer = "";
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i) == '.')
			{
				answer += "10";
			}
			else
			{
				answer += "01";
			}
		}
		if(answer.charAt(0) == '0')
		{
			answer = answer.substring(1);
		}
		return answer;
	}
	
	public int toDecimal(String binary)
	{
		int answer = 0;
		for(int i=0; i < binary.length(); i++)
		{
			char num = binary.charAt(i);
			if(num != '0')
			{
				answer += Math.pow(2, binary.length()-1-i);
			}
		}
		return answer;
	}
	
	public void encode(TreeNode<DecimalNode> t, String s)
	{
		if(t.getLeft() != null || t.getRight() != null)
		{
			encode(t.getLeft(), s + '0');
			encode(t.getRight(), s + '1');
		}
		else
		{
			System.out.println(t.getValue().getValue() + " " + (toDecimal(s) + s.length()) + " " + s);
			embedding.insert(t.getValue().getValue(), toDecimal(s) + s.length());
			paths.insert(s, toDecimal(s) + s.length());
		}
	}
}
