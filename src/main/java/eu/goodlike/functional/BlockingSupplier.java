package eu.goodlike.functional;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * Supplier implementation, that can be set once by an arbitrary Thread and will be available to all threads from that
 * point on
 */
public final class BlockingSupplier<T> implements Supplier<T> {

    /**
     * Sets the value of this supplier by using a different supplier
     * @throws NullPointerException if supplier is null
     * @throws IllegalStateException if this supplier has already been set
     */
    public synchronized void set(Supplier<T> supplier) {
        if (supplier == null)
            throw new NullPointerException("Supplier cannot be null");

        set(supplier.get());
    }

    /**
     * Sets the value of this supplier
     * @throws IllegalStateException if this supplier has already been set
     */
    public synchronized void set(T value) {
        if (this.value != null)
            throw new IllegalStateException("Value can be set only once!");

        this.value = value;
        blockingGate.countDown();
    }

    /**
     * @return the value held by this supplier, blocking until some other Thread sets it
     * @throws IllegalStateException if the waiting was interrupted
     */
    public T get() {
        try {
            blockingGate.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Blocking supplier was interrupted!", e);
        }
        return value;
    }

    // PRIVATE

    private T value;
    private final CountDownLatch blockingGate = new CountDownLatch(1);

}