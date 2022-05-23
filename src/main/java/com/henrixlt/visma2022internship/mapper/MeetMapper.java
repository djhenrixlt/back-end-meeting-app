package com.henrixlt.visma2022internship.mapper;

import com.henrixlt.visma2022internship.dto.MeetingDto;
import com.henrixlt.visma2022internship.entity.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MeetMapper {

    MeetMapper MEET_MAPPER = Mappers.getMapper(MeetMapper.class);

    MeetingDto mapDto(Meeting meeting);

    Meeting mapModel(MeetingDto meetingDto);


}
