package utils;

/**
 * Created by ammon on 2/24/15.
 */
public class Weight {
    public Weight() {

    }

    public Weight(long dateInMilliseconds, float weight) {
        this.dateInMilliseconds = dateInMilliseconds;
        this.weight = weight;
    }

    public long dateInMilliseconds;
    public float weight;
}