package ru.otus.javapro.homeworks.hw9serialization.service;

import ru.otus.javapro.homeworks.hw9serialization.dao.dto.*;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat.ChatSession;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat.Member;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConverterService {
    public SmsReport convertToSmsReport(Sms sms){
        SmsReport smsReport = SmsReport.builder().smsDataGroupList(new ArrayList<>()).build();

        List<SmsDataGroupByNumber> smsDataGroupList = smsReport.getSmsDataGroupList();
        for (ChatSession chatSession: sms.getChatSessions()) {
            for (Member member : chatSession.getMembers()) {
                for (Message message: chatSession.getMessages()){
                    SmsDataGroupByNumber currentSmsDataGroup = smsDataGroupList.stream().filter(
                            smsDataGroup -> smsDataGroup.getBelongNumber().equals(message.getBelongNumber())).findFirst().orElse(
                            SmsDataGroupByNumber.builder()
                                    .smsDataList(new ArrayList<>())
                                    .belongNumber(message.getBelongNumber()).build());
                    if (!smsDataGroupList.contains(currentSmsDataGroup)){
                        smsDataGroupList.add(currentSmsDataGroup);
                    }
                    currentSmsDataGroup.getSmsDataList().add(SmsData.builder()
                            .chatIdentifier(chatSession.getIdentifier())
                            .membersLast(member.getLast())
                            .sendDate(message.getSendDate())
                            .text(message.getText()).build());
                }
            }
        }

        smsReport.getSmsDataGroupList().forEach(smsDataGroupByNumber ->
                smsDataGroupByNumber.getSmsDataList().sort(Comparator.comparing(SmsData::getSendDate)));
        return smsReport;
    }

    public SmsReportOuterClass.SmsReport convertToOuterClassSmsReport(SmsReport smsReport){
        var smsReportProtoBuf = SmsReportOuterClass.SmsReport.newBuilder().build();
        for (SmsDataGroupByNumber smsDataGroupByNumber : smsReport.getSmsDataGroupList()){
            SmsReportOuterClass.SmsDataGroupByNumber smsDataGroupByNumberProtoBuf = SmsReportOuterClass.SmsDataGroupByNumber.newBuilder()
                    .setBelongNumber(smsDataGroupByNumber.getBelongNumber()).build();

            for (SmsData smsData : smsDataGroupByNumber.getSmsDataList()){
                smsDataGroupByNumberProtoBuf = smsDataGroupByNumberProtoBuf.toBuilder().addSmsData(
                        SmsReportOuterClass.SmsData.newBuilder()
                                .setChatIdentifier(smsData.getChatIdentifier())
                                .setMembersLast(smsData.getMembersLast())
                                .setSendDate(smsData.getSendDate().toString())
                                .setText(smsData.getText()).build()).build();
            }
            smsReportProtoBuf = smsReportProtoBuf.toBuilder().addSmsDataGroupByNumber(smsDataGroupByNumberProtoBuf).build();
        }
        return smsReportProtoBuf;
    }
}
