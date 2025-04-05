package com.trainingapi.trainingAPi.dto.request;

import com.trainingapi.trainingAPi.annotations.ValidValueStringDefine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLecturerRequest {
    String name;
    String lecturerCode;
    @ValidValueStringDefine(values={"nam","nữ"},message = "DATA_REQUEST_INVALID")
    String gender;
    @ValidValueStringDefine(values={"tiến sĩ","giáo sư"},message = "DATA_REQUEST_INVALID")
    String titleAcademicRank;
    String avatar;
    String department;
    boolean status;
    Date dob;
    Date startDateOfTeaching;

}
