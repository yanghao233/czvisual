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
    public List<String> findTypeByGroup(String groupParam) {
        return hotspringMapper.findTypeByGroup(groupParam);
    }
}
