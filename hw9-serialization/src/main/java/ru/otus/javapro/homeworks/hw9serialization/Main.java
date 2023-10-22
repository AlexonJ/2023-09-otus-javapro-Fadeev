package ru.otus.javapro.homeworks.hw9serialization;

import com.opencsv.exceptions.CsvException;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.Sms;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsReport;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsReportOuterClass;
import ru.otus.javapro.homeworks.hw9serialization.service.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final String RELATIVE_OUTPUT_PATH = "output/";
    private static final String SOURCE_JSON_FILENAME = "sms-jsons/sms-original.json";
    private static final String TARGET_JSON_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.json";
    private static final String TARGET_XML_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.xml";
    private static final String TARGET_CSV_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.csv";
    private static final String TARGET_YML_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.yml";
    private static final String TARGET_SER_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.ser";
    private static final String TARGET_PROTOBUF_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.protobuf";

    public static void main(String[] args) throws IOException, JAXBException, CsvException, ClassNotFoundException {

        FileServiceImpl fileService = new FileServiceImpl();
        ConverterService converterService = new ConverterService();
        JsonSerializationService jsonSerializationService = new JsonSerializationService();
        XmlSerializationService xmlSerializationService = new XmlSerializationService();
        CsvSerializationService csvSerializationService = new CsvSerializationService();
        YmlSerializationService ymlSerializationService = new YmlSerializationService();

        File outputDirectory = fileService.createOutputDirectory(RELATIVE_OUTPUT_PATH);
        System.out.printf("Output directory is %s%n", outputDirectory.getAbsolutePath());

        System.out.printf("Reading JSON from resource file %s%n", SOURCE_JSON_FILENAME);
        String jsonFromSourceFile = fileService.readFromFileAsResource(SOURCE_JSON_FILENAME);
        System.out.printf("Deserializing JSON ...%n");
        Sms sms = jsonSerializationService.deserialize(jsonFromSourceFile, Sms.class);
        System.out.printf("Converting JSON ...%n");
        SmsReport smsReport = converterService.convertToSmsReport(sms);

        System.out.printf("Serializing converted object ...%n");

        String smsReportJson = jsonSerializationService.serialize(smsReport);
        System.out.printf("Write converted object into JSON file %s%n", TARGET_JSON_FILENAME);
        fileService.writeStringToFile(TARGET_JSON_FILENAME, smsReportJson);
        System.out.printf("Deserialized converted JSON object: %n%s",
                jsonSerializationService.deserialize(smsReportJson, SmsReport.class));

        String smsReportXml = xmlSerializationService.serialize(smsReport);
        System.out.printf("Write converted object into XML file %s%n", TARGET_XML_FILENAME);
        fileService.writeStringToFile(TARGET_XML_FILENAME, smsReportXml);
        System.out.printf("Deserialized converted XML object: %n%s%n",
                xmlSerializationService.deserialize(smsReportXml, SmsReport.class));

        String smsReportCsv = csvSerializationService.serialize(smsReport);
        System.out.printf("Write converted object into CSV file %s%n", TARGET_CSV_FILENAME);
        fileService.writeStringToFile(TARGET_CSV_FILENAME, smsReportCsv);
        System.out.printf("Deserialized converted CSV object: %n%s%n",
                csvSerializationService.deserialize(smsReportCsv, SmsReport.class));

        String smsReportYml = ymlSerializationService.serialize(smsReport);
        System.out.printf("Write converted object into YML file %s%n", TARGET_YML_FILENAME);
        fileService.writeStringToFile(TARGET_YML_FILENAME, smsReportYml);
        System.out.printf("Deserialized converted YML object: %n%s%n",
                ymlSerializationService.deserialize(smsReportYml, SmsReport.class));

        System.out.printf("Deserializing converted object ...%n");
        smsReport = jsonSerializationService.deserialize(smsReportJson, SmsReport.class);
        System.out.println("Content of deserialized object SmsReport: \n" + smsReport);

        System.out.printf("Serialized SmsReport object saved as Java-serialized object into %s file%n", TARGET_SER_FILENAME);
        fileService.writeObjectToFile(TARGET_SER_FILENAME, smsReport);
        System.out.printf("Deserialized converted Java-serialized object: %n%s%n",
                fileService.readObjectFromFile(TARGET_SER_FILENAME, SmsReport.class));

        SmsReportOuterClass.SmsReport SmsReportConverted = converterService.convertToOuterClassSmsReport(smsReport);
        byte[] serializedData = SmsReportConverted.toByteArray();
        System.out.printf("Serialized SmsReport object saved as ProtoBuf-serialized object into %s file%n", TARGET_PROTOBUF_FILENAME);
        fileService.writeObjectToFile(TARGET_PROTOBUF_FILENAME, serializedData);

    }

}