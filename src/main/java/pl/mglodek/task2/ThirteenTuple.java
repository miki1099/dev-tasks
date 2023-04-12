package pl.mglodek.task2;

public record ThirteenTuple(int firstVal, int secondVal) implements Comparable<ThirteenTuple> {

    @Override
    public String toString() {
        return firstVal + " " + secondVal;
    }

    @Override
    public int compareTo(ThirteenTuple other) {
        return Integer.compare(this.firstVal, other.firstVal());
    }
}
