package bitc.fullstack405.ajaxtest.service;

import bitc.fullstack405.ajaxtest.dto.AreaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class AreaServiceImpl implements AreaService {
    @Override
    public List<AreaDTO> getAreaList(String areaName) throws Exception {
        List<AreaDTO> areaList = new ArrayList<>();

        areaList.add(new AreaDTO(areaName));

        return areaList;
    }

    @Override
    public List<AreaDTO> getSubAreaList(String areaName, String areaSubName) throws Exception {
        List<AreaDTO> areaList = new ArrayList<>();

        List<String> subList(String areaName, String areaSubName) {

        }

        if (areaName.equals("군산")) {
            AreaDTO se1 = new AreaDTO();
            AreaDTO se2 = new AreaDTO();
            AreaDTO se3 = new AreaDTO();
            AreaDTO se4 = new AreaDTO();

            se1.setAreaName("남구");
            se2.setAreaName("북구");
            se3.setAreaName("서구");
            se4.setAreaName("동구");

            se1.setAreaSubName("남구1동");
            se1.setAreaSubName("남구2동");
            se1.setAreaSubName("남구3동");
            se1.setAreaSubName("남구4동");

            se2.setAreaSubName("북구1동");
            se2.setAreaSubName("북구2동");
            se2.setAreaSubName("북구3동");
            se2.setAreaSubName("북구4동");

            se3.setAreaSubName("서구1동");
            se3.setAreaSubName("서구2동");
            se3.setAreaSubName("서구3동");
            se3.setAreaSubName("서구4동");

            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");

            areaList.add(se1);
            areaList.add(se2);
            areaList.add(se3);
            areaList.add(se4);

        } else if (areaName.equals("서울")) {

            AreaDTO se1 = new AreaDTO();
            AreaDTO se2 = new AreaDTO();
            AreaDTO se3 = new AreaDTO();
            AreaDTO se4 = new AreaDTO();

            se1.setAreaName("남구");
            se2.setAreaName("북구");
            se3.setAreaName("서구");
            se4.setAreaName("동구");

            se1.setAreaSubName("남구1동");
            se1.setAreaSubName("남구2동");
            se1.setAreaSubName("남구3동");
            se1.setAreaSubName("남구4동");

            se2.setAreaSubName("북구1동");
            se2.setAreaSubName("북구2동");
            se2.setAreaSubName("북구3동");
            se2.setAreaSubName("북구4동");

            se3.setAreaSubName("서구1동");
            se3.setAreaSubName("서구2동");
            se3.setAreaSubName("서구3동");
            se3.setAreaSubName("서구4동");

            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");

            areaList.add(se1);
            areaList.add(se2);
            areaList.add(se3);
            areaList.add(se4);

        } else if (areaName.equals("대전")) {
            AreaDTO se1 = new AreaDTO();
            AreaDTO se2 = new AreaDTO();
            AreaDTO se3 = new AreaDTO();
            AreaDTO se4 = new AreaDTO();

            se1.setAreaName("남구");
            se2.setAreaName("북구");
            se3.setAreaName("서구");
            se4.setAreaName("동구");

            se1.setAreaSubName("남구1동");
            se1.setAreaSubName("남구2동");
            se1.setAreaSubName("남구3동");
            se1.setAreaSubName("남구4동");

            se2.setAreaSubName("북구1동");
            se2.setAreaSubName("북구2동");
            se2.setAreaSubName("북구3동");
            se2.setAreaSubName("북구4동");

            se3.setAreaSubName("서구1동");
            se3.setAreaSubName("서구2동");
            se3.setAreaSubName("서구3동");
            se3.setAreaSubName("서구4동");

            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구1동");

            areaList.add(se1);
            areaList.add(se2);
            areaList.add(se3);
            areaList.add(se4);

        } else if (areaName.equals("부산")) {
            AreaDTO se1 = new AreaDTO();
            AreaDTO se2 = new AreaDTO();
            AreaDTO se3 = new AreaDTO();
            AreaDTO se4 = new AreaDTO();

            se1.setAreaName("남구");
            se2.setAreaName("북구");
            se3.setAreaName("서구");
            se4.setAreaName("동구");

            se1.setAreaSubName("남구1동");
            se1.setAreaSubName("남구2동");
            se1.setAreaSubName("남구3동");
            se1.setAreaSubName("남구4동");

            se2.setAreaSubName("북구1동");
            se2.setAreaSubName("북구2동");
            se2.setAreaSubName("북구3동");
            se2.setAreaSubName("북구4동");

            se3.setAreaSubName("서구1동");
            se3.setAreaSubName("서구2동");
            se3.setAreaSubName("서구3동");
            se3.setAreaSubName("서구4동");

            se4.setAreaSubName("동구1동");
            se4.setAreaSubName("동구2동");
            se4.setAreaSubName("동구3동");
            se4.setAreaSubName("동구4동");

            areaList.add(se1);
            areaList.add(se2);
            areaList.add(se3);
            areaList.add(se4);
        }
        return areaList;
    }
}
