package com.covidplus.service.impl;

import com.covidplus.dao.DashboardDao;
import com.covidplus.dao.KakaoBotDao;
import com.covidplus.service.KakaoBotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Service
public class KakaoBotServiceImpl implements KakaoBotService {
    @Autowired
    private KakaoBotDao kakaoBotDao;

    @Override
    public String corona(Map<String, Object> paramMap) {
        String date = (String) paramMap.get("date");
        String locate = (String) paramMap.get("locate");

        HashMap<String,String> param = new HashMap<>();
        param.put("date",date);
        param.put("locate",locate);
        if(locate!=null && locate.equals("전국"))
            param.put("locate","합계");
        List<Map<String,String>> result = kakaoBotDao.selectCoronaSido(param);

        if(result.size()>0) {
            param = (HashMap<String, String>) result.get(0);

            String resultDt = param.get("CREATE_DT").substring(0, 4) + "년 " + param.get("CREATE_DT").substring(4, 6) + "월 " + param.get("CREATE_DT").substring(6, 8) + "일";
            String incDec = String.valueOf(param.get("INC_DEC"));
            String gubun = param.get("GUBUN");
            if (gubun.equals("합계")) {
                gubun = "전국";
            } else {
                gubun = gubun + " 지역";
            }
            return "::"+resultDt+" "+gubun+" 코로나 확진자 수는 "+incDec+"명 입니다."+"::";
        }else{
            return "::현재 지원되는 지역은\n" +
                    "[서울][부산][인천][세종][대구][광주][대전][울산][제주]\n" +
                    "[경기][강원][경남][경북][충남][충북][전남][전북][전국]\n" +
                    "입니다.\n"+
                    "자세한 문의는 본체에게::";
        }

    }

    @Override
    public ModelAndView weather(Map<String,Object> paramMap) {
        ModelAndView modelAndView = new ModelAndView("kakaoresult");
        String locate_name = (String)paramMap.get("locate_name");
        if(locate_name == null) locate_name = "서울";
        String code = kakaoBotDao.selectLocateCode(locate_name);
        try {
            Map resultMap = (Map) getLandFcst(code).get("items");

            String dateType[] = new String[4];
            List<Map<String, Object>> resultlist = (List<Map<String, Object>>) resultMap.get("item");
            String result = locate_name + "날씨 (기상청 발표 " + String.valueOf(resultlist.get(0).get("announceTime")).substring(8, 10) + ":" + String.valueOf(resultlist.get(0).get("announceTime")).substring(10, 12) + "기준)#n";
            long date = (long) resultlist.get(0).get("announceTime") % 10000;
            if (date > 500 && date < 1100) {
                dateType[0] = "오늘 오전";
                dateType[1] = "오늘 오후";
                dateType[2] = "내일 오전";
                dateType[3] = "내일 오후";
                for (int i = 0; i < 4; i++) {
                    result = result + dateType[i] + " 기온 " + resultlist.get(i).get("ta") + "도 " + resultlist.get(i).get("wf") + "#n";
                }
            } else {
                dateType[0] = "오늘 오후";
                dateType[1] = "내일 오전";
                dateType[2] = "내일 오후";
                for (int i = 0; i < 3; i++) {
                    result = result + dateType[i] + " 기온 " + resultlist.get(i).get("ta") + "도 " + resultlist.get(i).get("wf") + "#n";
                }
            }
            result = result.substring(0, result.length() - 2);
            modelAndView.addObject("result", result);
        } catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("result", "잘못된 지역 명 혹은 뭔가 걍 잘못댐#n입력 지역명 : "+(String)paramMap.get("locate_name"));
        }
        return modelAndView;
    }

    @Override
    public ModelAndView inputProfile(Map<String, Object> paramMap) {
        ModelAndView modelAndView = new ModelAndView("kakaoresult");
        
        String user_name = (String) paramMap.get("user_name");
        String option_name = (String) paramMap.get("option_name");
        String option_value = (String) paramMap.get("option_value");

        try {
            switch (option_name) {
                case "지역":
                    kakaoBotDao.inputProfile(paramMap);
                    modelAndView.addObject("result", option_name + ":" + option_value + "적용완료");
                    break;
                default:
                    modelAndView.addObject("result", "지금은 지원하지 않는 옵션 인듯");
                    break;
            }
        }catch (Exception e){
            modelAndView.addObject("result", "뭔가 잘못된듯 하다");
        }
        return modelAndView;
    }

    Map<String,Object> getLandFcst(String code){
        StringBuilder sb = new StringBuilder();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=qZROU8a65Qg61gcpCNbiF8qSlva%2BjIT%2BunGeegk5cAlERCnjPYcMosujUQ49F7ZWt2TfOJdgy8Lowjinys6%2BeQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /*별첨 엑셀자료 참조(‘육상’ 구분 값 참고)*/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resultMap = new HashMap<>();

        try {
            resultMap = objectMapper.readValue(sb.toString() ,Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        resultMap = (Map<String, Object>) ((Map)resultMap.get("response")).get("body");

        return resultMap;
    }


}

