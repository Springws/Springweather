package com.wusen.wsweather.Utils;

import com.google.gson.Gson;
import com.wusen.wsweather.Model.Model.ForecastInfo;
import com.wusen.wsweather.Model.Model.IndexInfo;
import com.wusen.wsweather.Model.Model.ResultInfo;
import com.wusen.wsweather.Model.Model.RetDataInfo;
import com.wusen.wsweather.Model.Model.TodayInfo;

import java.util.List;

/**
 * Created by 15059 on 2016/3/29.
 */
public class ParseUtils {

    //unicode解码
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static ResultInfo parseResult(String jsonDate){
        Gson gson = new Gson();
        ResultInfo resultInfo = gson.fromJson(jsonDate, ResultInfo.class);
        return resultInfo;
    }

    public static RetDataInfo parseRetdata(String jsonDate){
        RetDataInfo retDataInfo = parseResult(jsonDate).getRetData();
        return retDataInfo;
    }

    public static TodayInfo parseToday(String jsonDate){
        TodayInfo todayInfo = parseRetdata(jsonDate).getToday();
        return todayInfo;
    }

    public static List<IndexInfo> parseIndex(String jsonDate){
        List<IndexInfo> indexInfo = parseToday(jsonDate).getIndex();
        return indexInfo;
    }

    public static List<ForecastInfo> parseForecastInfo(String jsonDate){
        List<ForecastInfo>  forecastInfos = parseRetdata(jsonDate).getForecast();
        return forecastInfos;
    }

}
