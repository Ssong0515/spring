package bitc.fullstack405.ajaxtest.service;

import bitc.fullstack405.ajaxtest.dto.AreaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AreaService {
    List<AreaDTO> getAreaList(String areaName) throws Exception;

    List<AreaDTO> getSubAreaList(String areaName, String areaSubName) throws Exception;
}
