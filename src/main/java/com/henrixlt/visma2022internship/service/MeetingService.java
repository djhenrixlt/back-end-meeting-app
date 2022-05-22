package com.henrixlt.visma2022internship.service;

import com.henrixlt.visma2022internship.exception.PersonException;
import com.henrixlt.visma2022internship.reader.FileReader;
import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.dto.PersonDto;
import com.henrixlt.visma2022internship.entity.Meeting;
import com.henrixlt.visma2022internship.exception.MeetingException;
import com.henrixlt.visma2022internship.mapper.MeetMapper;
import com.henrixlt.visma2022internship.modal.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class MeetingService {

    private final String ADMIN = "Henrikas";
    private FileReader fileReader;


    public List<MeetingDto> findAll() throws IOException {
        return fileReader.readAll()
                .stream()
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public MeetingDto findById(Long id) throws IOException {
        List<MeetingDto> meetingDtoList = fileReader.readAll().stream()
                .map(meet -> MeetMapper.MEET_MAPPER.mapDto(meet))
                .collect(Collectors.toList());
        if (!(meetingDtoList.size() >= id)){
            throw new MeetingException(id);
        }
        return meetingDtoList
                .stream()
                .filter(meet -> meet.getId().equals(id))
                .map(dto -> dto)
                .findFirst()
                .orElseThrow(()->new  MeetingException(id));

    }



    public void save(MeetingDto meetingDto) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (findMeeting(meetings,meetingDto.getId()) >= 0){
            throw new MeetingException();
        }
        meetingDto.setId((long) meetings.size()+1);
        Meeting meeting = MeetMapper.MEET_MAPPER.mapModel(meetingDto);
        meetings.add(meeting);
        fileReader.writeAll(meetings);
    }
//    public List<MeetingDto> findByDiscription(MeetingDto meetingDto){
//        List<Meeting> meetings = getMeeting();
//
//    }

    public List<MeetingDto> update(MeetingDto meetingDto) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (meetingDto == null && !meetingDto.getAuthor().isAuthor()){
            throw new MeetingException(meetingDto.getName());
        }
        if (!existbyID(meetings, meetingDto.getId())) {
            throw new MeetingException(meetingDto.getId());
        }
        int index = findMeeting(meetings, meetingDto.getId());
        meetings.set(index, MeetMapper.MEET_MAPPER.mapModel(meetingDto));

        fileReader.writeAll(meetings);
        return meetings
                .stream()
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }
    public void addPersonToMeeting( PersonDto persondto, Long meetingID) throws IOException {
        List<Meeting> meetings = getMeeting();

        if (persondto == null && !persondto.isAuthor()){
            throw new MeetingException(persondto.getFirstName());
        }
        if (!existbyID(meetings, meetingID)) {
            throw new MeetingException(meetingID);
        }
        int index = findMeeting(meetings, meetingID);
        Meeting meeting = meetings.get(index);
        List<Person> people = meeting.getParticipant();

        if (existbyName(people, persondto.getLastName())) {
            throw new PersonException(persondto.getLastName());
        }

        people.add(getPerson(persondto));
        meeting.setParticipant(people);
        meetings.set(index,meeting);
        fileReader.writeAll(meetings);
    }

    private Person getPerson(PersonDto persondto) {
        return Person.builder()
                .firstName(persondto.getFirstName())
                .lastName(persondto.getLastName())
                .author(false)
                .build();
    }

    public void removePerson(MeetingDto meetingDto, PersonDto person) throws IOException {
        meetingDto.getParticipant().remove(person);
        save(meetingDto);
    }

    public void delete(MeetingDto meetingDto, Long id) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (!meetingDto.getAuthor().isAuthor() || !meetingDto.getAuthor().getFirstName().equals(ADMIN)){
            throw new MeetingException(meetingDto.getName());
        }
        if (!existbyID(meetings, id)) {
            throw new MeetingException(id);
        }
        int index = findMeeting(meetings, id);
        meetings.remove(index);
        fileReader.writeAll(meetings);
    }

    private List<Meeting> getMeeting() throws IOException {
        return new ArrayList<>(fileReader.readAll());
    }

    private boolean existbyID(List<Meeting> meetings, Long id){
        return meetings.stream()
                .anyMatch(meet -> meet.getId().equals(id));
    }
    private int findMeeting(List<Meeting> meetings, Long id) {
        int index = -1;
        // Iterate over the elements of the list
        for (Meeting  meeting : meetings) {
            if (meeting.getId().equals(id)) index = meetings.indexOf(meeting);
        }
        return index;
    }
    private boolean existbyName(List<Person>  people, String lastName){
        return people.stream()
                .anyMatch(person -> person.getLastName().equals(lastName));
    }

}
