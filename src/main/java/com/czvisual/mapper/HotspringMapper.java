package com.czvisual.mapper;

import com.czvisual.entity.Hotspring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotspringMapper {
    List<Hotspring> findAll();
    List<String> findTypeByGroup(String groupParam);
    List<Hotspring> findFilteredAll(String area, String commonType, String heatDisplayType, String heatDamageType, String chemicalCompositionType);

    Integer addHotSpring(Hotspring hotspring);

    Integer addHotSpringTable(String tableName);

    Integer updateHotSpring(Hotspring hotspring);

    Integer delHotSpring(String tableName);

    Integer delHotSpringTable(String tableName);
}
