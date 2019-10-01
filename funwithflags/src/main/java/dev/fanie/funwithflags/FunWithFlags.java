package dev.fanie.funwithflags;

public interface FunWithFlags {
    @interface Flag {
        long id();
    }

    @interface Properties {
        boolean isSingleton() default false;
    }

    @interface Property {
        long[] ids() default {};

        boolean fallback() default false;
    }
}
