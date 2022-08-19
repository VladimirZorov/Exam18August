import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Kat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] licensePlates = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] cars = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> licensePlatesQueue = new ArrayDeque<>();
        for (int v : licensePlates) {
            licensePlatesQueue.offer(v);
        }
        Deque<Integer> carsStack = new ArrayDeque<>();
        for (int v : cars) {
            carsStack.push(v);
        }


        int daysCount = 0;
        int registeredCarsCount = 0;
        int restLicesePlatesCount = 0;
        int carsWithoutLicenseCount = 0;


        while (!(licensePlatesQueue.size() ==0) && !(carsStack.size() ==0)) {

            daysCount++;

            int restCarForDay = 0;
            int restPlatesForDay = 0;

            int todayUsedLicensePlates = licensePlatesQueue.peek();
            int todayCarsReady = carsStack.peek();

            if ((todayUsedLicensePlates / 2) == todayCarsReady) {
                licensePlatesQueue.poll();
                carsStack.pop();
                registeredCarsCount += todayCarsReady;
            } else if ((todayUsedLicensePlates / 2) > todayCarsReady) {
                registeredCarsCount += todayCarsReady;
                carsStack.pop();
                restPlatesForDay = todayUsedLicensePlates - todayCarsReady*2;
                licensePlatesQueue.poll();
                licensePlatesQueue.addLast(restPlatesForDay);
            } else if ((todayUsedLicensePlates / 2) < todayCarsReady) {
                registeredCarsCount += (todayUsedLicensePlates/2);
                licensePlatesQueue.poll();
                restCarForDay = todayCarsReady - (todayUsedLicensePlates/2);
                carsStack.pop();
                carsStack.addLast(restCarForDay);

            }


        }

        if (licensePlatesQueue.size()==0) {
            for (Integer n :carsStack) {
                carsWithoutLicenseCount += n;
            }
        }

        if (carsStack.size() == 0) {
            for (Integer n : licensePlatesQueue) {
                restLicesePlatesCount += n;
            }
        }
        System.out.printf("%d cars were registered for %d days!%n",registeredCarsCount, daysCount);

        if (restLicesePlatesCount > 0){
            System.out.printf("%d license plates remain!%n", restLicesePlatesCount);
        }

        if (carsWithoutLicenseCount > 0) {
            System.out.printf("%d cars remain without license plates!%n", carsWithoutLicenseCount);
        }

        if (carsWithoutLicenseCount == 0 && restLicesePlatesCount == 0) {
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
    }
}
