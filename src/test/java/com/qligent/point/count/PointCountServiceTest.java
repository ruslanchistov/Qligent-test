package com.qligent.point.count;

import com.qligent.point.count.dto.Input;
import com.qligent.point.count.dto.Point;
import com.qligent.point.count.dto.Region;
import com.qligent.point.count.exception.WrongInputException;
import com.qligent.point.count.service.PointCountService;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PointCountServiceTest {


    @Test
    void pointCount()  {
        PointCountService pointCountService = new PointCountService();
        List<List<Point>> point_groups = new ArrayList<>();
        int[] amount_actual_points = {0,0,0,5};
//        список точек, не принадлежащих ни одной области
        point_groups.add(List.of(new Point(2, 1), new Point(1, 1), new Point(2, 7),
                new Point(4, 9), new Point(6, 1)));
//        список точек принадлежащих окружности, но не принадлежащих региону
        point_groups.add(List.of(new Point(4, 1), new Point(5, 2), new Point(3, 5),
                new Point(1,4),new Point(3,3)));
//        список точек принадлежащих региону, но не принадлежащих окружности
        point_groups.add(List.of(new Point(8, 3), new Point(8, 8), new Point(7, 7),
                new Point(5,8),new Point(6,7)));
//        список точек, принадлежащих и окружности и региону
        point_groups.add(List.of(new Point(4, 4), new Point(7, 4), new Point(6, 5),
                new Point(4,7),new Point(5,5)));
        for (int i = 0; i < 4; i++) {
            Input input = Input.builder()
                    .initialPoint(new Point(4, 4))
                    .radius(3)
                    .region(new Region(new Point(4, 8), new Point(8, 3)))
                    .points(point_groups.get(i))
                    .build();
            try {
                int count = pointCountService.pointCount(input);
                assertEquals(amount_actual_points[i], count);
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void checkingInputParameters() throws WrongInputException{
        Throwable thrown = assertThrows(WrongInputException.class,
                () ->new PointCountService().pointCount(null));
        assertNotNull(thrown.getMessage());
    }
}
