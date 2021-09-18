package com.deltav;

/**
 * The type Generic inter class without type.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author DeltaV235
 * @version 1.0
 */
public class GenericInterClassWithoutType<K, V> implements GenericInterface<K, V> {

    @Override
    public K print(V v) {
        return (K) v.getClass();
    }
}
