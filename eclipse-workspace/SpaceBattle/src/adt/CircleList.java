package adt;

public class CircleList<T> {
	private DoubleNode<T> head;
	private int size;
	
	public CircleList() {
		head = null;
		size = 0;
	}
	
	public void add(T value) {
		DoubleNode<T> newNode = new DoubleNode<T>();
		newNode.setValue(value);
		if(head == null) {
			head = newNode;
			size++;
		} else {
		newNode.setPrev(head.getPrev());
		newNode.setNext(head);
		head.getPrev().setNext(newNode);
		head.setPrev(newNode);
		size++;
		}
	}
	
//	public void remove(int index) {
//		if(index == 0 && index < size) {
//			head.getNext().setPrev(head.getPrev());
//			head.getPrev().setNext(head.getNext());
//			head = head.getNext();
//			size--;
//		} else if(index <= size/2) {
//			DoubleNode<T> current = head;
//			for(int c = 0; c != index; c++) {
//				current = current.getNext();
//			}
//			
//		}
//	}
}
