import java.util.Iterator;


public class Set<T extends Comparable<T>> implements Iterable<T>, Comparable <Set<T>> 
{
	private BST<T> myBST = new BST<T>();

	public Set(){											//default ctor
	}
	public Set(T[]setElements)								//Ctor take array of set elements and insert each into the set
	{
		for(T element: setElements)
			myBST.insert(element);
	}

	public T search(T target){								//Search
		return myBST.search(target);
	}

	public boolean elementOf(T t){							//Returns whether t is in the set or not
		if(myBST.search(t) != null)
			return true;
		else
			return false;
	}

	public boolean insert(T value){							//Insert
		return myBST.insert(value);
	}

	public boolean delete(T target){						//Delete
		return myBST.delete(target);
	}

	public Set<T> copy()									//Return a copy of the current set
	{
		Set<T> copyThisSet = this;							//This is the set we want to copy
		Set<T> newSet = new Set<T>();					
		for(T element: copyThisSet)							//Take each element from this set and insert to new set
			newSet.insert(element);
		return newSet;
	}
	
	public Set<T> union(Set<T> s)									//Return a set which is the union of the current set and s.
	{
		Set<T> newSet = new Set<T>();
		Iterator<T> setOneIter = this.iterator();					//First set, the one called method
		Iterator<T> setTwoIter = s.iterator();						//Second set, argument
		while( setOneIter.hasNext() )
			newSet.insert(setOneIter.next());						//Iterate through set and add to new set
		while( setTwoIter.hasNext() )
			newSet.insert(setTwoIter.next());
		return newSet;
	}
	
	public Set<T> intersection(Set<T> s)							//Return a set which is the intersection of the current set and s
	{
		Set<T> newSet = new Set<T>();
		Iterator<T> setOneIter = this.iterator();					//First set, the one called method
		while( setOneIter.hasNext() )
		{
			T element = setOneIter.next();
			if( s.elementOf( element ) )							//Check if second set has the same element
				newSet.insert(element);								//If it does add to new set
		}
		return newSet;
	}
	
	public boolean subsetOf(Set s)									//Return whether this is a subset of s
	{
		Set<T> subSet = this;
		boolean isSubset = true;
		Iterator<T> subsetIterator = this.iterator();				//Create iterator for subset
		while(subsetIterator.hasNext() && isSubset)					//Loop until no more element or has different element
		{
			T element = subsetIterator.next();						//take out each element from subset
			if(!s.elementOf(element))								//See if element in the set "s"
				isSubset = false;
		}
		return isSubset;
	}
	
	@Override
	public Iterator<T> iterator() {									//Iterator
		return myBST.iterator();
	}
	
	
	public String toString()										//toString
	{
		Iterator<T> myIterator = iterator();
		String message = "{";										//Add "{" at the beginning
		if(!myIterator.hasNext())									//empty set
			message = message+ "}";
		else
		{
			while(myIterator.hasNext())								//Loop until no more element
			{
				message = message + myIterator.next() + ",";		//Add element to message
			}
		}
		message = message.substring(0,message.length()-1) + "}"; 	//Add "}" at the end
		return message;
	}
	
	public int compareTo(Set<T> s)		//Return whether this is a subset of s	
	{
		Iterator<T> setOneIter = this.iterator();		//Create iterator for both sets
		Iterator<T> setTwoIter = s.iterator();
		boolean allSame = false;							//element are all the same
		boolean setOneIsLarger = false;
		boolean setTwoIsLarger = false;
		boolean setOneDone = false;
		boolean setTwoDone = false;
		
		while( !allSame  && !setOneIsLarger && !setTwoIsLarger)	//Loop until sets are same OR one is larger than
		{
			T elementSetOne = null, elementSetTwo = null;
			if(setOneIter.hasNext())							//If setOne has more element
				elementSetOne = setOneIter.next();
			else
				setOneDone = true;
			if(setTwoIter.hasNext())							//If setTwo has more element
				elementSetTwo = setTwoIter.next();
			else
				setTwoDone = true;
			
			if(setOneDone && setTwoDone)						//If both sets have no more element
				allSame = true;									//then both sets are the same
			else if(setOneDone)									//Else if setOne has no more element
				setTwoIsLarger = true;							//We make setTwo larger
			else if(setTwoDone)									//Else if setTwo has no more element
				setOneIsLarger = true;							//We make setOne larger
			
			else												//Else we compare the both elements
			{
				int value = elementSetOne.compareTo(elementSetTwo);	//compare elements
				if(value < 0)										//If setOne element larger than setTwo element
					setTwoIsLarger = true;
				else if(value > 0)									//If setTwo element larger than setOne element
					setOneIsLarger = true;								
			}														//Both same we do nothing
		}
		if(allSame)
			return 0;
		else if(setOneIsLarger)
			return 1;
		else
			return -1;
	}
}
