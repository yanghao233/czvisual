package com.czvisual.service;

import com.czvisual.entity.Hotspring;
import com.czvisual.mapper.HotspringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotspringService {
    @Autowired
    private HotspringMapper hotspringMapper;

    public List<Hotspring> findAll() {
        return hotspringMapper.findAll();
    }

    public List<Hotspring> findFilteredAll(String area, String commonType, String heatDisplayType, String heatDamageType, String chemicalCompositionType) {
        return hotspringMapper.findFilteredAll(area, commonType, heatDisplayType, heatDamageType, chemicalCompositionType);
    }

    public List<String> findTypeByGroup(String groupParam) {
        return hotspringMapper.findTypeByGroup(groupParam);
    }

    public Integer[] addHotSpring(Hotspring hotspring) {
        int i = hotspringMapper.addHotSpring(hotspring);
        int j = hotspringMapper.addHotSpringTable(hotspring.getTablename());
        return new Integer[]{i,j};
    }


    public Integer updateHotSpring(Hotspring hotspring) {
        return hotspringMapper.updateHotSpring(hotspring);
    }

    public int[] delHotSpring(String  tableName) {
        int i = hotspringMapper.delHotSpring(tableName);
        int j = hotspringMapper.delHotSpringTable(tableName);
        return new int[]{i,j};
    }
}
