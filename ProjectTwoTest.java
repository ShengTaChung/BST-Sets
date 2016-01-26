import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;



public class ProjectTwoTest {
	/*
	@Test
	public void test1() {
		//              |50|
		//             /	\
		//           |25|   |60|
		//          /    \
		//	   |15|        |35|
		//	  /    \      /    \
		//	 |5|  |20|  |30|  |40|
		BST<Integer> bst = new BST<Integer>();
		bst.insert(50); bst.insert(25); bst.insert(35);	bst.insert(30);
		bst.insert(15); bst.insert(20); bst.insert(5); bst.insert(40); bst.insert(60);
		System.out.print("Insert tree:\n");
		bst.printTreePreOrder(bst.getRoot());
		
		System.out.println("Search method: " + bst.search(50) +" "+ bst.search(25) +" "+ bst.search(35) +" "+
			bst.search(30) +" "+ bst.search(15) +" "+ bst.search(20) +" "+ bst.search(5)+" "+ bst.search(40) 
			+" "+ bst.search(60) +"\nSearch 100, not in tree Return null: "+ bst.search(100));
		
		System.out.println("Delete method: delete 25");
		bst.delete(25);
		bst.printTreePreOrder(bst.getRoot());
		
		Iterator<Integer> myIterator = bst.iterator();
		System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//System.out.println(myIterator.hasNext());
		//assertEquals(true, true);
	}
	*/
	@Test
	public void test2()
	{
		Set<Integer> set = new Set<Integer>();
		System.out.println("empty set:" +set);
		System.out.println(set.search(1));
	}

}
