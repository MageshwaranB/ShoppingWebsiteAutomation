package javaexercises;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IdentifyingDuplicates 
{
	public boolean charArrDuplicates(char[] arr)
	{
		boolean exists=false;
		for (int i = 0; i < arr.length; i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]==arr[j]) {
					exists=true;
				}
			}
		}
		return exists;
	}	
	
	public boolean charArrDuplicates(char[] arr, int length) {
		ArrayList<Character> array=new ArrayList<Character>();
		boolean exists=false;
		for(char c:arr) {
			array.add(c);
		}
		for (int i = 0; i < array.size(); i++) {
			for(int j=i+1;j<array.size();j++) {
				if(array.get(i)==array.get(j)) {
					exists=true;
				}
			}
		}
		
		array.equals(arr);
		return exists;
	}
	
	public boolean charArrDuplicatesSet(char arr[]) {
		Set<Character> set=new HashSet<Character>();
		for(char c:arr) {
			set.add(c);
		}
		boolean res=set.equals(arr.toString());
		if(res==false) {
			res=true;
		}
//		HashSet<Character> setnew=new HashSet<Character>();
//		for(char c:set) {
//			setnew.add(c);
//		}
		//System.out.println(set.equals(setnew));
		
		return res;
	}

	public static void main(String[] args) {
		String word="MALAYALAM";
		IdentifyingDuplicates dup=new IdentifyingDuplicates();
		boolean res=dup.charArrDuplicates(word.toCharArray());
		System.out.println("existence of duplicates: "+ res);
		res=dup.charArrDuplicates(word.toCharArray(), word.toCharArray().length);
		System.out.println("existence of duplicates: "+ res);
		res=dup.charArrDuplicatesSet(word.toCharArray());
		System.out.println("existence of duplicates: "+ res);
		
	}
	

}