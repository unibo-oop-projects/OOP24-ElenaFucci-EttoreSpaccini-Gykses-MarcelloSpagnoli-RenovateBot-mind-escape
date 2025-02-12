package mindescape.model.world.core.api;

/**
 * A generic class representing a pair of values.
 * <p>
 * This class is immutable, meaning that once an instance is created,
 * its values cannot be changed. It provides basic methods for accessing
 * the values, as well as implementations of {@code equals}, {@code hashCode},
 * and {@code toString} for easy comparison and representation.
 * </p>
 *
 * @param <X> the type of the first element in the pair
 * @param <Y> the type of the second element in the pair
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructs a new {@code Pair} with the specified values.
     *
     * @param x the first element of the pair
     * @param y the second element of the pair
     */
    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the first element of the pair.
     *
     * @return the first element of type {@code X}
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the second element of the pair.
     *
     * @return the second element of type {@code Y}
     */
    public Y getY() {
        return y;
    }

    /**
     * Computes the hash code for this pair based on its elements.
     *
     * @return the hash code value for this pair
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * Compares this pair to the specified object for equality.
     * Two pairs are considered equal if both their elements are equal.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)){
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of the pair.
     *
     * @return a string in the format {@code Pair [x=value1, y=value2]}
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
