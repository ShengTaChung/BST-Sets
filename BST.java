import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T>
{
	private BSTNode<T> root;

	public T search(T target){				//Return target or null returned if not in tree			
		return search(target, root);
	}
	private T search(T target, BSTNode<T> p){
		if(p == null)						//Checking empty subtree
			return null;
		int comp = target.compareTo(p.data);
		if(comp == 0)						//Found it
			return p.data;
		if(comp < 0)						//Search LST
			return search(target, p.left);
		return search(target, p.right);		//Search RST
	}
	public boolean insert(T value){			//Insert value into tree, return false if fail(Value already in tree)
		if(root == null){					//Empty tree
			root = new BSTNode<T>(value);
			return true;
		}
		return insert(value, root);
	}
	private boolean insert(T value, BSTNode<T> p){	//Pre-condition: P is not null
		int comp = value.compareTo(p.data);
		if(comp == 0){
			return false;							//Attempt to insert duplicate value
		}
		if(comp < 0){								//Insert into LST
			if(p.left == null){						//Found insertion point
				p.left = new BSTNode<T>(value);
				return true;
			}
			else{									//p.left != null
				return insert(value, p.left);		//Continues down LST
			}
		}
		else{										//comp > 0 insert RST
			if(p.right == null){					//Found insert point
				p.right = new BSTNode<T>(value);
				return true;
			}
			else{
				return insert(value, p.right);
			}
		}
	}
	
	public boolean delete(T value){			// 3 cases Target: (1) is leaf, (2) has one child, (3) has Two children 
		return delete(value, root, root);
	}
	private boolean delete(T value, BSTNode<T> current, BSTNode<T> parent){	//Return false if not found
		if(current == null)
			return false;											//Value not found 
		int comp = value.compareTo(current.data);
		if(comp < 0)
			return delete(value, current.left, current); 			//Go left
		else if(comp > 0)
			return delete(value, current.right, current);			//Go right
		else														//Found node to delete
		{
			if(current.left == null && current.right == null)		//Case(1) Target is a leaf
				deletingCaseOne(current, parent);
			else if(current.right == null || current.left == null)	//case(2) Target has one child
				deletingCaseTwo(current, parent);
			else													//Case(3) Target has 2 children
				deletingCaseThree(current);
			return true;										
		}
	}
	private void deletingCaseOne(BSTNode<T> deleteMe, BSTNode<T> parent){
		if(deleteMe == root)								//Only one element in tree
			root = null;
		if(parent.right == deleteMe)						//Is right child
			parent.right = null;							//Disconnect right child
		else												//Is left child
			parent.left = null;								//Disconnect left child
	}
	private void deletingCaseTwo(BSTNode<T> deleteMe, BSTNode<T> parent){
		if(deleteMe.right == null)							//Target has one child, if left
		{
			if(deleteMe == root)							//Only one element in tree
				root = deleteMe.left;
			else if(parent.right == deleteMe)				//Is right child
				parent.right = deleteMe.left;				//Current's left child is now Parent's right child
			else											//Is left child
				parent.left = deleteMe.left;				//Current's left child is now Parent's left child
		}
		else if(deleteMe.left == null)						//Target has one child, if right
		{
			if(deleteMe == root)							//Only one element in tree
				root = deleteMe.right;
			else if(parent.right == deleteMe)				//Is right child
				parent.right = deleteMe.right;				//Target's right child is now Parent's right child
			else											//Is left child
				parent.left = deleteMe.right;				//Target's right child is now Parent's left child
		}	
	}
	private void deletingCaseThree(BSTNode<T> deleteMe){
		BSTNode<T> parentOfLargestLST = findMaxNodeParent(deleteMe.left);		//Find Largest's parent in LST
		BSTNode<T> largestLST;
		if( parentOfLargestLST.right != null )									
			largestLST = parentOfLargestLST.right;								//Largest in LST is on right side
		else																	//If deleteMe.left is the only node in left subtree
		{
			largestLST = parentOfLargestLST;									//Make parent of largestLST become largest in left subtree
			parentOfLargestLST = deleteMe;										//and delete target become parent of the largest in left subtree 
		}
		deleteMe.data = largestLST.data;										//Switch data
		
		if(largestLST.left == null && largestLST.right == null)					//Case(1) Target is a leaf
			deletingCaseOne(largestLST, parentOfLargestLST);
		else if(largestLST.right == null || largestLST.left == null)			//case(2) Target has one child
			deletingCaseTwo(largestLST, parentOfLargestLST);
		
	}
	private BSTNode<T> findMaxNodeParent(BSTNode<T> subRoot){					//Will find the largest value's parent of the subtree
		BSTNode<T> parent = subRoot;											//Check empty subtree, however delete case 3 will always has left child
		if(subRoot == null)														//But good to check maybe can use for other methods
			return null;
		else{
			while(subRoot.right != null){										//Go all the way to the right,loop end before reach null
				parent = subRoot;												//set parent before change the subRoot
				subRoot = subRoot.right;										//set subRoot to next right node
			}
			return parent;
		}
	}
	@Override
	public Iterator<T> iterator() {												//Iterator
		TreeIterator myIterator = new TreeIterator(this);
		return myIterator;
	}
	
	private class TreeIterator implements Iterator<T>	//Inner class Iterator
	{
		Stack<BSTNode<T>> stack;
		private BSTNode<T> cursor;							
		public TreeIterator(BST<T> bst)					//Ctor
		{
			stack = new Stack<BSTNode<T>>();
			cursor = bst.root;
		}
		public boolean hasNext()						//Method check if has next element
		{
			return (!stack.empty() || cursor != null);
		}
		
		public T next() {
			BSTNode<T> savedCursor = null;
			while(cursor != null)							//Push the cursor and go all the way left until cursor is null
			{
				stack.push(cursor);							//Push: add to stack
				cursor = cursor.left;						//Go left
			}
			if(!stack.isEmpty())							//If the stack isn't empty
			{
				cursor = stack.pop();						//Pop top of stack into cursor
				savedCursor = cursor;						//And save pointer at savedCursor
				cursor = cursor.right;
			}
			return savedCursor.data;
		}
	}
	
	private class BSTNode<G>{		//Inner class Node
		G data;
		BSTNode<G> left, right;
		public BSTNode(G g){
			data = g;
		}
	}
}
