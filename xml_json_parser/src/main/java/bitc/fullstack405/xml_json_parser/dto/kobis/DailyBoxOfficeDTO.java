package bitc.fullstack405.xml_json_parser.dto.kobis;

import lombok.Data;

@Data
public class DailyBoxOfficeDTO {
    public String rnum;
    public String rank;
    public String rankInten;
    public String rankOldAndNew;
    public String movieCd;
    public String movieNm;
    public String openDt;
    public String salesAmt;
    public String salesShare;
    public String salesInten;
    public String salesChange;
    public String salesAcc;
    public String audiCnt;
    public String audiInten;
    public String audiChange;
    public String audiAcc;
    public String scrnCnt;
    public String showCnt;
}