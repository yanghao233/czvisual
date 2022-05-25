package com.czvisual.mapper;

import com.czvisual.entity.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {
    int insertData(Data data, String table);

    int insertDataBatch(List<Data> dataList, String table);
}
