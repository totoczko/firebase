package com.example.martyna.chmura;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Martyna on 2017-12-09.
 */

public class ItemID {
    private final static AtomicInteger c = new AtomicInteger(0);
    public static int getID() {
        return c.incrementAndGet();
    }
}
