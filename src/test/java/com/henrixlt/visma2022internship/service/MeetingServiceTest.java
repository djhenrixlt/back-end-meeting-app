package com.henrixlt.visma2022internship.service;

import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.entity.Meeting;
import com.henrixlt.visma2022internship.mapper.MeetMapper;
import com.henrixlt.visma2022internship.reader.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeetingServiceTest {

    private static final long ID = 1L;

    @InjectMocks
    private MeetingService meetingService;

    @Mock
    private FileReader fileReader;

    @Test
    void findAll() throws IOException {
        Meeting  meeting = Meeting.builder().name("name").build();
        List<Meeting> meetings = List.of(meeting);
        when(fileReader.readAll()).thenReturn(meetings);
        List<MeetingDto> meetingDtoList = meetingService.findAll();
        assertEquals(1,meetingDtoList.size());
        assertEquals(meetings.get(0).getName(),
                meetingDtoList.get(0).getName());
    }

    @Test
    void findById() throws IOException {
        Meeting  meeting = Meeting.builder().id(ID).build();
        List<Meeting> meetings = List.of(meeting);
        when(fileReader.readAll()).thenReturn(meetings);

        MeetingDto meetingDto = meetingService.findById(ID);

        assertEquals(meeting.getId(),
                meetingDto.getId());
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void addPersonToMeeting() {
    }

    @Test
    void removePerson() {
    }

    @Test
    void delete() {
    }
}
