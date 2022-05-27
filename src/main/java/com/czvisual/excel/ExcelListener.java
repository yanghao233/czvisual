package com.czvisual.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.czvisual.entity.Data;
import com.czvisual.service.DataService;

import java.util.List;

public class ExcelListener<T> implements ReadListener<T> {
    private DataService dataService;
    private String table;
    public static final int BATCH_COUNT = 100;
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cachedDataList.add(t);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    public void saveData() {
//        for(T t : cachedDataList) {
//            System.out.println(t);
//        }
        dataService.insertDataBatch((List<Data>) cachedDataList, table);
    }

    public ExcelListener(DataService dataService, String table) {
        this.dataService = dataService;
        this.table = table;
    }

    //TODO: 错误处理
}
