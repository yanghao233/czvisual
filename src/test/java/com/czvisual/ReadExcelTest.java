package com.czvisual;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;



public class ReadExcelTest {
    

    public static void read() {
        String filename = "test.xlsx";
        EasyExcel.read(filename, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData Data : dataList) {
                System.out.println(Data);
            }
        })).sheet().doRead();
    }

    public static void main(String[] args) {
        read();
    }
}
