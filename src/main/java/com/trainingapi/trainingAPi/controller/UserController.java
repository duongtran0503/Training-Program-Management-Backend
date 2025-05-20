package com.trainingapi.trainingAPi.controller;

import com.trainingapi.trainingAPi.dto.request.CreateLecturerRequest;
import com.trainingapi.trainingAPi.dto.request.CreateUserRequest;
import com.trainingapi.trainingAPi.dto.request.UpdateLecturerRequest;
import com.trainingapi.trainingAPi.dto.response.*;
import com.trainingapi.trainingAPi.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserController {
     UserService userService;

     @PostMapping("/account")
    public ApiResponse<CreateUserResponse> create(@RequestBody CreateUserRequest request){
        return  ApiResponse.<CreateUserResponse>builder()
                .data(userService.create(request))
                .isSuccess(true)
                .statusCode(200)
                .build();
     }

    @PostMapping("/lecturer-account")
    public ApiResponse<LecturerResponse> createLecturer(@Valid @RequestBody CreateLecturerRequest request) {
         return  ApiResponse.<LecturerResponse>builder()
                 .data(userService.createLecturer(request))
                 .isSuccess(true)
                 .statusCode(200)
                 .build();
    }

    @GetMapping("/list-lecturers")
    public ApiResponse<List<LecturerResponse>> getAllLecturer() {
          return  ApiResponse.<List<LecturerResponse>>builder()
                  .data(userService.getAllLecturer())
                  .isSuccess(true)
                  .statusCode(200)
                  .build();
    }

    @PutMapping("/update-lecturer/{id}")
    public ApiResponse<LecturerResponse> updateLecturer(@Valid @RequestBody UpdateLecturerRequest request,@PathVariable String id){
         return  ApiResponse.<LecturerResponse>builder()
                 .isSuccess(true)
                 .statusCode(200)
                 .data(userService.updateLecturer(request,id))
                 .build();
    }

    @DeleteMapping("/delete-lecturer/{id}")
    public  ApiResponse<Void> deleteLecturer(@PathVariable String id) {
         userService.deleteLecturer(id);
         return ApiResponse.<Void>builder().isSuccess(true).message("delete success").statusCode(200).build();
    }

    @GetMapping("/search-lecturer")
    public  ApiResponse<List<LecturerResponse>> findLecturerByName( @RequestParam(value = "name", required = false) String name){
         return ApiResponse.<List<LecturerResponse>>builder().statusCode(200).isSuccess(true)
                 .data(userService.searchLecturer(name)).build();
    }

}
