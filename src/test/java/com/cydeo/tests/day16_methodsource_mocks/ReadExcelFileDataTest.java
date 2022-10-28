package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ReadExcelFileDataTest {
    @Test
    public void readBookItUsers(){
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath,"QA3");
        System.out.println("excelUtil.columnCount() = " + excelUtil.columnCount());
        System.out.println(excelUtil.getCellData(0,0));
        List<Map<String,String>>data = excelUtil.getDataList();
        System.out.println("data = " + data);
    }
}
