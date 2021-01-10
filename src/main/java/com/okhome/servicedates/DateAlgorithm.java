package com.okhome.servicedates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

interface JobCurator {
    void put(Date date);
    List<Date> get(Date after);
}

class DateOperations implements JobCurator {

    private static List<Date> DateList = new ArrayList<>();
    private static Date currentDate;

    @Override
    public void put(Date date) {
        DateList.add(date);
    }

    @Override
    public List<Date> get(Date after) {
        // format date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString;

        // current
        currentDate = after;

        // arr size
        int arraySize = DateList.size();

        // eliminate past date
        for (Date date: DateList) {
            if (date.compareTo(currentDate) < 0) {
                DateList.remove(DateList.indexOf(date));
            }
        }

        // sort dates
        Comparator<Date> sortDateAsc = new Comparator<Date>() {
            @Override
            public int compare(Date date1, Date date2) {
                return date1.compareTo(date2);
            }
        };

        // sorting
        Collections.sort(DateList, sortDateAsc);

        // print out
        for (Date date : DateList) {
            dateString = df.format(date);
            System.out.println(dateString);
        }

        return DateList;
    }
}

public class DateAlgorithm {

    // scanner
    private static final Scanner scanner = new Scanner(System.in);

    // psvm
    public static void main(String[] args) throws ParseException {

        DateOperations dateOps = new DateOperations();

        // scan amount
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n+1 ; i++) {

            // scan date
            String inString = scanner.nextLine();
            Date inDate = new SimpleDateFormat("yyyy-MM-dd").parse(inString);

            if (i == n) {
                // get current date
                dateOps.get(inDate);
            } else {
                // save date
                dateOps.put(inDate);
            }
        }

    }
}
