package com.aashman.learnmate.features.mycollection.dto;

import com.aashman.learnmate.dto.MyPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyCollectionSearchRequest extends MyPageRequest {
    private  String name;
}
