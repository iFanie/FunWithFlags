package dev.fanie.funwithflagscompiler.component;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;

import dev.fanie.funwithflagscompiler.Contract;
import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

public class View implements Contract.View {
    private final Contract.Presenter mPresenter;
    private final Filer mFiler;

    public View(Contract.Presenter presenter, Filer filer) {
        mPresenter = presenter;
        mFiler = filer;
    }

    @Override
    public void publishPropertiesImplementation(PropertiesImplementation propertiesImplementation) throws IOException {
        final JavaFileObject fileObject = mFiler.createSourceFile(propertiesImplementation.getQualifiedName());
        final Writer writer = fileObject.openWriter();

        writer.write(propertiesImplementation.getSourceCode());
        writer.close();
    }
}
