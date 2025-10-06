package com.aashman.learnmate.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MyPageRequest {

    @Min(1)
    private  Integer page = 1;
    private  Integer pageSize = 20;
}
