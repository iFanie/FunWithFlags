package dev.fanie.funwithflagscompiler;

import java.util.Locale;

import javax.lang.model.element.Element;

public class DuplicateFlagIdException extends RuntimeException {
    public DuplicateFlagIdException(Element firstFlag, Element secondFlag) {
        super(String.format(
                Locale.ENGLISH,
                "Flags &1$s and %2$s, have the same ID.",
                describeFlag(firstFlag),
                describeFlag(secondFlag)
        ));
    }

    private static String describeFlag(Element flag) {
        return flag.getEnclosingElement().getSimpleName() + "#" + flag.getSimpleName();
    }
}
