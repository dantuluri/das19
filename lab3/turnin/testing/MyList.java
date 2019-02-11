public class MyList<E> 
{
    private int size = 10;
    private Object elements[];

    public MyList() {
        elements = new Object[10];
    }

    public void add(E e) {
        if (size == elements.length) {
            ensureCapa();
        }
        elements[size++] = e;
    }


    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

}