package generics;

public class MiniList<E> {
    private E first;
    private E second;
    private E third;

    public MiniList(E first, E second, E third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public E get(int index) {
        return switch (index) {
            case 0 -> first;
            case 1 -> second;
            case 2 -> third;
            default -> throw new IndexOutOfBoundsException(index);
        };
    }

    public void set(int index, E value) {
        switch (index) {
            case 0 -> first = value;
            case 1 -> second = value;
            case 2 -> third = value;
            default -> throw new IndexOutOfBoundsException(index);
        }
    }

    public int size() {
        return 3;
    }
}
