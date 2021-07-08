package com.qligent.point.count;

import com.qligent.point.count.dto.Input;
import com.qligent.point.count.dto.Point;
import com.qligent.point.count.dto.Region;
import com.qligent.point.count.exception.WrongInputException;
import com.qligent.point.count.service.PointCountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            Input input = Input.builder()
                    .initialPoint(entryDataForCalculation("center of circle"))
                    .radius(enterRadius())
                    .region(new Region(entryDataForCalculation("top left corner of the region"),
                            entryDataForCalculation("lower right corner of the region")))
                    .points(enterPointsForCheck())
                    .build();

            System.out.println("The number of points that satisfy the condition = " +
                    new PointCountService().pointCount(input));
        }catch (WrongInputException e){
            e.printStackTrace();
        }
    }

    private static Point entryDataForCalculation(String assignmentCoordinate)throws WrongInputException{
        Point point = new Point();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter coordinates "+assignmentCoordinate);
        System.out.println("Coordinate x (integer) ");
        if(!scan.hasNextInt()){throw new WrongInputException("data type does not match");}
        point.setX(scan.nextInt());
        System.out.println("Coordinate y (integer) ");
        if(!scan.hasNextInt()){throw new WrongInputException("data type does not match");}
        point.setY(scan.nextInt());
        return point;
    }

    private static List<Point> enterPointsForCheck()throws WrongInputException{
        Scanner scan = new Scanner(System.in);
        int count;
        System.out.println("Enter the number of points to check ");
        if(!scan.hasNextInt()){throw new WrongInputException("data type does not match");}
        count = scan.nextInt();
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
          pointList.add(entryDataForCalculation("coordinates of " + (i+1) +" point"));
        }
        return pointList;
    }

    private static int enterRadius()throws WrongInputException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the radius of the circle (integer) ");
        if(!scan.hasNextInt()){throw new WrongInputException("data type does not match");}
        return scan.nextInt();
    }
}
