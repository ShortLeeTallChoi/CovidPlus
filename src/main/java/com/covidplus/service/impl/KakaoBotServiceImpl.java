package com.covidplus.service.impl;

import com.covidplus.dao.DashboardDao;
import com.covidplus.dao.KakaoBotDao;
import com.covidplus.service.KakaoBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
