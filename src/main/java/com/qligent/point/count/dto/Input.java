package com.qligent.point.count.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Input {

    private Point initialPoint;
    private int radius;
    private Region region;
    private List<Point> points;
}
