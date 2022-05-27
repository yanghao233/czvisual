package com.czvisual.mapper;

import com.czvisual.entity.Hotspring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotspringMapper {
    public List<Hotspring> findAll();
    public List<String> findTypeByGroup(String groupParam);
}
