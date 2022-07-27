import service.EnrollmentProcess;

import java.io.File;
import java.util.List;

public class EnrollmentApplication {
    public static void main(String[] args) {
        EnrollmentProcess enrollmentProcess = new EnrollmentProcess();
        List<File> filesInFolder = enrollmentProcess.filterCSVFiles();
        enrollmentProcess.sortEnrolleesByNameForEachInsuranceCompany(enrollmentProcess, filesInFolder);
    }

}
