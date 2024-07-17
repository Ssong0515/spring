package bitc.fullstack405.xml_json_parser.controller;

import bitc.fullstack405.xml_json_parser.dto.kobis.DailyBoxOfficeDTO;
import bitc.fullstack405.xml_json_parser.dto.pharmacy.FullDataItemDTO;
import bitc.fullstack405.xml_json_parser.service.ParserService;
import bitc.fullstack405.xml_json_parser.service.ParserServiceImpl;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parser")
public class ParserController {

    @Autowired
    private ParserService parserService;

    @Value("${fullstack405.pharmacy.service.key}")
    private String pharmacyServiceMyKey;

    @Value("${fullstack405.pharmacy.service.fulldata.url}")
    private String pharmacyServiceFullDataUrl;

    @Value("${fullstack405.kobis.service.dailyBoxOffice.url}")
    private String kobisServiceDailyBoxOfficeUrl;

    @Value("${fullstack405.kobis.service.key}")
    private String kobisServiceMyKey;



    @RequestMapping({"", "/"})
    public String index(){
        return "Index";
    }

    @RequestMapping("/pharmacy/fullDataFile")
    public ModelAndView getFullDataFile() throws JAXBException {
        ModelAndView mv = new ModelAndView("pharmacy/FullDataList");

        List<FullDataItemDTO> dataList = parserService.getItemListFile("C:/fullstack405/spring/pharmacy.xml");
        mv.addObject("dataList", dataList);

        return mv;
    }

    @ResponseBody
    @RequestMapping("/pharmacy/fullDataUrl")
    public Object getFullDataUrl(@RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows) throws Exception {
        // 1. URL 정보
//        https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?
//         serviceKey=2D5sJoXmsD8CoI52h815N5%2BZVk65v2TWE4PtROyX2fwzEYu9coqrKPPF33BJ2BxRJho6IkPWTLiHc%2FhlJfOfSA%3D%3D&pageNo=1&numOfRows=10

//        String serviceUrl = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        String optKey = "?serviceKey=";
        String opt1 = "&pageNo=";
        String opt2 = "&numOfRows=";

//        String serviceKey = "2D5sJoXmsD8CoI52h815N5%2BZVk65v2TWE4PtROyX2fwzEYu9coqrKPPF33BJ2BxRJho6IkPWTLiHc%2FhlJfOfSA%3D%3D";
//        String pageNo = "1";
//        String numOfRows = "10";

        // 2. 서비스에 URL 정보 전달
//        List<FullDataItemDTO> itemList = parserService.getItemListUrl(serviceUrl + optKey + serviceKey + opt1 + pageNo + opt2 + numOfRows);
        List<FullDataItemDTO> itemList = parserService.getItemListUrl(pharmacyServiceFullDataUrl + optKey + pharmacyServiceMyKey + opt1 + pageNo + opt2 + numOfRows);

        // 3. 파싱된 데이터를 클라이언트에 전달
        return itemList;
    }

    @RequestMapping("/kobis/dailyBoxOffice")
    public String dailyBoxOfficeView() throws Exception {
        return "kobis/DailyBoxOffice";
    }

    @ResponseBody
    @RequestMapping("/kobis/dailyBoxOfficeJson")
    public Object getDailyBoxOfficeJson(String targetDt) throws Exception {
        // 1. 서비스 url 생성
        // 2. 옵션 추가
        String optKey = "?key=" + kobisServiceMyKey;
        String opt1 = "&targetDt=" + targetDt;
        String serviceUrl = kobisServiceDailyBoxOfficeUrl + optKey + opt1;

        // 3. 서비스를 사용하여 정보 가져오기
        List<DailyBoxOfficeDTO> dailyBoxOfficeList = parserService.getDailyBoxOfficeList(serviceUrl);

        // 4. ajax 통신으로 클라이언트에 데이터 전달
        return dailyBoxOfficeList;
    }
}
