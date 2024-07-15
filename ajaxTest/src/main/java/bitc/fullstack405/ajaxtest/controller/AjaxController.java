package bitc.fullstack405.ajaxtest.controller;

import bitc.fullstack405.ajaxtest.dto.AreaDTO;
import bitc.fullstack405.ajaxtest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AjaxController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

//    ajax를 사용하지 않는 계산기의 사용자 입력 폼
    @RequestMapping("/ajax/nonAjaxCalView")
    public String nonAjaxCalView(){
        return "/ajax/nonAjaxCalView";
    }

//    ajax를 사용하지 않는 계산기의 연산 결과 뷰
    @RequestMapping("/ajax/nonAjaxCalResult")
    public ModelAndView nonAjaxCalResult(@RequestParam("num1") int num1, @RequestParam("num2") int num2){
        ModelAndView mv = new ModelAndView("/ajax/nonAjaxCalResult");

        int result = num1 + num2;
        mv.addObject("num1", num1);
        mv.addObject("num2", num2);
        mv.addObject("result", result);
        return mv;
    }

    @RequestMapping("/ajax/AjaxCalView")
    public String ajaxCalculator(){
        return "/ajax/AjaxCalView";
    }


    // @RequestParam: 클라이언트에서 전달한 데이터를 가져오는 어노테이션, jsp의 request.getParameter()와 같음
    // 매개변수를 추가하여 여러가지 옵션을 사용할 수 있음
    // value: 클라이언트에서 전달한 데이터의 파라미터 명을 지정
    // required: true(기본값)/false 로 설정, 해당 파라미터의 값이 없을 경우 오류 표시(false 사용 시 오류 미표시)
    // defaultValue: required 속성과 함께 사용하여 사용자가 해당 파라미터에 값을 입력하지 않을 경우 defaultValue로 설정 된 값을 사용

    // @RequestMapping: 해당 메서드와 사용자가 접속할 URL을 매칭하는 어노테이션
    // value: 사용자가 접속할 URL 설정
    // method: 사용자가 서버로 데이터 전달 시 사용한 통신 방식을 설정 (GET POST), Restful 방식 사용 시 GET, POST, PUT, DELETE를 사용할 수 있음

    // @ResponseBody: 응답으로 리턴할 데이터를 html 템플릿이 아닌 데이터 자체를 전달하도록 하는 어노테이션,
    // ajax 통신 시 반드시 필요
    @ResponseBody
    @RequestMapping(value ="/ajax/AjaxCalResult", method = RequestMethod.POST)
    public Object ajaxCalResult(@RequestParam("num1") int num1, @RequestParam("num2") int num2){
        int result = num1 + num2;

        // HashMap 타입 객체 생성
        Map<String, String> mapResult = new HashMap<>();
        // result라는 이름으로 success 저장
        mapResult.put("result", "success");
        // value라는 이름으로 변수 result의 값을 저장
        mapResult.put("value", String.valueOf(result));

        // Object 타입으로 변환 하지만, 스프링 프레임워크가 자동으로 json 타입으로 전달
        // json 타입으로 데이터 전달 시 자바에서 json 타입으로 변환 후 전달 해야 함(Gson 라이브러리 사용)
        return mapResult;
    }

    @RequestMapping(value = "/ajax/selectBox", method = RequestMethod.GET)
    public String selectBox(){


        return "/ajax/SelectBox";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/selectBox", method = RequestMethod.POST)
    public Object selectBox(@RequestParam("areaName") String areaName) throws Exception {

        List<AreaDTO> areaList = areaService.getAreaList(areaName);

        return areaList;
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/selectBox2", method = RequestMethod.POST)
    public Object selectBox2(@RequestParam("areaName") String areaName, @RequestParam("areaSubName") String areaSubName) throws Exception {

        List<AreaDTO> subAreaList = areaService.getSubAreaList(areaName, areaSubName);

        return subAreaList;
    }

    @RequestMapping(value = "/ajax/quiz1", method = RequestMethod.GET)
    public String ajaxCalQuiz(){
        return "/ajax/quiz1";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/quiz1", method = RequestMethod.POST)
    public Object calQuiz(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("cal") String cal) {

        Map<String, String> resultMap = new HashMap<>();
        int result = 0;
        if (cal.equals("+")) {
            result = num1 + num2;
        } else if (cal.equals("-")){
            result = num1 - num2;
        } else if (cal.equals("*")) {
            result = num1 * num2;
        } else if (cal.equals("/")) {
            result = num1 / num2;
        }

        resultMap.put("result", "success");
        resultMap.put("value", String.valueOf(result));
        return resultMap;
    }
}


