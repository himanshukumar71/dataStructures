import java.util.Scanner;
import java.util.Stack;

class Node {
	int data;
	Node next;
}

class LinkedList {
	Node head;
	Node nonRecursivePointer;
	public static int counter;
	public static boolean found;

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
	
	public void searchUsingRecursion(Node current, int value){
		if(current != null){
			counter++;
		}
		if(current.next == null){
			
			if(current.data == value){
				System.out.println("Element found at "+counter);
				found = true;
				
			} 
			counter--;
		} else {
			searchUsingRecursion(current.next, value);
			if(current.data == value){
				System.out.println("Element found at "+counter);
				found = true;
			}
			counter--;
		}
		if(found == false && current == this.head){
			System.out.println("No such Element found in the list");
		}
		
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
					System.out.println(list.makeCycle(position));
					str = scanner.next();
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

				}

			}

		}

	}

}
