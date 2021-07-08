package com.qligent.point.count.service;

import com.qligent.point.count.dto.Input;
import com.qligent.point.count.exception.WrongInputException;
import org.springframework.stereotype.Service;

@Service
public class PointCountService {

    public int pointCount(Input input) {
        if (input == null){ throw new WrongInputException("input data is null");
        }
        int count = 0;
        int cur_x, cur_y;
        boolean circle_affiliation, region_affiliation;


        int count_points = input.getPoints().size();
        int radius = input.getRadius();
        int center_x = input.getInitialPoint().getX();
        int center_y = input.getInitialPoint().getY();
        int leftUpperCorner_x = input.getRegion().getLeftUpperCorner().getX();
        int leftUpperCorner_y = input.getRegion().getLeftUpperCorner().getY();
        int rightDownCorner_x = input.getRegion().getRightDownCorner().getX();
        int rightDownCorner_y = input.getRegion().getRightDownCorner().getY();


        for (int i = 0; i < count_points; i++) {
            cur_x = input.getPoints().get(i).getX();
            cur_y = input.getPoints().get(i).getY();
            circle_affiliation = Math.pow((cur_x - center_x), 2) + Math.pow((cur_y - center_y), 2)
                    <= Math.pow(radius, 2);
            region_affiliation = (leftUpperCorner_x <=cur_x && cur_x <=rightDownCorner_x)&&
                            (leftUpperCorner_y >=cur_y && cur_y >= rightDownCorner_y);
            if (circle_affiliation && region_affiliation) {
                count += 1;
            }
        }
        return count;
    }
}
