package dev.fanie.funwithflagscompiler.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

import dev.fanie.funwithflags.FunWithFlags;
import dev.fanie.funwithflagscompiler.Contract;
import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

import static dev.fanie.funwithflagscompiler.util.Getter.filterAnnotatedElements;
import static dev.fanie.funwithflagscompiler.util.Mapper.getElementArrayFrom;
import static dev.fanie.funwithflagscompiler.util.Mapper.getPropertiesImplementationsArrayFrom;
import static dev.fanie.funwithflagscompiler.util.Mapper.getTypeElementArrayFrom;

public class Model implements Contract.Model {
    private final Contract.Presenter mPresenter;

    private final Map<Long, Element> flagsStorage = new HashMap<>();
    private final List<PropertiesImplementation> propertiesImplementationsStorage = new ArrayList<>();

    private RoundEnvironment mRoundEnvironment;

    public Model(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void connect(RoundEnvironment roundEnvironment) {
        mRoundEnvironment = roundEnvironment;
    }

    @Override
    public void requestFlags() {
        final Set<? extends Element> flagsSet = mRoundEnvironment.getElementsAnnotatedWith(FunWithFlags.Flag.class);
        final Element[] flags = getElementArrayFrom(flagsSet);

        mPresenter.onFlagsReceived(flags);
    }

    @Override
    public Element getFlagById(long id) {
        return flagsStorage.get(id);
    }

    @Override
    public void storeFlagWithId(Element flag, long id) {
        flagsStorage.put(id, flag);
    }

    @Override
    public void requestPropertiesClasses() {
        final Set<? extends Element> propertiesClassesSet = mRoundEnvironment.getElementsAnnotatedWith(FunWithFlags.Properties.class);
        final Set<TypeElement> propertiesClassesTypesSet = ElementFilter.typesIn(propertiesClassesSet);
        final TypeElement[] propertiesClasses = getTypeElementArrayFrom(propertiesClassesTypesSet);

        mPresenter.onPropertiesClassesReceived(propertiesClasses);
    }

    @Override
    public void requestProperties(TypeElement propertiesClass) {
        final List<? extends Element> enclosedElements = propertiesClass.getEnclosedElements();
        final List<? extends Element> annotatedElements = filterAnnotatedElements(enclosedElements, FunWithFlags.Property.class);
        final Element[] properties = getElementArrayFrom(annotatedElements);

        mPresenter.onPropertiesReceived(propertiesClass, properties);
    }

    @Override
    public void storePropertiesImplementation(PropertiesImplementation propertiesImplementation) {
        propertiesImplementationsStorage.add(propertiesImplementation);
    }

    @Override
    public PropertiesImplementation[] getStoredPropertiesImplementations() {
        return getPropertiesImplementationsArrayFrom(propertiesImplementationsStorage);
    }
}
