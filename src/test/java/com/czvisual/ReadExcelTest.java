package com.czvisual;



import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.czvisual.entity.Data;
import com.czvisual.excel.ExcelListener;
import com.czvisual.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReadExcelTest {
    @Autowired
    DataService dataService;

    @Test
    public void readTest() {
        String fileName = "4.xlsx";

        EasyExcel.read(fileName, Data.class, new ExcelListener<>(dataService, "lysd_4")).sheet().headRowNumber(3).doRead();
    }
}
