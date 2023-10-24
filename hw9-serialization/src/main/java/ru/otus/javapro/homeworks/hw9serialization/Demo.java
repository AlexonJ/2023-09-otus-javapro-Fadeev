package ru.otus.javapro.homeworks.hw9serialization;

import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.Sms;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsReport;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsReportOuterClass;
import ru.otus.javapro.homeworks.hw9serialization.service.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Demo {

    private static final String RELATIVE_OUTPUT_PATH = "output/";
    private static final String SOURCE_JSON_FILENAME = "sms-jsons/sms-original.json";
    private static final String TARGET_JSON_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.json";
    private static final String TARGET_XML_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.xml";
    private static final String TARGET_CSV_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.csv";
    private static final String TARGET_YML_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.yml";
    private static final String TARGET_SER_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.ser";
    private static final String TARGET_PROTOBUF_FILENAME = RELATIVE_OUTPUT_PATH + "sms-report.protobuf";

    public static void demo() throws IOException, JAXBException, CsvException, ClassNotFoundException {

        FileServiceImpl fileService = new FileServiceImpl();
        ConverterService converterService = new ConverterService();
        JsonSerializationService jsonSerializationService = new JsonSerializationService();
        XmlSerializationService xmlSerializationService = new XmlSerializationService();
        CsvSerializationService csvSerializationService = new CsvSerializationService();
        YmlSerializationService ymlSerializationService = new YmlSerializationService();

        File outputDirectory = fileService.createOutputDirectory(RELATIVE_OUTPUT_PATH);
        log.info("Output directory is {}", outputDirectory.getAbsolutePath());

        log.info("Reading JSON from resource file {}]", SOURCE_JSON_FILENAME);
        String jsonFromSourceFile = fileService.readFromFileAsResource(SOURCE_JSON_FILENAME);
        log.info("Deserializing JSON ...");
        Sms sms = jsonSerializationService.deserialize(jsonFromSourceFile, Sms.class);
        log.info("Converting JSON ...");
        SmsReport smsReport = converterService.convertToSmsReport(sms);

        log.info("Serializing converted object ...");

        String smsReportJson = jsonSerializationService.serialize(smsReport);
        log.info("Write converted object into JSON file {}", TARGET_JSON_FILENAME);
        fileService.writeStringToFile(TARGET_JSON_FILENAME, smsReportJson);
        log.info("Deserialized converted JSON object: {}",
                jsonSerializationService.deserialize(smsReportJson, SmsReport.class));

        String smsReportXml = xmlSerializationService.serialize(smsReport);
        log.info("Write converted object into XML file {}", TARGET_XML_FILENAME);
        fileService.writeStringToFile(TARGET_XML_FILENAME, smsReportXml);
        log.info("Deserialized converted XML object: {}",
                xmlSerializationService.deserialize(smsReportXml, SmsReport.class));

        String smsReportCsv = csvSerializationService.serialize(smsReport);
        log.info("Write converted object into CSV file {}", TARGET_CSV_FILENAME);
        fileService.writeStringToFile(TARGET_CSV_FILENAME, smsReportCsv);
        log.info("Deserialized converted CSV object: {}",
                csvSerializationService.deserialize(smsReportCsv, SmsReport.class));

        String smsReportYml = ymlSerializationService.serialize(smsReport);
        log.info("Write converted object into YML file {}", TARGET_YML_FILENAME);
        fileService.writeStringToFile(TARGET_YML_FILENAME, smsReportYml);
        log.info("Deserialized converted YML object: {}",
                ymlSerializationService.deserialize(smsReportYml, SmsReport.class));

        log.info("Deserializing converted object ...");
        smsReport = jsonSerializationService.deserialize(smsReportJson, SmsReport.class);
        System.out.println("Content of deserialized object SmsReport: \n" + smsReport);

        log.info("Serialized SmsReport object saved as Java-serialized object into {} file", TARGET_SER_FILENAME);
        fileService.writeObjectToFile(TARGET_SER_FILENAME, smsReport);
        log.info("Deserialized converted Java-serialized object: {}",
                fileService.readObjectFromFile(TARGET_SER_FILENAME, SmsReport.class));

        SmsReportOuterClass.SmsReport SmsReportConverted = converterService.convertToOuterClassSmsReport(smsReport);
        byte[] serializedData = SmsReportConverted.toByteArray();
        log.info("Serialized SmsReport object saved as ProtoBuf-serialized object into {} file", TARGET_PROTOBUF_FILENAME);
        fileService.writeObjectToFile(TARGET_PROTOBUF_FILENAME, serializedData);

    }
}