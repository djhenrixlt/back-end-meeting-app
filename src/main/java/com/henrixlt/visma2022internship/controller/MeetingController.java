package com.henrixlt.visma2022internship.controller;

import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.dto.PersonDto;
import com.henrixlt.visma2022internship.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
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
}
