package dev.fanie.funwithflagscompiler.entity;

import java.util.Arrays;
import java.util.Objects;

public class Property {
    private final String mType;
    private final String mName;
    private final String[] mFlags;
    private final String mFallback;

    public Property(String type, String name, String[] flags, String fallback) {
        mType = type;
        mName = name;
        mFlags = flags;
        mFallback = fallback;
    }

    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String[] getFlags() {
        return mFlags;
    }

    public String getFallback() {
        return mFallback;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Property)) {
            return false;
        }

        final Property that = (Property) object;
        return Objects.equals(this.mType, that.mType) && Objects.equals(this.mName, that.mName) &&
                Arrays.equals(this.mFlags, that.mFlags) && Objects.equals(this.mFallback, that.mFallback);
    }
}
