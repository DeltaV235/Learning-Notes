package com.deltav;

/**
 * The interface Generic interface.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author DeltaV235
 * @version 1.0
 */
public interface GenericInterface<K, V> {
    /**
     * Print k.
     *
     * @param v the v
     * @return the k
     */
    K print(V v);
}
