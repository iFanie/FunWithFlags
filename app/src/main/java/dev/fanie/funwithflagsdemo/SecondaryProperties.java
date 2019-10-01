package dev.fanie.funwithflagsdemo;

import dev.fanie.funwithflags.FunWithFlags;

@FunWithFlags.Properties(isSingleton = true)
public interface SecondaryProperties {

    @FunWithFlags.Property(ids = {12L, -1L})
    boolean isOpen();
}
