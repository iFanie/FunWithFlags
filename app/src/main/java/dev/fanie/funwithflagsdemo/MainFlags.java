package dev.fanie.funwithflagsdemo;

import dev.fanie.funwithflags.FunWithFlags;

public final class MainFlags {
    private MainFlags() {}

    @FunWithFlags.Flag(id = 1L)
    public static final boolean IS_FEATURE_LIVE = true;

    @FunWithFlags.Flag(id = 2L)
    public static final boolean IS_FEATURE_ACCESSIBLE = false;
}
