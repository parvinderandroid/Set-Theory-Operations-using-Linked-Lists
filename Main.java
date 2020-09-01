import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.StringJoiner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

class Node
{
	String data;
	Node next;
	Node prev;
}
class DoublyCircularLinkedList
{
	Node start,p,q,r,s,t;
	int size;
	int search(String s)
	{
		int pos=1;
		if(start==null)
			return -1; //List is empty
		else
		{
			p=start;
			do
			{
				if(p.data.equals(s))
				{
					p=null;
					return pos;
				}
				pos++;
				p=p.next;
			}while(p!=start);
			if(p==start && p.data.equals(s)==false)
			{
				p=null;
				return -2; //Element not found
			}
		}
		return 0;
	}
	void add(String s, int pos)
	{
		int i;
		p=new Node();
		p.data=s;
		p.next=null;
		p.prev=null;
		if(start==null)
		{
			start=p;
			start.next=start;
			start.prev=start;
			p=null;
			size++;
		}
		else if(pos==1)
		{
			q=start.prev;
			q.next=p;
			p.prev=q;
			p.next=start;
			start.prev=p;
			start=p;
			p=null;
			q=null;
			size++;
		}
		else if(pos==size+1)
		{
			start.prev.next=p;
			p.prev=start.prev;
			p.next=start;
			start.prev=p;
			p=null;
			size++;
		}
		else
		{
			q=start;
			for(i=1;i<pos-1;i++)
			{
				q=q.next;
				if(q==start)
					break;
			}
			p.next=q.next;
			p.prev=q;
			q.next=p;
			p.next.prev=p;
			p=null;
			q=null;
			size++;
		}
	}
	void del(int pos)
	{
		int i;
		if(size==0)
			JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		else if(pos>size)
			JOptionPane.showMessageDialog(null, "Position not in middle", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		else if(start.next==start)
		{
			JOptionPane.showMessageDialog(null, "Deleted element = " + start.data, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
			start=null;
			size--;
		}
		else if(pos==1)
		{
			JOptionPane.showMessageDialog(null, "Deleted element = " + start.data, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
			start.prev.next=start.next;
			start.next.prev=start.prev;
			p=start;
			start=start.next;
			p=null;
			size--;
		}
		else if(pos==size)
		{
			p=start.prev.prev;
			q=start.prev;
			JOptionPane.showMessageDialog(null, "Deleted element = " + q.data, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
			p.next=start;
			start.prev=p;
			p=null;
			q=null;
			size--;
		}
		else
		{
			p=start;
			for(i=1;i<pos;i++)
				p=p.next;
			JOptionPane.showMessageDialog(null, "Deleted element = " + p.data, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
			p.prev.next=p.next;
			p.next.prev=p.prev;
			p=null;
			size--;
		}
	}
	void delElement(String s)
	{
		int pos=search(s);
		if(pos==-1)
			JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		else if(pos==-2)
			JOptionPane.showMessageDialog(null, "Element not found", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		else
			del(pos);
	}
	void display()
	{
		p = start;
		String t = "";
		do {
			t += p.data + "\n";
			p = p.next;
		}while(p!=start);
		JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
	}
	void displayReverse()
	{
		p = q = start.prev;
		String t = "";
		do {
			t += p.data + "\n";
			p = p.prev;
		}while(p!=q);
		JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
	}
	void nodeinfo()
	{
		int i=1;
		if(size==0)
			JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		else
		{
			String t = "";
			p=start;
			t += String.format("%-25s %-25s %-25s %-25s %-25s \n", "Position", "Prev", "Address", "Data", "Next");
			do
			{
				t += String.format("%-25d %-25d %-25d %-25s %-25d \n", i++, p.prev.hashCode(), p.hashCode(), p.data, p.next.hashCode());
				p=p.next;
			}while(p!=start);
			JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
			p=null;
		}
	}
	void delnode(Node a)
	{
		int pos=1;
		p=start;
		while(p!=a)
		{
			pos++;
			p=p.next;
		}
		del(pos);
	}
	void duplicate()
	{
		r=start;
		do
		{
			s=r;
			while(s.next!=start)
			{
				if (r.data.equals(s.next.data))
				{
					t=s.next;
					delnode(t);
				}
				else
					s=s.next;
			}
			r=r.next;
		}while(r!=start);
		r=null;
		s=null;
	}
	String get(int pos)
	{
		int i;
		p=start;
		for(i=1;i<pos;i++)
			p=p.next;
		return p.data;
	}
	void set(String s, int pos)
	{
		int i;
		p=start;
		for(i=1;i<pos;i++)
			p=p.next;
		p.data=s;
		p=null;
	}
	void sort()
	{
		int i,j;
		String t1,t2;
		for(i=1;i<=size;i++)
		{
			for(j=1;j<=size-i;j++)
			{
				if(get(j).compareToIgnoreCase(get(j+1))>0)
				{
					t1=get(j);
					t2=get(j+1);
					set(t1, j+1);
					set(t2, j);
				}
			}
		}
	}
	boolean contains(String s)
	{
		if(search(s)>0)
			return true;
		return false;
	}
	void readFile(String name)
	{
		try
		{
			var fr=new FileReader(name);
			var br=new BufferedReader(fr);
			String s = br.readLine();
			while(s != null)
			{
				add(s, size+1);
				s=br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "File doesn't exist", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
		}
	}
	void writeFile(String name)throws Exception
	{	
		var fw=new FileWriter(name, true);
		var bw=new BufferedWriter(fw);
		var pw=new PrintWriter(bw);
		p = start;
		String t = "";
		do {
			t += p.data + "\n";
			p = p.next;
		}while(p!=start);
		pw.print(t);
		pw.close();
	}
}

class Sets
{
	public boolean contains(String a[], String n)
	{
		for(int i=0;i<a.length;i++)
			if(a[i].equals(n))
				return true;
		return false;
	}
	public String[] toStringArray(DoublyCircularLinkedList a)
	{
		String b[]=new String[a.size];
		for(int i=0;i<a.size;i++)
			b[i]=a.get(i+1);
		Arrays.sort(b);
		return b;
	}
	public String printSet(String a[])
	{
		int i;
		var myString=new StringJoiner(", ", "{", "}");
		for(i=0;i<a.length;i++)
			myString.add(a[i]);
		return myString.toString();
	}
	public String[] subtract(String a[], String b[])
	{
		int i;
		var set=new DoublyCircularLinkedList();
		for(i=0;i<a.length;i++)
			if(contains(b, a[i])==false)
				set.add(a[i], set.size+1);
		return toStringArray(set);
	}
	public String[] union(String a[], String b[])
	{
		var set=new DoublyCircularLinkedList();
		for(int i=0;i<a.length;i++)
			if(set.contains(a[i])==false)
				set.add(a[i], set.size+1);
		for(int i=0;i<b.length;i++)
			if(set.contains(b[i])==false)
				set.add(b[i], set.size+1);
		return toStringArray(set);
	}
	public String[] intersection(String a[], String b[])
	{
		int i,j=0;
		String c[]=new String[a.length+b.length];
		for(i=0;i<a.length;i++)
			c[j++]=a[i];
		for(i=0;i<b.length;i++)
			c[j++]=b[i];
		var set=new DoublyCircularLinkedList();
		for(i=0;i<c.length;i++)
			if(contains(a, c[i]) && contains(b, c[i]) && set.contains(c[i])==false)
				set.add(c[i], set.size+1);
		return toStringArray(set);
	}
	public String cartesian(String a[], String b[])
	{
		var myString=new StringJoiner(", ", "{", "}");
		int i,j;
		for(i=0;i<a.length;i++)
			for(j=0;j<b.length;j++)
				myString.add( String.format("(%s,%s)", a[i], b[j]) );
		return myString.toString();
	}
}

class Main
{
	public static void main(String[]args)throws Exception
	{
		out:while(true)
		{
			String t;
			var s1 = new StringBuilder();
			s1.append("Press 1 : Linked List operations \n");
			s1.append("Press 2 : Set operations using Linked List \n");
			s1.append("Press any other number to exit the app \n");
			byte choice = Byte.parseByte( JOptionPane.showInputDialog(s1) );
			switch(choice)
			{
			case 1:
				var list=new DoublyCircularLinkedList();
				int pos;
				String s;
				in : while(true)
				{
					var sb2 = new StringBuilder();
					sb2.append("Press 1  : Add at beginning \n");
					sb2.append("Press 2  : Add at end \n");
					sb2.append("Press 3  : Add at position \n");
					sb2.append("Press 4  : Delete from beginning \n");
					sb2.append("Press 5  : Delete from end \n");
					sb2.append("Press 6  : Delete from position \n");
					sb2.append("Press 7  : Delete any element \n");
					sb2.append("Press 8  : Get value at position \n");
					sb2.append("Press 9  : Set value at position \n");
					sb2.append("Press 10 : Search any element \n");
					sb2.append("Press 11 : Display number of elements \n");
					sb2.append("Press 12 : Sort list \n");
					sb2.append("Press 13 : Remove duplicate elements \n");
					sb2.append("Press 14 : Display List \n");
					sb2.append("Press 15 : Display Reverse List \n");
					sb2.append("Press 16 : Display Node Info \n");
					sb2.append("Press 17 : Read from text file \n");
					sb2.append("Press 18 : Write to text file \n");
					sb2.append("Press any other number to exit \n");
					choice = Byte.parseByte( JOptionPane.showInputDialog(sb2) );
					switch(choice)
					{
						case 1:
							s = JOptionPane.showInputDialog("Enter element to add");
							list.add(s, 1);
							break;
						case 2:
							s = JOptionPane.showInputDialog("Enter element to add");
							list.add(s, list.size+1);
							break;
						case 3:
							pos = Integer.parseInt( JOptionPane.showInputDialog("Enter position to add") );
							if(pos>list.size+1)
							{
								JOptionPane.showMessageDialog(null, "Position not in list", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
								break;
							}
							s = JOptionPane.showInputDialog("Enter element to add");
							list.add(s, pos);
							break;
						case 4:
							list.del(1);
							break;
						case 5:
							list.del(list.size);
							break;
						case 6:
							pos = Integer.parseInt( JOptionPane.showInputDialog("Enter position to delete") );
							list.del(pos);
							break;
						case 7:
							s = JOptionPane.showInputDialog("Enter element to be deleted");
							list.delElement(s);
							break;
						case 8:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
							{
								pos = Integer.parseInt( JOptionPane.showInputDialog("Enter position to get value") );
								if(pos>list.size)
									JOptionPane.showMessageDialog(null, "Position not in list", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Value at position = " + list.get(pos), "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							}
							break;
						case 9:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
							{
								pos = Integer.parseInt( JOptionPane.showInputDialog("Enter position to set value") );
								if(pos>list.size)
									JOptionPane.showMessageDialog(null, "Position not in list", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
								else
								{
									t = "" + "Old value = " + list.get(pos);
									t += "\nEnter new value";
									s = JOptionPane.showInputDialog(t);
									list.set(s, pos);
									JOptionPane.showMessageDialog(null, "New value = " + list.get(pos), "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
								}
							}
							break;
						case 10:
							s = JOptionPane.showInputDialog("Enter element to search");
							pos=list.search(s);
							if(pos==-1)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else if(pos==-2)
								JOptionPane.showMessageDialog(null, "Element not found", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "Element found at position " + pos, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 11:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "Number of elements = " + list.size, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 12:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								list.sort();
							break;
						case 13:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								list.duplicate();
							break;
						case 14:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								list.display();
							break;
						case 15:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
								list.displayReverse();
							break;
						case 16:
							list.nodeinfo();
							break;
						case 17:
							s = JOptionPane.showInputDialog("Enter name of file");
							if(s.endsWith(".txt") == false)
								s += ".txt";
							list.readFile(s);
							break;
						case 18:
							if(list.size==0)
								JOptionPane.showMessageDialog(null, "List is empty", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							else
							{
								s = JOptionPane.showInputDialog("Enter name of file");
								if(s.endsWith(".txt") == false)
									s += ".txt";
								list.writeFile(s);
							}
							break;
						default:
							JOptionPane.showMessageDialog(null, "You have opted to quit", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break in;
					}
				}
				break;
			case 2:
				String a[],b[],x[];
				a=b=new String[0];
				int n1,n2,i;
				var obj=new Sets();
				in : while(true)
				{
					var sb3 = new StringBuilder();
					sb3.append("Press 1  : Input both sets \n");
					sb3.append("Press 2  : View Both Sets \n");
					sb3.append("Press 3  : Union of A & B \n");
					sb3.append("Press 4  : Intersection of A & B \n");
					sb3.append("Press 5  : A - B \n");
					sb3.append("Press 6  : B - A \n");
					sb3.append("Press 7  : A Complement \n");
					sb3.append("Press 8  : B Complement \n");
					sb3.append("Press 9  : Symmetric Difference between A & B \n");
					sb3.append("Press 10 : Cartesian Product of A & B \n");
					sb3.append("Press any other number to exit \n");
					choice = Byte.parseByte( JOptionPane.showInputDialog(sb3) );
					switch(choice)
					{
						case 1:
							n1 = Integer.parseInt( JOptionPane.showInputDialog("Enter number of elements you want in Set A") );
							n2 = Integer.parseInt( JOptionPane.showInputDialog("Enter number of elements you want in Set B") );
							a=new String[n1];
							b=new String[n2];
							JOptionPane.showMessageDialog(null, "Enter all the elements in Set A", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							for(i=0;i<n1;i++)
								a[i] = JOptionPane.showInputDialog("Enter element number " + (i+1));
							Arrays.sort(a);
							JOptionPane.showMessageDialog(null, "Enter all the elements in Set B", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							for(i=0;i<n2;i++)
								b[i] = JOptionPane.showInputDialog("Enter element number " + (i+1));
							Arrays.sort(b);
							break;
						case 2:
							t = "" + "A = ";
							t += obj.printSet(a);
							t += "\nB = ";
							t += obj.printSet(b);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 3:
							t = "" + "Union = ";
							t += obj.printSet(obj.union(a, b));
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 4:
							t = "" + "Intersection = ";
							x=obj.intersection(a, b);
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 5:
							t = "" + "A - B = ";
							x=obj.subtract(a, b);
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 6:
							t = "" + "B - A = ";
							x=obj.subtract(b, a);
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 7:
							t = "" + "A complement = ";
							x=obj.subtract(obj.union(a, b), a);
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 8:
							t = "" + "B complement = ";
							x=obj.subtract(obj.union(a, b), b);
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 9:
							t = "" + "Symmetric Difference = ";
							x=obj.union(obj.subtract(a, b), obj.subtract(b, a));
							if(x.length == 0)
								t += "{ }";
							else
								t += obj.printSet(x);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						case 10:
							t = "" + "Cartesian Product = ";
							t += obj.cartesian(a, b);
							JOptionPane.showMessageDialog(null, t, "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break;
						default :
							JOptionPane.showMessageDialog(null, "You have opted to quit", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
							break in;
					}
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "You have opted to close the app", "Buddha Institute of Technology", JOptionPane.PLAIN_MESSAGE);
				break out;
			}
		}
	}
}