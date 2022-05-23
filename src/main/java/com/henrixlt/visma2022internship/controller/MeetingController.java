package com.henrixlt.visma2022internship.controller;

import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.dto.PersonDto;
import com.henrixlt.visma2022internship.modal.Category;
import com.henrixlt.visma2022internship.modal.Type;
import com.henrixlt.visma2022internship.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/meetings")
public class MeetingController {


    private MeetingService meetingService;

    @GetMapping
    public ResponseEntity<List<MeetingDto>> findAll() throws IOException {
        return ResponseEntity.ok(meetingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDto> findByID(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(meetingService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid MeetingDto meetingDto) throws IOException {
        meetingService.save(meetingDto);
    }

    @PutMapping
    public ResponseEntity<List<MeetingDto>> update(@RequestBody @Valid MeetingDto meetingDto) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(meetingService.update(meetingDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody @Valid MeetingDto meetingDto, @PathVariable Long id) throws IOException {
        meetingService.delete(meetingDto, id);
    }

    @PutMapping("/{meetingId}/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addPersonToMeeting(@RequestBody @Valid PersonDto personDto, @PathVariable Long meetingId) throws IOException {
        meetingService.addPersonToMeeting(personDto, meetingId);
    }

    @DeleteMapping("/{meetingId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonFromMeeting(@RequestBody @Valid PersonDto personDto, @PathVariable Long meetingId) throws IOException {
        meetingService.removePerson(personDto, meetingId);
    }

    //TODO: ALL similar  methods make to one
    @GetMapping("/byName")
    public ResponseEntity<MeetingDto> getByName(@RequestParam(value = "name") String name) throws IOException {
        return ResponseEntity.ok(meetingService.findByName(name));
    }

    @GetMapping("/byDescription")
    public ResponseEntity<List<MeetingDto>> getByDescription(@RequestParam(value = "description") String description) throws IOException {
        return ResponseEntity.ok(meetingService.findByDescription(description));
    }

    @GetMapping("/byType")
    public ResponseEntity<List<MeetingDto>> getByType(@RequestParam(value = "type") Type type) throws IOException {
        return ResponseEntity.ok(meetingService.findByType(type));
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<MeetingDto>> getByCategory(@RequestParam(value = "category") Category category) throws IOException {
        return ResponseEntity.ok(meetingService.findByCategory(category));
    }

    @GetMapping("/byResPerson")
    public ResponseEntity<List<MeetingDto>> getByResPerson(@RequestParam("person") String person) throws IOException {
        return ResponseEntity.ok(meetingService.findByResPerson(person));
    }

    @GetMapping("/byStartFromDate")
    public ResponseEntity<List<MeetingDto>> getByStartFromDate(@RequestParam("date") LocalDate date) throws IOException {
        return ResponseEntity.ok(meetingService.findStartFromDate(date));
    }

    @GetMapping("/bytDate")
    public ResponseEntity<List<MeetingDto>> findBytDate(@RequestParam("starting") LocalDate starting,
                                                       @RequestParam("ending") LocalDate ending) throws IOException {
        return ResponseEntity.ok(meetingService.findBtDate(starting, ending));
    }

    @GetMapping("/byNumberUsers")
    public ResponseEntity<List<MeetingDto>> findByNumberOfPeople(@RequestParam("number") Integer number) throws IOException {
        return ResponseEntity.ok(meetingService.findByNumberOfPeople(number));
    }
}

