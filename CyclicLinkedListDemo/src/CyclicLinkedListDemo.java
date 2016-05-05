import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

class Node {
	int data;
	Node next;
	
	Node(int value){
		data = value;
		next = null;
	}
	Node(){
		
	}
}

class LinkedList {
	Node head;
	Node nonRecursivePointer;
	public static int counter;
	public static boolean found;
	public static int number;

	public void insertAtLast(int value) {
		Node toBeAttached = new Node();
		toBeAttached.data = value;
		toBeAttached.next = null;
		if (head == null) {
			head = toBeAttached;
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = toBeAttached;
		}
	}

	public void printList() {
		if (head == null) {
			System.out.println("No Elements in the List");
		} else {
			Node temp = head;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}

		}
	}

	public int makeCycle(int position) {
		System.out.println("Cycle creating...");
		Node temp = head;
		int currentPosition = 0;
		Node nodeToBeConnected = null;
		while (temp.next != null) {
			currentPosition++;
			if (currentPosition == position) {
				nodeToBeConnected = temp;
			}
			temp = temp.next;
		}
		currentPosition++;
		if (currentPosition == position) {
			nodeToBeConnected = temp;
		}
		if (nodeToBeConnected != null) {
			temp.next = nodeToBeConnected;
			nodeToBeConnected = null;
			System.out.println("Cycle created at "+position);
			return 1;
		} else {
			System.out.println("Invalid poisition");
		}
		return 0;
	}

	public void printListUsingRecusrion(Node current) {
		if (current.next == null) {
			System.out.print(current.data + " ");
		} else {
			printListUsingRecusrion(current.next);
			System.out.print(current.data + " ");
		}
	}

	public boolean searchElement(int n) {
		Node temp = head;
		int position = 0;
		while (temp != null) {
			position++;
			if (temp.data == n) {
				System.out.println("Element found at " + position);
				return true;
			}
			temp = temp.next;
		}
		System.out.println("No such element found");
		return false;
	}

	public boolean searchElementAtPosition(int n) {
		Node temp = head;
		int position = 0;
		while (temp != null) {
			position++;
			if (position == n) {
				System.out.println("Element found at " + position + ": " + temp.data);
				return true;
			}
			temp = temp.next;
		}
		System.out.println("No  element found at that position");
		return false;
	}

	public void checkPalindromeUsingStack() {
		Stack<Integer> stack = new Stack<Integer>();
		Node temp = head;
		while (temp != null) {
			stack.push(temp.data);
			temp = temp.next;
		}
		temp = head;
		while (temp != null) {
			if (stack.pop() != temp.data) {
				System.out.println("Not palindrome");
				return;
			}
			temp = temp.next;
		}
		if (head != null && temp == null) {
			System.out.println("Number is palindrome");
		}
	}

	public void removeElementAtposition(int position) {
		Node temp = head;
		Node toBeFree = null;
		int count = 0;
		if (position == 1) {
			head = temp.next;
			temp.next = null;
		}
		while (temp != null && temp.next != null) {
			count++;
			if (count == position - 1) {
				// position -1 because we have to do
				// operation in that node only
				toBeFree = temp.next;
				temp.next = toBeFree.next;
				toBeFree.next = null;
			}
			temp = temp.next;
		}
		if (count != position - 1) {
			System.out.println("Invalid position");
		}
	}

	public boolean checkPalindromeUsingRecursion(Node head) {
		Node current = head;
		if (current.next == null) {
			nonRecursivePointer = this.head;
			if (nonRecursivePointer.data != current.data) {
				nonRecursivePointer = nonRecursivePointer.next;
				return false;
			} else {
				nonRecursivePointer = nonRecursivePointer.next;
				return true;
			}

		} else {
			if (checkPalindromeUsingRecursion(current.next)) {
				if (nonRecursivePointer.data != current.data) {
					return false;
				} else {
					nonRecursivePointer = nonRecursivePointer.next;
					return true;
				}
			} else {
				return false;
			}

		}
	}

	public void searchUsingRecursion(Node current, int value) {
		if (current != null) {
			counter++;
		}
		if (current.next == null) {

			if (current.data == value) {
				System.out.println("Element found at " + counter);
				found = true;

			}
			counter--;
		} else {
			searchUsingRecursion(current.next, value);
			if (current.data == value) {
				System.out.println("Element found at " + counter);
				found = true;
			}
			counter--;
		}
		if (found == false && current == this.head) {
			System.out.println("No such Element found in the list");
		}

	}

	public void digitForm() {
		Node temp = head;
		int number = 0;
		while (temp != null) {
			number = number * 10 + temp.data;
			temp = temp.next;
		}
		System.out.println("The number is " + number);
	}

	public void digitFormUsingRecursion(Node current) {
		if (current.next == null) {
			number = (current.data) + number * 10;
			System.out.println(number);
		} else {
			number = (current.data) + number * 10;
			digitFormUsingRecursion(current.next);
		}
	}

	public void insertInSortedList(int value, LinkedList list) {
		list.bubbleSort();
		Node prev = head;
		Node temp = head;
		Node toBeAttached = new Node();
		toBeAttached.data = value;
		while (temp != null) {
			if (temp.data < value) {
				prev = temp;
				temp = temp.next;
			} else {
				toBeAttached.next = temp;
				prev.next = toBeAttached;
				break;
			}
		}
		if (temp == null) {
			toBeAttached.next = temp;
			prev.next = toBeAttached;
		}
		list.printList();
	}

	public void bubbleSort() {
		Node finalSortedNode = null;
		Node temp1 = head;
		Node temp2 = head;
		int store = 0;
		while (finalSortedNode != head) {
			// compare till the last sorted Element, decrease the node position
			// by 1 everytime itteration is complete
			if (temp2 != null && temp2.next != finalSortedNode) {
				temp2 = temp1.next;
				if (temp1.data > temp2.data) {
					store = temp2.data;
					temp2.data = temp1.data;
					temp1.data = store;
				}
				temp1 = temp1.next;
			} else if (temp2.next == finalSortedNode) {
				finalSortedNode = temp2;
				temp1 = head;
				temp2 = head;
			}
		}
	}

	public void selectionSort() {
		Node temp1 = head;
		Node temp2 = head;
		int store = 0;
		// move second pointer till last element
		while (temp1 != null && temp2 != null && temp1.next != null) {
			// second pointer will point to next element before comparision
			temp2 = temp2.next;
			if (temp2.data < temp1.data) {
				// swap
				store = temp2.data;
				temp2.data = temp1.data;
				temp1.data = store;
			}
			// if second pointer is the last element then start from beginning
			// by increasing first pointer by 1 and second pointer from pointer
			// one
			if (temp2.next == null) {
				temp1 = temp1.next;
				temp2 = temp1;
			}
		}
	}

	public void insertionSort() {
		Node current = head;
		Node nextNode = head;
		while (current.next != null) {
			nextNode = current.next;
			if (nextNode.data < current.data) {
				insertInSorted(nextNode, current);
			} else {
				current = current.next;
			}
		}
		printList();
	}

	public void insertInSorted(Node nodeToBeInserted, Node current) {
		Node prev = null;
		while (prev != current) {
			if (prev == null) {
				prev = head;
			}
			if (nodeToBeInserted.data <= head.data) {
				// insert at head or in place of prev

				current.next = nodeToBeInserted.next;
				nodeToBeInserted.next = head;
				head = nodeToBeInserted;
				prev = head;
				break;
			} else if (nodeToBeInserted.data > prev.data && nodeToBeInserted.data <= prev.next.data) {
				// insert just after prev and before prev.next
				current.next = nodeToBeInserted.next;
				nodeToBeInserted.next = prev.next;
				prev.next = nodeToBeInserted;
				break;
			} else if (nodeToBeInserted.data > prev.data && nodeToBeInserted.data > prev.next.data) {
				// increase prev
				prev = prev.next;
			}
		}

	}

	public void pairWiseSwap() {
		Node current = head;
		Node prev = null;
		while (current != null && current.next != null) {
			if (prev == null) {
				head = current.next;
				current.next = current.next.next;
				head.next = current;
				prev = current;
				current = current.next;
			} else {
				prev.next = current.next;
				current.next = current.next.next;
				prev.next.next = current;
				prev = current;
				current = current.next;
			}
		}
		printList();
	}

	public void storeListInArrayIteration() {
		Node temp = head;
		int size = 0;
		int i = 0;
		// calculate size
		while (temp != null) {
			size++;
			temp = temp.next;
		}
		int arr[] = new int[size];
		temp = head;
		while (temp != null) {
			arr[i] = temp.data;
			i++;
			temp = temp.next;
		}
		for (int j = 0; j < size; j++) {
			System.out.print("{" + arr[j] + "}");
		}
		System.out.println("");
		System.out.println("Array Length: " + arr.length);
	}

	public int storeListInArrayUsingRecursion() {
		Node temp = head;
		int i = 0;
		int size = sizeUsingRecursion(head);
		int arr[] = new int[size];
		while (temp != null) {
			arr[i] = temp.data;
			i++;
			temp = temp.next;
		}
		for (int j = 0; j < size; j++) {
			System.out.print("{" + arr[j] + "}");
		}
		return 0;
	}

	public int sizeUsingRecursion(Node current) {
		if (current == null) {
			return 0;
		} else {
			return 1 + sizeUsingRecursion(current.next);
		}
	}
	
	public void printMiddleElementList(){
		Node slowPtr = head;
		Node fastPtr = head;
		while(fastPtr != null && fastPtr.next != null && fastPtr.next.next != null){
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
		}
		System.out.println("Middle Element " +slowPtr.data);
	}
	
	public void nthNodeFromLast(int n){
		Node firstPtr = head;
		Node secondPtr = head;
		int count = 1;
		if(head != null){
			while(count != n){
				secondPtr = secondPtr.next;
				count++;
			}
		} else {
			System.out.println("No Element in the list");
		}
		while(secondPtr.next != null){
			firstPtr = firstPtr.next;
			secondPtr = secondPtr.next;
		}
		System.out.println(n+"th Element in the list from last is "+firstPtr.data);
	}
	
	
	public void countOccurence(){
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		Node temp = head;
		int count;
		
		if(head != null){
		 while(temp != null){
			 if(map.get(temp.data) == null){
				 count = 1;
			 } else {
				 count = map.get(temp.data) + 1;
			 }
			 map.put(temp.data, count);
			 temp = temp.next;
		 }
		}
		if(temp == null){
			for(int keyset: map.keySet()){
				System.out.println("Number of time "+keyset+" is in the list : "+map.get(keyset));
			}
		}
	}
	
	public void detectLoop(){
		Node slowPtr = head, fastPtr = head;
		while(fastPtr != null && fastPtr.next != null && fastPtr.next.next != null){
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if(slowPtr == fastPtr){
				System.out.println("Loop detected");
				break;
			}
		}
		if(fastPtr == null || fastPtr.next == null || fastPtr.next.next == null){
		System.out.println("No Loop found in the given list");	
		}
	}
	
	public int poistionOfLoop(){
		Node slowPtr = head, fastPtr = head;
		int count = 0;
		if(head != null){
			if(fastPtr != null && fastPtr.next != null && fastPtr.next.next != null){
		      fastPtr = fastPtr.next.next;
		      slowPtr = slowPtr.next;
			}
			while(slowPtr != fastPtr){
			     if(fastPtr == null || fastPtr.next == null || fastPtr.next.next == null){
			    	 System.out.println("No loop");
			    	 break;
			     } else {
			    	 fastPtr = fastPtr.next.next;
				     slowPtr = slowPtr.next;
			     }
			     
			}
			if(slowPtr == fastPtr){
				
				fastPtr = head;
				while(slowPtr != fastPtr){
					count++;
					slowPtr = slowPtr.next;
					fastPtr = fastPtr.next;
				}
				// once sloptr == fastptr we need to increase the counter as it will not go in the while loop
				count++;
			}
			if(count != 0){
				System.out.println("Loop detected at position : "+count);
			}
		}
		return count;
	}
	
	public void breakLoop(){
		int positionOfLoop = poistionOfLoop();
		int count = 0;
		Node temp = head;
		Node loopedNode = null;
		if(head != null && positionOfLoop != 0){
			while(temp.next != loopedNode){
				count++;
				if(count == positionOfLoop){
					loopedNode = temp;
				}
				temp = temp.next;
			}
			temp.next = null;			
		}
		System.out.println("No more cyclic list");
	}
	
	public void intersectionPoint(){
		LinkedList list1 = new LinkedList(); 
		LinkedList list2 = new LinkedList();
		list1.head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		
		list2.head = new Node(10);
		list2.head.next = new Node(20);
		list2.head.next.next = new Node(30);
		list2.head.next.next.next = list1.head;
		
		
		int listSize1 = list1.sizeUsingRecursion(list1.head); 
		int listSize2 = list2.sizeUsingRecursion(list2.head);
		int diff = listSize1 - listSize2;
		Node temp = null;
		int counter = 0;
		if(diff > 0){
			Node temp2 = list2.head;
			temp = list1.head;
			while(counter != diff){
				temp = temp.next;
				counter++;
			}
			while(temp != temp2 || temp != null || temp2 != null){
				counter++;
				temp = temp.next;
				temp2 = temp2.next;
			}
		//	if(temp2 != null || temp != null){
				System.out.println("The intersection point is : "+temp.data+" at position "+counter);
			//} else {
			//	System.out.println("No intersection");
			//}
			
		} else {
			Node temp1 = list1.head;
			temp = list2.head;
			while(counter != -diff){
				System.out.println(temp.data);
				temp = temp.next;
				counter++;
			}
			counter++;
			while(temp != temp1){
				counter++;
				temp = temp.next;
				temp1 = temp1.next;
			}
			if(temp1 != null || temp != null){
				System.out.println("The intersection point is : "+temp1.data+" at position "+counter);
			} else {
				System.out.println("No intersection");
			}
		}
	}
	
	public void removeDuplicates(){
		bubbleSort();
		Node freeNode = null;
		Node temp = head;
		if(temp != null){
			while(temp.next != null){
				if(temp.data == temp.next.data){
					freeNode = temp.next;
					temp.next = freeNode.next;
					freeNode.next = null;
				} else {
					temp = temp.next;
				}
			}
		} else {
			System.out.println("List is empty");
		}
	}
	
	public void removeDuplicatesWithoutSorting(){
		
	}

}

public class CyclicLinkedListDemo {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		System.out.println("Enter the elements to enter in the list, write 'exit' to exit");
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String str = scanner.next();
			if (str.equals("exit")) {
				break;
			} else {
				int value = Integer.parseInt(str);
				list.insertAtLast(value);
			}

		}
		System.out.println("Press 1: To print List");
		System.out.println("Press 2: Press 2 to make it Cyclic");
		System.out.println("Press 3: Press 3 to printReverse List using recursion");
		System.out.println("Press 4: Press 4 to search Element");
		System.out.println("Press 5: Press 5 to search Element at position");
		System.out.println("Press 6: Press 6 to check the list is palindrome or not using Stack");
		System.out.println("Press 7: Press 7 to check the list is palindrome or not using Recursion");
		System.out.println("Press 8: Press 8 to remove Element");
		System.out.println("Press 9: Press 9 to search Element using Recursion");
		System.out.println("Press 10: Press 10 for Bubble Sort");
		System.out.println("Press 11: Press 11 for Selection Sort");
		System.out.println("Press 12: Press 12 for Insertion Sort");
		System.out.println("Press 13: Press 13 for Digit form");
		System.out.println("Press 14: Press 14 for Digit formation using recursion");
		System.out.println("Press 15: Press 15 to insert in SortedList");
		System.out.println("Press 16: Press 16 to do Swap Pairwise");
		System.out.println("Press 17: Press 17 to store data in array");
		System.out.println("Press 18: Press 18 to store data in array using recursion");
		System.out.println("Press 19: Press 19 to print middle element");
		System.out.println("Press 20: Press 20 to print random nth element from last");
		System.out.println("Press 21: Press 21 to print the total occurance of each character in list");
		System.out.println("Press 22: Press 22 to detect the loop");
		System.out.println("Press 23: Press 23 to get the position of the loop");
		System.out.println("Press 24: Press 24 to break the loop");
		System.out.println("Press 25: Press 25 to check intersection of another linkedlist starting from 10,20,30, list1");
		System.out.println("Press 26: Press 26 to remove duplicates from the list");
		
		
		
		while (true) {
			String str = scanner.next();
			int position;
			if (str.equals("exit")) {
				break;
			} else {
				int value = Integer.parseInt(str);
				switch (value) {
				case 1:
					list.printList();
					break;

				case 2:
					System.out.println("Enter the position number where end node will attach");
					str = scanner.next();
					position = Integer.parseInt(str);
					list.makeCycle(position);
					break;

				case 3:
					list.printListUsingRecusrion(list.head);
					break;

				case 4:
					System.out.println("Enter the Element to search");
					int elementToBeSearched = scanner.nextInt();
					list.searchElement(elementToBeSearched);

					break;

				case 5:
					System.out.println("Enter the position to search");
					int positionToBeSearched = scanner.nextInt();
					list.searchElementAtPosition(positionToBeSearched);

					break;

				case 6:
					list.checkPalindromeUsingStack();
					break;

				case 7:
					if (list.checkPalindromeUsingRecursion(list.head)) {
						System.out.println("Number is Palindrome");
					} else {
						System.out.println("Number is not Palindrome");
					}
					break;

				case 8:
					System.out.println("Enter the position in the list to be removed");
					int positionToBeRemoved = scanner.nextInt();
					list.removeElementAtposition(positionToBeRemoved);

					break;

				case 9:
					System.out.println("Enter the Element to search");
					elementToBeSearched = scanner.nextInt();
					list.searchUsingRecursion(list.head, elementToBeSearched);

					break;

				case 10:
					list.bubbleSort();
					list.printList();

					break;

				case 11:
					list.selectionSort();
					list.printList();

					break;

				case 12:
					list.insertionSort();

					break;

				case 13:
					list.digitForm();

					break;

				case 14:
					list.digitFormUsingRecursion(list.head);

					break;

				case 15:
					Random ran = new Random();
					int number = ran.nextInt(10);
					list.insertInSortedList(6, list);
					// list.printList();
					break;

				case 16:
					list.pairWiseSwap();
					break;

				case 17:
					list.storeListInArrayIteration();
					break;

				case 18:
					list.storeListInArrayUsingRecursion();
					break;
					
				case 19:
					list.printMiddleElementList();
					break;
					
				case 20:
					ran = new Random();
					number = ran.nextInt(5);
					list.nthNodeFromLast(number);
					break;
					
				case 21:
					list.countOccurence();
					break;
					
				case 22:
					list.detectLoop();
					break;
					
				case 23:
					list.poistionOfLoop();
					break;
					
				case 24:
					list.breakLoop();
					break;
					
				case 25:
					list.intersectionPoint();
					break;
					
				case 26:
					list.removeDuplicates();
					System.out.println("The new List is :");
					list.printList();
					break;
					
				default:
					break;
				}

			}

		}

	}

}
