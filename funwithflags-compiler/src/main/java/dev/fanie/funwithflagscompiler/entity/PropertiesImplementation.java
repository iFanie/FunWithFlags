package dev.fanie.funwithflagscompiler.entity;

import java.util.Locale;

public class PropertiesImplementation {
    public String getQualifiedName() {
        return null;
    }

    public String getSourceCode() {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PropertiesImplementation)) {
            return false;
        }

        final PropertiesImplementation that = (PropertiesImplementation) object;

        return true;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.ENGLISH,
                "PropertiesImplementation()"
        );
    }
}
