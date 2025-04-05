package com.trainingapi.trainingAPi.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLecturerRequest {
    String name;
    String lecturerCode;
    String gender;
    String titleAcademicRank;
    String avatar;
    String department;
    boolean status;
    Date dob;
    Date startDateOfTeaching;
    Date endDateOfTeaching;
}
