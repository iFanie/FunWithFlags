package dev.fanie.funwithflagscompiler;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflags.FunWithFlags;
import dev.fanie.funwithflagscompiler.component.Model;
import dev.fanie.funwithflagscompiler.component.Presenter;
import dev.fanie.funwithflagscompiler.component.View;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Compiler extends AbstractProcessor {
    private Contract.Model model;
    private Contract.View view;
    private Contract.Presenter presenter;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(Arrays.asList(
                FunWithFlags.Flag.class.getCanonicalName(),
                FunWithFlags.Property.class.getCanonicalName(),
                FunWithFlags.Properties.class.getCanonicalName()
        ));
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        presenter = new Presenter();
        model = new Model(presenter);
        view = new View(presenter, processingEnvironment.getFiler());

        presenter.connect(model, view);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!roundEnvironment.processingOver()) {
            model.connect(roundEnvironment);
            presenter.onCompilation();
            return true;
        } else {
            presenter.onFinalization();
            return false;
        }
    }
}
