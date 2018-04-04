package adt;

public class List <T> {
	private Node<T> head;
	private int size;
	
	public List() {
		head = null;
		size = 0;
	}

	public void add(T value) {
		Node<T> newNode = new Node<T>();
		newNode.setValue(value);
		if(head == null) {
			head = newNode;
			size++;
			return;
		}
		Node<T> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newNode);
		size++;
	}
	
	public void remove(int index) {
		if(index == 0 && index < size) {
			head = head.getNext();
			size--;
			return;
		}
		Node<T> current = head;
		int counter = 0;
		while(counter < index-1 && current.getNext() != null) {
			current = current.getNext();
			counter++;
		}
		if(counter == size-2) {
			current.setNext(null);
			size--;
			return;
		} else {
			current.setNext(current.getNext().getNext());
			size--;
			return;
		}
	}
	
	public T get(int index) {
		if(index > size-1)
			return null;
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getValue();
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		this.head = null;
		this.size = 0;
	}
	
	public void swap(int oldIndex, int newIndex) {
		T d1 = this.get(oldIndex);
		T d2 = this.get(newIndex);
		Node<T> current = head;
		for(int c = 0; c < this.size(); c++) {
			if(this.get(c) == d1) {
				current.setValue(d2);
			} else if(this.get(c) == d2) {
				current.setValue(d1);
			}
			current = current.getNext();
		}
	}
}