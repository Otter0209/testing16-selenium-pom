package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

//        BufferedReader: doc du lieu trong file va luu vao bo nho dem (cache)
//        giup viec doc cac dong du lieu duoc nhanh hon
//        try-catch: vua handle happy case vua handle error case
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        try (reader) {
            String line;
            boolean isHeader = true;

//            doc lan luot tung dong du lieu -> line cuoi
            while ((line = reader.readLine()) != null) {
//                khong lay header lam du lieu
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

//                bo qua nhung dong trong (khong co du lieu)
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(",");
                data.add(values);
            }
        }

        return data;
    }

    //    sau khi doc file => chuan hoa du lieu de phu hop voi testng
//    => Object[][]: bang du lieu
//    - moi hang -> chay 1 lan test, 1 bo tham so (username, password, expected result)
//    - moi cot -> tung tham so
    public static Object[][] toDataProviderArray(List<String[]> rows) {
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }


}