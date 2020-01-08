package com.fpt.automatedtesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto implements Serializable {

    String name;
    String code;
    List<ParamResponseDto> listParams;
    String subject;

}
