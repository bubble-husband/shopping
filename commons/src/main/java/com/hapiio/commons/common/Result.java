package com.hapiio.commons.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String message;
}
