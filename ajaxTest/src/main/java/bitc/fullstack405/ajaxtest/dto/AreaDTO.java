package bitc.fullstack405.ajaxtest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AreaDTO {
    private String areaName;
    private ArrayList<String> subAreas;

    public AreaDTO(String areaName) {
        this.areaName = areaName;
    }

    private List<String> subAreaNames() {
        List<String> subAreas = new ArrayList<>();

        subAreas.add(this.areaName + "동구");
        subAreas.add(this.areaName + "서구");
        subAreas.add(this.areaName + "남구");
        subAreas.add(this.areaName + "북구");

        subAreas.add(this.areaName);

        return subAreas;
    }

    private List<String> subSubAreaNames(String subAreaName){
        List<String> subSubAreas = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            subSubAreas.add(this.areaName + " " + subAreaName + i + "동");
        }

        return subSubAreas;
    }
}
