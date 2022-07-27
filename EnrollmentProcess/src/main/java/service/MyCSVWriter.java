package service;

import model.EnrollmentData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class MyCSVWriter {
    public void writeResultsToCSV(Map<String, List<EnrollmentData>> map, File file) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (List<EnrollmentData> list : map.values()) {
            for (EnrollmentData enrollmentData : list) {
                StringBuilder builder = new StringBuilder();
                builder.append(enrollmentData.getInsuranceCompany() + ",");
                builder.append(enrollmentData.getUserId() + ",");
                builder.append(enrollmentData.getVersion() + ",");
                builder.append(enrollmentData.getFirstAndLastName());
                builder.append('\n');
                pw.write(builder.toString());
            }
        }
        pw.close();

    }
}
