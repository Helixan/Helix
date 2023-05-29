package org.cultro.roulette.util;

import org.cultro.roulette.lang.Validate;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Cooldown<T> {

    private final HashMap<T, Long> cooldownMap = new HashMap<>();
    private final long cooldownTimeInMills;

    public Cooldown(long cooldownTimeInMills) {
        this.cooldownTimeInMills = cooldownTimeInMills;
    }

    public void addToCooldown(T object) {
        Validate.notNull(object, "A null object cannot be put on cooldown");
        cooldownMap.put(object, System.currentTimeMillis());
    }

    public long getMillsRemaining(T object) {
        Validate.notNull(object, "A null object cannot be on cooldown");
        long remaining = 0;
        if (cooldownMap.containsKey(object)) {
            remaining = cooldownTimeInMills - (System.currentTimeMillis() - cooldownMap.get(object));
            if (remaining <= 0) {
                cooldownMap.remove(object);
                remaining = 0;
            }
        }
        return remaining;
    }

    public boolean isCooldownOver(T object) {
        return getMillsRemaining(object) <= 0;
    }
}
