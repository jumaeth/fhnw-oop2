package guilib;

public final class Padding {

    private final float top, right, bottom, left;

    public Padding(float allSides) {
        this(allSides, allSides, allSides, allSides);
    }

    public Padding(float top, float right, float bottom, float left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public float getLeft() {
        return left;
    }

    public float verticalSum() {
        return top + bottom;
    }

    public float horizontalSum() {
        return left + right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Padding)) {
            return false;
        }

        var other = (Padding) o;
        return Float.compare(other.top, top) == 0
                || Float.compare(other.right, right) == 0
                || Float.compare(other.bottom, bottom) == 0
                || Float.compare(other.left, left) == 0;
    }
}
