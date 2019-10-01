package dev.fanie.funwithflagscompiler.entity;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class PropertiesImplementation {
    private final String mPackage;
    private final String mClassName;
    private final String mImplements;
    private final boolean mIsSingleton;
    private final Property[] mProperties;

    public PropertiesImplementation(String packageName, String className, String implementsName, boolean isSingleton, Property[] properties) {
        mPackage = packageName;
        mClassName = className;
        mImplements = implementsName;
        mIsSingleton = isSingleton;
        mProperties = properties;
    }

    public String getQualifiedName() {
        return mPackage + "." + mClassName;
    }

    public String getSourceCode() {
        return "package " + mPackage + ";\n\n" +
                "public class " + mClassName + " implements " + mImplements + " {\n" +
                printConstructor() + "\n" +
                printProperties() + "\n" +
                "}\n";
    }

    private String printConstructor() {
        if (mIsSingleton) {
            return "\tprivate " + mClassName + "() {}\n" +
                    "\tpublic static final " + mClassName + " INSTANCE = new " + mClassName + "();\n";
        } else {
            return "";
        }
    }

    private String printProperties() {
        final StringBuilder stringBuilder = new StringBuilder(printProperty(mProperties[0]));

        for (int index = 1; index < mProperties.length; index++) {
            stringBuilder.append("\n\n");
            stringBuilder.append(printProperty(mProperties[index]));
        }

        return stringBuilder.toString();
    }

    private String printProperty(Property property) {
        return "\t@Override\n" +
                "\tpublic " + property.getType() + " " + property.getName() + "() {\n" +
                "\t\treturn " + printFlags(property.getFlags(), property.getFallback()) + ";\n" +
                "\t}";
    }

    private String printFlags(String[] flags, String fallback) {
        if (flags.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(flags[0]);

            for (int index = 1; index < flags.length; index++) {
                stringBuilder.append(" &&\n");
                stringBuilder.append("\t\t\t\t");
                stringBuilder.append(flags[index]);
            }

            return stringBuilder.toString();
        } else {
            return "/* fallback value: */ " + fallback;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PropertiesImplementation)) {
            return false;
        }

        final PropertiesImplementation that = (PropertiesImplementation) object;
        return Objects.equals(this.mPackage, that.mPackage) && Objects.equals(this.mClassName, that.mClassName) &&
                Objects.equals(this.mImplements, that.mImplements) && Objects.equals(this.mIsSingleton, that.mIsSingleton) &&
                Arrays.equals(this.mProperties, that.mProperties);
    }

    @Override
    public String toString() {
        return String.format(
                Locale.ENGLISH,
                "PropertiesImplementation()"
        );
    }
}
