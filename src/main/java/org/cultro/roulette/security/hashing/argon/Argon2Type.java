package org.cultro.roulette.security.hashing.argon;

public enum Argon2Type {

    ARGON2_d(0), ARGON2_i(1), ARGON2_di(2);

    private final int type;

    Argon2Type(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
