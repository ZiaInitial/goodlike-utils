package eu.goodlike.neat.impl.nullcheck;

import eu.goodlike.neat.Null;

import java.util.Collection;

/**
 * Null implementation for collection check
 */
public final class CollectionNull extends Null {

    @Override
    public boolean containsNull() {
        return collection.contains(null);
    }

    @Override
    protected int indexOfNull() {
        int i = 0;
        for (Object element : collection) {
            if (element == null)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    protected String contentToString() {
        return collection.toString();
    }

    @Override
    protected String genericErrorFormat() {
        return "Cannot contain null: {}";
    }

    // CONSTRUCTORS

    public CollectionNull(Collection<?> collection) {
        this.collection = collection;
    }

    // PRIVATE

    private final Collection<?> collection;

}
