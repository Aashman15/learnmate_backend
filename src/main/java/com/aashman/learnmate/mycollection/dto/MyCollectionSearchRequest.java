package com.aashman.learnmate.mycollection.dto;

import com.aashman.learnmate.dto.MyPageRequest;
import lombok.Data;

@Data
public class MyCollectionSearchRequest extends MyPageRequest {
    private  String name;
}
