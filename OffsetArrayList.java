package h06;

import java.util.Arrays;

public class OffsetArrayList<E> {

	//Instance Variables
	E[] array;
	int size = 0;
	int offset;

	//Constructor
	public OffsetArrayList(int initialCapacity, int offset) {

		this.offset = offset;

		if (initialCapacity < 0) {
			throw new NegativeArraySizeException ("Error, Array size cannot be negative");
		}

		array = (E[]) new Object[initialCapacity]; 

	}

	//Size: Return # of elements
	int size() {
		return size; 
	}

	//Capacity: returns the size of underlying array
	int capactity() {
		return array.length;
	}

	//Offset: returns offset of first element
	int offset() {
		return offset;
	}

	//Get: Returns object at the given index
	E get (int index) {

		//Make sure index is within bounds of array
		if (index >= offset + size ||  index < offset) {
			throw new IndexOutOfBoundsException();
		}

		//return element at specified index
		return array[index-offset];
	}

	//Set: returns the old value
	E set(int index, E element) {

		//Check if index is valid
		if (index < offset || index >= offset+size) {
			throw new IndexOutOfBoundsException();
		}

		E temp = array[index - offset];
		array[index - offset] = element;

		return temp;

	}

	//Add, add at the end of the list. Always return true;
	boolean add(E element) {

		//Check if array capacity is full
		if (size == array.length) {
			array = Arrays.copyOf(array, (size*2)+1);
		}

		array[size] = element;
		size++;
		return true;
	}

	//Add at index... method thing... add in element and shift the rest to the right one 
	void add(int index, E element) {

		//Check if array capacity is full
		if (size == array.length) {
			array = Arrays.copyOf(array, (size*2)+1);
		}

		//Check if index is valid
		if (index < offset || index >= offset + size + 1) {
			throw new IndexOutOfBoundsException();
		}

		for(int i = size; i > (index-offset); i--) {
			array[i] = array[i-1];


		}
		array[index - offset] = element;

		size++;

	}

	// removes object at index
	E remove(int index) {

		if (index < offset || index >= offset+size) {
			throw new IndexOutOfBoundsException();
		}

		E temp = array[index-offset];
		array[index - offset] = null;

		//Shift array to left after index
		for(int i = index-offset; i < size-1; i++) {
			array[i] = array[i+1];
		}

		size--;
		return temp;

	}

	//toString: concatenate all the elements' toString() with "," as a separator
	@Override
	public String toString() {

		String seperator = "";
		String result = "";
		for(int i = 0; i < size; i++) {
			result = result + seperator + array[i];
			seperator = ", ";
		}
		return result;

	}

	public static boolean unitTest() { 
		boolean allTestsPassed = true;

		//Test Constructor
		try {
			OffsetArrayList<Integer> negativeCapacityArray = new OffsetArrayList<>(-5, 0);
			allTestsPassed = false; //this part should not come up
		} catch(NegativeArraySizeException e) {/*expected to catch this exception for negative */}


		//Outer Loop that sets the stage
		for (int i = -5; i < 11; i++) {

			OffsetArrayList<Integer> unitTestArray = new OffsetArrayList<>(10,i);

			//Test get (invalid)
			try {
				unitTestArray.get(i-1);
				allTestsPassed = false;
			} catch(IndexOutOfBoundsException e) {/* if the exception is thrown as expected before the boolean is changed, it'll be true*/}

			//Test set (invalid index)
			try {
				unitTestArray.set(i-1, 0);
				allTestsPassed = false;
			} catch (IndexOutOfBoundsException e) {}

			//Test for add
			try {
				unitTestArray.add(i - 1, 0);
				allTestsPassed = false;
			} catch (IndexOutOfBoundsException e) {}

			//Test for remove
			try {
				unitTestArray.remove(i - 1);
				allTestsPassed = false;
			} catch (IndexOutOfBoundsException e) {}
		}

		//Test toString
		for (int testCapacity = 0; testCapacity < 4; testCapacity++) {
			for(int testOffset = -1; testOffset < 3; testOffset ++) {
				OffsetArrayList<Integer> toStringTest = new OffsetArrayList<>(testCapacity,testOffset);
				String expectedString = "";
				String testSeperator = "";


				for(int fill = 0; fill <= testCapacity; fill++) {
					toStringTest.add(fill);
				}

				for (int i = 0; i < toStringTest.size(); i++ ) {
					expectedString = expectedString + testSeperator + toStringTest.get(i + testOffset);
					testSeperator = ", ";
				}

				String actualString = toStringTest.toString();
				if (!expectedString.equals(actualString)) {
					allTestsPassed = false;
				}

			}

		}


		return allTestsPassed;
	} 

	public static void main(String[] args) {

		OffsetArrayList<Integer> test = new OffsetArrayList<>(5, 0); 
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.remove(1);

		for(int i = 0; i < 4; i++) {
			System.out.println(test.get(i));
		}

		String toStringTest = test.toString();
		System.out.println(toStringTest);

		System.out.println(OffsetArrayList.unitTest());


	}


}