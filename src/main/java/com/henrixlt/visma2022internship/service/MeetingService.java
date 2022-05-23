package com.henrixlt.visma2022internship.service;

import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.dto.PersonDto;
import com.henrixlt.visma2022internship.entity.Meeting;
import com.henrixlt.visma2022internship.exception.MeetingException;
import com.henrixlt.visma2022internship.exception.PersonException;
import com.henrixlt.visma2022internship.mapper.MeetMapper;
import com.henrixlt.visma2022internship.modal.Category;
import com.henrixlt.visma2022internship.modal.Person;
import com.henrixlt.visma2022internship.modal.Type;
import com.henrixlt.visma2022internship.reader.FileReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
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

    public MeetingDto findByName(String meetingName) throws IOException {
        return fileReader.readAll()
                .stream()
                .filter(name -> name.getName().equals(meetingName))
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .findAny()
                .orElseThrow(() -> new MeetingException(meetingName));
    }

    public MeetingDto findById(Long id) throws IOException {
        List<MeetingDto> meetingDtoList = fileReader.readAll().stream()
                .map(meet -> MeetMapper.MEET_MAPPER.mapDto(meet))
                .collect(Collectors.toList());
        if (!(meetingDtoList.size() >= id)) {
            throw new MeetingException(id);
        }
        return meetingDtoList
                .stream()
                .filter(meet -> meet.getId().equals(id))
                .map(dto -> dto)
                .findFirst()
                .orElseThrow(() -> new MeetingException(id));

    }

    public void save(MeetingDto meetingDto) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (findMeeting(meetings, meetingDto.getId()) >= 0) {
            throw new MeetingException();
        }
        meetingDto.setId((long) meetings.size() + 1);
        Meeting meeting = MeetMapper.MEET_MAPPER.mapModel(meetingDto);
        Person person = meeting.getAuthor();
        person.setAuthor(true);
        meeting.setAuthor(person);
        meeting.getParticipant().set(0, person);
        meetings.add(meeting);
        fileReader.writeAll(meetings);
    }

    public List<MeetingDto> update(MeetingDto meetingDto) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (meetingDto == null && !meetingDto.getAuthor().isAuthor()) {
            throw new MeetingException(meetingDto.getName());
        }
        if (!existByID(meetings, meetingDto.getId())) {
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

    public void delete(MeetingDto meetingDto, Long id) throws IOException {
        List<Meeting> meetings = getMeeting();
        if (!meetingDto.getAuthor().isAuthor() || !meetingDto.getAuthor().getFirstName().equals(ADMIN)) {
            throw new MeetingException(meetingDto.getName());
        }
        if (!existByID(meetings, id)) {
            throw new MeetingException(id);
        }
        int index = findMeeting(meetings, id);
        meetings.remove(index);
        fileReader.writeAll(meetings);
    }

    public void addPersonToMeeting(PersonDto persondto, Long meetingID) throws IOException {
        List<Meeting> meetings = getMeeting();

        if (persondto == null && !persondto.isAuthor()) {
            throw new MeetingException(persondto.getFirstName());
        }
        if (!existByID(meetings, meetingID)) {
            throw new MeetingException(meetingID);
        }
        int index = findMeeting(meetings, meetingID);
        Meeting meeting = meetings.get(index);
        List<Person> people = meeting.getParticipant();

        if (existByName(people, persondto.getLastName())) {
            throw new PersonException(persondto.getLastName());
        }

        people.add(getPerson(persondto));
        meeting.setParticipant(people);
        meetings.set(index, meeting);
        fileReader.writeAll(meetings);
    }

    public void removePerson(PersonDto persondto, Long meetingID) throws IOException {
        //TODO fix dublicate code
        List<Meeting> meetings = getMeeting();

        if (persondto == null && !persondto.isAuthor()) {
            throw new MeetingException(persondto.getFirstName());
        }
        if (!existByID(meetings, meetingID)) {
            throw new MeetingException(meetingID);
        }
        int index = findMeeting(meetings, meetingID);
        Meeting meeting = meetings.get(index);
        List<Person> people = meeting.getParticipant();

        if (!existByName(people, persondto.getLastName())) {
            throw new PersonException();
        }

        people.remove(getPerson(persondto));
        meeting.setParticipant(people);
        meetings.set(index, meeting);
        fileReader.writeAll(meetings);
    }
    //TODO: All find methods make it to one
    //TODO: Fif Exception tiping correction
    public List<MeetingDto> findByDescription(String meetingDesc) throws IOException {
        return fileReader.readAll()
                .stream()
                .filter(desc -> desc.getDescription().contains(meetingDesc))
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findByResPerson(String resPerson) throws IOException {
        return fileReader.readAll()
                .stream()
                .filter(person -> person.getResponsiblePerson().equals(resPerson))
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findByCategory(Category meetingCategory) throws IOException {
        return fileReader.readAll()
                .stream()
                .filter(category -> category.getCategory() == meetingCategory)
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findByType(Type meetingType) throws IOException {
        return fileReader.readAll()
                .stream()
                .filter(type -> type.getType() == meetingType)
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findStartFromDate(String startDate) throws IOException {
        LocalDate meetingDate = LocalDate.parse(startDate);
        return fileReader.readAll()
                .stream()
                .filter(date -> date.getStartDate().compareTo(meetingDate) >= 0)
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findBtDate(String startDate, String endDate) throws IOException {
        LocalDate startTime = LocalDate.parse(startDate);
        LocalDate endTime = LocalDate.parse(endDate);
        return fileReader.readAll()
                .stream()
                .filter(time -> (time.getStartDate().compareTo(startTime) >= 0)
                        && (time.getEndDate().compareTo(endTime) <= 0))
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    public List<MeetingDto> findByNumberOfPeople(Integer number) throws IOException {

        return fileReader.readAll()
                .stream()
                .filter(people -> people.getParticipant().size() >= number)
                .map(MeetMapper.MEET_MAPPER::mapDto)
                .collect(Collectors.toList());
    }

    private List<Meeting> getMeeting() throws IOException {
        return new ArrayList<>(fileReader.readAll());
    }

    private boolean existByID(List<Meeting> meetings, Long id) {
        return meetings.stream()
                .anyMatch(meet -> meet.getId().equals(id));
    }

    private int findMeeting(List<Meeting> meetings, Long id) {
        int index = -1;
        // Iterate over the elements of the list
        for (Meeting meeting : meetings) {
            if (meeting.getId().equals(id)) index = meetings.indexOf(meeting);
        }
        return index;
    }

    private boolean existByName(List<Person> people, String lastName) {
        return people.stream()
                .anyMatch(person -> person.getLastName().equals(lastName));
    }

    private Person getPerson(PersonDto persondto) {
        return Person.builder()
                .firstName(persondto.getFirstName())
                .lastName(persondto.getLastName())
                .author(false)
                .build();
    }

}
