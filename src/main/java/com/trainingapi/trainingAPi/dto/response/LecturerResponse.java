package com.trainingapi.trainingAPi.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LecturerResponse {
    String id;
    String role = "lecturer";
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
    Date createAt;
    Date updateAt;
}
