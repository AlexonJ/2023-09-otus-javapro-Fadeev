package ru.otus.javapro.homeworks.hw9serialization.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsData;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsDataGroupByNumber;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.SmsReport;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvSerializationService implements SerializationService{

    @Override
    public String serialize(Object object) {

        if (!(object instanceof SmsReport)){
            return null;
        }
        SmsReport smsReport = (SmsReport) object;
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        String[] header = {"belong_number", "chat_identifier", "members_last", "message_send_date", "text"};
        csvWriter.writeNext(header);
        for (SmsDataGroupByNumber dataGroup : smsReport.getSmsDataGroupList()) {
            for (SmsData smsData : dataGroup.getSmsDataList()) {
                csvWriter.writeNext(new String[]{
                        dataGroup.getBelongNumber()
                        , smsData.getChatIdentifier()
                        , smsData.getMembersLast()
                        , smsData.getSendDate().toString()
                        , smsData.getText()});
            }
        }

        return writer.getBuffer().toString();

    }

    @Override
    public <T> T deserialize(String csvString, Class<T> clazz) throws IOException, CsvException {

        if (clazz != SmsReport.class){
            return null;
        }

        StringReader stringReader = new StringReader(csvString);
        CSVReader csvReader = new CSVReader(stringReader);

        List<String[]> data = csvReader.readAll();
        data.remove(0);

        SmsReport smsReport = SmsReport.builder().smsDataGroupList(new ArrayList<>()).build();
        List<SmsDataGroupByNumber> smsDataGroupList = smsReport.getSmsDataGroupList();
        for (String[] row : data) {
            String belong_number = row[0];
            String chat_identifier = row[1];
            String members_last = row[2];
            LocalDateTime message_send_date = LocalDateTime.parse(row[3]);
            String text = row[4];

            SmsDataGroupByNumber currentSmsDataGroup = smsDataGroupList.stream().filter(
                    smsDataGroup -> smsDataGroup.getBelongNumber().equals(belong_number)).findFirst().orElse(
                    SmsDataGroupByNumber.builder()
                            .smsDataList(new ArrayList<>())
                            .belongNumber(belong_number).build());

            if (!smsDataGroupList.contains(currentSmsDataGroup)){
                smsDataGroupList.add(currentSmsDataGroup);
            }
            currentSmsDataGroup.getSmsDataList().add(SmsData.builder()
                    .chatIdentifier(chat_identifier)
                    .membersLast(members_last)
                    .sendDate(message_send_date)
                    .text(text).build());
        }

        csvReader.close();
        return (T)smsReport;
    }
}
