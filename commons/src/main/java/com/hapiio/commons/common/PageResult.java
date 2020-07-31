package com.hapiio.commons.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResult implements Serializable {

    private Long total;
    private List rows;
}
