package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {
    private int star;
    private String review_context;
    private String review_date;
    private String user_id;
}
