package JavaFX;

import java.io.Serializable;

public class EdgeDoubleWeighted implements Comparable<EdgeDoubleWeighted>, Serializable {

    private int v;
    private int w;
    private double stWeight;
    //private double ndWeight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  stWeight the first weight of this edge
     * @param  //ndWeight the second weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public EdgeDoubleWeighted(int v, int w, double stWeight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (Double.isNaN(stWeight)) throw new IllegalArgumentException("Weight is NaN");
        //if (Double.isNaN(ndWeight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.stWeight = stWeight;
        //this.ndWeight = ndWeight;
    }
    public int getV(){return v;}

    public int getW(){return w;}

    public double getStWeight() {
        return stWeight;
    }

   /* public double getNdWeight() {
        return ndWeight;
    }*/

    public void setV(int v) {this.v = v;}

    public void setW(int w) {this.w = w;}

    public void setStWeight(double stWeight) {this.stWeight = stWeight;}

   /* public void setNdWeight(double ndWeight) {
        this.ndWeight = ndWeight;
    }*/

    /**
     * Returns the weight of this edge.
     *
     * @return the first weight of this edge
     */
    public double stWeight() {
        return stWeight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the second weight of this edge
     */
   /* public double ndWeight() {
        return ndWeight;
    }*/

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited from {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(EdgeDoubleWeighted that) {
        return Double.compare(this.stWeight, that.stWeight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, stWeight);
    }


}