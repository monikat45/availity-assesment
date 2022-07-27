package service;

import model.EnrollmentData;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.EndpointReference;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class EnrollmentProcess {
    MyCSVWriter myCSVWriter;

    public EnrollmentProcess() {
        this.myCSVWriter = new MyCSVWriter();
    }

    public List<File> filterCSVFiles() {
        //Read all log files
        List<File> filesInFolder = null;
        try {
            String extention = ".csv";
            filesInFolder = Files.walk(Paths.get("./src/main/resources/enrollments"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> file.getName().endsWith(extention))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error in reading the log files" + e);
        }
        return filesInFolder;
    }

    public Map<String, List<EnrollmentData>> convertCSVFileDataToEnrollmentData(File file) {
        Map<String, List<EnrollmentData>> map = new HashMap<>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(file));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] enrollmentRecord = line.split(splitBy);
                EnrollmentData record = null;
                List<EnrollmentData> enrollmentDataList = null;
                record = getEnrollmentData(enrollmentRecord);
                if (map.containsKey(enrollmentRecord[3])) {
                    enrollmentDataList = map.get(enrollmentRecord[3]);
                    performDuplicateUserChange(enrollmentDataList, record);
                } else {
                    enrollmentDataList = new ArrayList<>();
                    enrollmentDataList.add(record);
                }
                map.put(record.getInsuranceCompany(), enrollmentDataList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void sortEnrolleesByNameForEachInsuranceCompany(EnrollmentProcess enrollmentProcess,
            List<File> filesInFolder) {
        for (File file : filesInFolder) {
            Map<String, List<EnrollmentData>> directoryMap = enrollmentProcess.convertCSVFileDataToEnrollmentData(file);
            Comparator<EnrollmentData> comparator = Comparator.comparing(EnrollmentData::getFirstAndLastName);
            directoryMap.values().forEach(l -> l.sort(comparator));
            myCSVWriter.writeResultsToCSV(directoryMap, file);
            printResultMap(directoryMap);
        }

    }

    private static int compare(EnrollmentData e1, EnrollmentData e2) {
        return e1.getFirstAndLastName().compareTo(e2.getFirstAndLastName());
    }

    private void printResultMap(Map<String, List<EnrollmentData>> resultMapWithSorting) {
        resultMapWithSorting.forEach((k, v) -> System.out.println("Insurance Company : " + k + "- data : " + v));
    }

    private EnrollmentData getEnrollmentData(String[] enrollmentRecord) {
        EnrollmentData record;
        record = new EnrollmentData();
        record.setUserId(enrollmentRecord[0].trim());
        record.setFirstAndLastName(enrollmentRecord[1].trim());
        record.setVersion(Integer.parseInt(enrollmentRecord[2].trim()));
        record.setInsuranceCompany(enrollmentRecord[3].trim());
        return record;
    }

    private void performDuplicateUserChange(List<EnrollmentData> enrollmentDataList, EnrollmentData enrollment) {
        final boolean[] isExistingUser = {false};
        enrollmentDataList.stream().forEach(enrollmentData -> {
            if (enrollmentData.getUserId().contentEquals(enrollment.getUserId()) &&
                    enrollmentData.getVersion() < enrollment.getVersion()) {
                isExistingUser[0] = true;
                enrollmentData.setVersion(enrollment.getVersion());
            }
        });
        if (!isExistingUser[0]) {
            enrollmentDataList.add(enrollment);
        }
    }
}
