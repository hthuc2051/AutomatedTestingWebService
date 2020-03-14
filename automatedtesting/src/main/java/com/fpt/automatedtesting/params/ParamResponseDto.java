package com.fpt.automatedtesting.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamResponseDto implements Serializable {
    String name;
    String type;
}
