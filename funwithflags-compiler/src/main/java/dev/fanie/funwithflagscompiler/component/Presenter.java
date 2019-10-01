package dev.fanie.funwithflagscompiler.component;

import java.io.IOException;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflagscompiler.Contract;
import dev.fanie.funwithflagscompiler.DuplicateFlagIdException;
import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

import static dev.fanie.funwithflagscompiler.util.Getter.getFlagIdFrom;
import static dev.fanie.funwithflagscompiler.util.Mapper.getPropertiesImplementationFrom;

public class Presenter implements Contract.Presenter {
    private Contract.Model mModel;
    private Contract.View mView;

    @Override
    public void connect(Contract.Model model, Contract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void onCompilation() {
        mModel.requestFlags();
        mModel.requestPropertiesClasses();
    }

    @Override
    public void onFlagsReceived(Element[] flags) {
        for (Element flag : flags) {
            final long id = getFlagIdFrom(flag);
            final Element storedFlag = mModel.getFlagById(id);

            if (storedFlag != null) {
                throw new DuplicateFlagIdException(flag, storedFlag);
            } else {
                mModel.storeFlagWithId(flag, id);
            }
        }
    }

    @Override
    public void onPropertiesClassesReceived(TypeElement[] propertiesClasses) {
        for (TypeElement propertyClass : propertiesClasses) {
            mModel.requestProperties(propertyClass);
        }
    }

    @Override
    public void onPropertiesReceived(TypeElement propertiesClass, Element[] properties) {
        final Map<Long, Element> storedFlags = mModel.getAllFlags();
        final PropertiesImplementation propertiesImplementation = getPropertiesImplementationFrom(propertiesClass, properties, storedFlags);
        mModel.storePropertiesImplementation(propertiesImplementation);
    }

    @Override
    public void onFinalization() {
        final PropertiesImplementation[] propertiesImplementations = mModel.getStoredPropertiesImplementations();

        for (PropertiesImplementation propertiesImplementation : propertiesImplementations) {
            try {
                mView.publishPropertiesImplementation(propertiesImplementation);
            } catch (IOException ignored) {

            }
        }
    }
}
