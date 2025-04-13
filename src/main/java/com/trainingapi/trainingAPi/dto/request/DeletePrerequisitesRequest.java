package com.trainingapi.trainingAPi.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class DeletePrerequisitesRequest {
   String prerequisiteCode;
}
