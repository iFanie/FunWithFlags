package dev.fanie.funwithflagsdemo;

import dev.fanie.funwithflags.FunWithFlags;

@FunWithFlags.Properties
public interface MainProperties {

    @FunWithFlags.Property(ids = {1L, 2L})
    boolean isEnabled();
}
