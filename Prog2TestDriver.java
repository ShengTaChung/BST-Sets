/*
   Test code for Program 2.  
   
   This test does simple insert and deletes then creates
   two sets of integers and takes union and intersection
   of them.  It then creates two sets of sets of 
   integers and takes union and intesertion.  The
   test is then repeated with Strings in place of
   integers
*/


@SuppressWarnings("unchecked")
public class Prog2TestDriver
{
   public static void main(String[] args)
   {
      basicTest();
      System.out.println();
      iTest();
      System.out.println();
      sTest();
      System.out.println();
      opTest();
   }
   
   // test insertion and deletion of set elements
   private static void basicTest()
   {
      // insert values and display growing set
      Set<Integer> set = new Set<Integer>();
      System.out.println("empty set: " + set);
      set.insert(3);
      System.out.println("insert 3: " + set);
      set.insert(1);
      System.out.println("insert 1: " + set);
      set.insert(2);
      System.out.println("insert 2: " + set);
      set.insert(5);
      System.out.println("insert 5: " + set);
      set.insert(4);
      System.out.println("insert 4: " + set);
      set.insert(4);      // repeatef omdrty
      System.out.println("insert 4 again (should leave set unchanged): " + set);
      // delete values and display shrinking set
      set.delete(3);
      System.out.println("delete 3: " + set);
      set.delete(1);
      System.out.println("delete 1: " + set);
      set.delete(2);
      System.out.println("delete 2: " + set);
      set.delete(5);
      System.out.println("delete 5: " + set);
      set.delete(4);
      System.out.println("delete 4: " + set);
      
   }
   
   /* test for integer sets and sets of sets -- creates
      sets of integers, performs operations, then creates
      sets of sets of integers and performs operations
    */
   private static void iTest()
   {
      // create an array of sets of Integer
      Set<Integer>[] aSets = (Set<Integer>[]) new Set[4];
      // assign set values to first two array elements
      Integer[] a1 = {1, 2, 3};
      Integer[] a2 = {2, 4, 6, 8};
      Integer[] a3, a4;
      aSets[0] = new Set(a1);
      aSets[1] = new Set(a2);
      // perform set operations on the sets
      aSets[2] = aSets[0].union(aSets[1]);
      aSets[3] = aSets[0].intersection(aSets[1]);
      for (int i = 0; i < 4; i++)
         System.out.println(aSets[i]);
      // repeat with sets of sets of integers
      Set<Set<Integer>>[] aSetsOfSets = (Set<Set<Integer>>[]) new Set[4];
      for (int i = 0; i < 4; i++)
         aSetsOfSets[i] = new Set<Set<Integer>>();
      aSetsOfSets[0].insert(aSets[0]);
      aSetsOfSets[0].insert(aSets[1]);
      aSetsOfSets[0].insert(aSets[2]);
      aSetsOfSets[1].insert(aSets[2]);
      aSetsOfSets[1].insert(aSets[3]);
      aSetsOfSets[2] = aSetsOfSets[0].union(aSetsOfSets[1]);
      aSetsOfSets[3] = aSetsOfSets[0].intersection(aSetsOfSets[1]);
      for (int i = 0; i < 4; i++)
         System.out.println(aSetsOfSets[i]);
   }
   // This test is identical to the previous test but with Strings  
   private static void sTest()
   {
      // create an array of sets of Stringss
      Set<String>[] aSets = (Set<String>[]) new Set[4];
      // assign set values to first two array elements
      String[] a1 = {"One", "two", "three"};
      String[] a2 = {"two", "four", "six", "eight"};
      String[] a3, a4;
      aSets[0] = new Set(a1);
      aSets[1] = new Set(a2);
      // perform set operations on thd sets
      aSets[2] = aSets[0].union(aSets[1]);
      aSets[3] = aSets[0].intersection(aSets[1]);
      for (int i = 0; i < 4; i++)
         System.out.println(aSets[i]);
      Set<Set<String>>[] aSetsOfSets = (Set<Set<String>>[]) new Set[4];
      for (int i = 0; i < 4; i++)
         aSetsOfSets[i] = new Set<Set<String>>();
      aSetsOfSets[0].insert(aSets[0]);
      aSetsOfSets[0].insert(aSets[1]);
      aSetsOfSets[0].insert(aSets[2]);
      aSetsOfSets[1].insert(aSets[2]);
      aSetsOfSets[1].insert(aSets[3]);
      aSetsOfSets[2] = aSetsOfSets[0].union(aSetsOfSets[1]);
      aSetsOfSets[3] = aSetsOfSets[0].intersection(aSetsOfSets[1]);
      for (int i = 0; i < 4; i++)
         System.out.println(aSetsOfSets[i]);
   }
   
   private static void opTest()
   {
      Set<Integer>[] setArray = (Set<Integer>[]) new Set[4];
      Integer[] i0 = {1, 2, 3, 4, 5};
      Integer[] i1 = {1, 3, 5};
      Integer[] i2 = {1, 2, 3};
      setArray[0] = new Set(i0);
      setArray[1] = new Set(i1);
      setArray[2] = new Set(i2);
      setArray[3] = setArray[0].copy();
      
      System.out.print(2 + " element of " + setArray[0]
                       + " " + setArray[0].elementOf(2));
      if (setArray[0].elementOf(2))
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
      System.out.print(2 + " element of " + setArray[1]
                       + " " + setArray[1].elementOf(2));
      if (!setArray[1].elementOf(2))
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
      System.out.print(setArray[0] + " subset of " + setArray[1]
                       + " " + setArray[0].subsetOf(setArray[1]));
      if (!setArray[0].subsetOf(setArray[1]))
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
      System.out.print(setArray[1] + " subset of " + setArray[0]
                       + " " + setArray[1].subsetOf(setArray[0]));
      if (setArray[1].subsetOf(setArray[0]))
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
      System.out.print(setArray[3] + " equals " + setArray[0]
                       + " " + setArray[3].subsetOf(setArray[0]));
      if (setArray[3].compareTo(setArray[0]) == 0)
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
      System.out.print(setArray[3] + " precedes " + setArray[0]
                       + " " + (setArray[3].compareTo(setArray[0]) < 0));
      if (setArray[3].compareTo(setArray[0]) < 0)
         System.out.println(":  fail");
      else
         System.out.println(":  ok");
      
      System.out.print(setArray[1] + " precedes " + setArray[0]
                       + " " + (setArray[1].compareTo(setArray[0]) < 0));
      if (setArray[1].compareTo(setArray[0]) < 0)
         System.out.println(":  fail");
      else
         System.out.println(":  ok");
      
      System.out.print(setArray[2] + " precedes " + setArray[0]
                       + " " + (setArray[2].compareTo(setArray[0]) < 0));
      if (setArray[2].compareTo(setArray[0]) < 0)
         System.out.println(":  ok");
      else
         System.out.println(":  fail");
      
   }

}

