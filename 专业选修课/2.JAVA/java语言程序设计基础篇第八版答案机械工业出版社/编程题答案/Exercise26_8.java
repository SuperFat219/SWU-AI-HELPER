public class Exercise26_8 {
  public static void main(String[] args) {
    Exercise26_8 exercise25_7 = new Exercise26_8();
  }

	Exercise26_8() {
		MyArrayList list = new MyArrayList();

		list.add("Red");
		list.add("Yellow");
		list.add("Green");
		list.add("Blue");
		list.add("Pink");

		java.util.Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

  class MyArrayList extends MyAbstractList {
    public static final int INITIAL_CAPACITY = 16;
    private Object[] data = new Object[INITIAL_CAPACITY];

    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(Object[] objects) {
      data = objects;
      size = objects.length;
    }

    /** Add a new element o at the specified index in this list */
    public void add(int index, Object o) {
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--) {
        data[i + 1] = data[i];
      }

      // Insert new element to data[index]
      data[index] = o;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size */
    private void ensureCapacity() {
      if (size >= data.length) {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
      }
    }

    /** Clear the list */
    public void clear() {
      data = new Object[INITIAL_CAPACITY];
    }

    /** Return true if this list contains the element o */
    public boolean contains(Object o) {
      for (int i = 0; i < size; i++) {
        if (o.equals(data[i])) {
          return true;
        }
      }

      return false;
    }

    /** Return the element from this list at the specified index */
    public Object get(int index) {
      return data[index];
    }

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object o) {
      for (int i = 0; i < size; i++) {
        if (o.equals(data[i])) {
          return i;
        }
      }

      return -1;
    }

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(Object o) {
      for (int i = size - 1; i >= 0; i--) {
        if (o.equals(data[i])) {
          return i;
        }
      }

      return -1;
    }

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public Object remove(int index) {
      Object o = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++) {
        data[j] = data[j + 1];
      }

      // Decrement size
      size--;

      return o;
    }

    /** Replace the element at the specified position in this list
     *  with the specified element. */
    public Object set(int index, Object o) {
      Object old = data[index];
      data[index] = o;
      return old;
    }

    /** Override toString() to return elements in the list */
    public String toString() {
      StringBuffer result = new StringBuffer("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) {
          result.append(", ");
        }
      }

      return result.toString() + "]";
    }

	  public java.util.Iterator iterator() {
			return new MyArrayListIterator(this);
		}

	  class MyArrayListIterator implements java.util.Iterator {
		  private MyArrayList list = new MyArrayList();
			private int current = 0;
			private MyArrayList originalList;

			MyArrayListIterator(MyArrayList originalList) {
				this.originalList = originalList;
				for (int i = 0; i < originalList.size(); i++)
					list.add(originalList.get(i));
			}

      public boolean hasNext() {
        if (current < list.size())
				  return true;

        return false;
      }

      public Object next() {
        return list.get(current++);
      }

      public void remove() {
				list.remove(current);
				originalList.remove(current);
      }
    }
  }

	abstract class MyAbstractList implements MyList {
		protected int size = 0; // The size of the list

		/** Create a default list */
		protected MyAbstractList() {
		}

		/** Create a list from an array of objects */
		protected MyAbstractList(Object[] objects) {
			for (int i = 0; i < objects.length; i++)
				add(objects[i]);
		}

		/** Add a new element o at the end of this list */
		public void add(Object o) {
			add(size, o);
		}

		/** Return true if this list contains no elements */
		public boolean isEmpty() {
			return size == 0;
		}

		/** Return the number of elements in this list */
		public int size() {
			return size;
		}

		/** Remove the first occurrence of the element o from this list.
		 *  Shift any subsequent elements to the left.
		 *  Return true if the element is removed. */
		public boolean remove(Object o) {
			if (indexOf(o) >= 0) {
				remove(indexOf(o));
				return true;
			}
			else
				return false;
		}
	}
}
