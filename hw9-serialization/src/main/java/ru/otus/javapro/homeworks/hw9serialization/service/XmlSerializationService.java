package ru.otus.javapro.homeworks.hw9serialization.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlSerializationService implements SerializationService{

    @Override
    public String serialize(Object object) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();

    }

    @Override
    public <T> T deserialize(String xmlString, Class<T> clazz) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xmlString);
        return (T) unmarshaller.unmarshal(reader);
    }
}
