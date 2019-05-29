package com.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.model.UserDetailDto;
import com.util.LogFormatter;
import com.util.ServiceResult;
import com.util.ServiceResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 使用HTTP方式封装的SDK
 */
@Component
public class HttpSDK {
    private static Logger mainLogger = LoggerFactory.getLogger(HttpSDK.class);
    private static final Logger bizLogger = LoggerFactory.getLogger("HTTP_INVOKE_LOGGER");


    public ServiceResult<String> getLoginUserId(String code, String accessToken) {
        try {
            String url = "https://oapi.dingtalk.com/user/getuserinfo?access_token="+accessToken+"&code="+code;
            String sr = HttpRequestHelper.httpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String userId = jsonObject.getString("userid");
                return ServiceResult.success(userId);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("code", code),
                    LogFormatter.KeyValue.getNew("accessToken", accessToken)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }


    public ServiceResult<UserDetailDto> getLoginUser(String userId, String accessToken) {
        try {
            String url = "https://oapi.dingtalk.com/user/get?access_token="+accessToken+"&userid="+userId;
            String sr = HttpRequestHelper.httpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                UserDetailDto detailDto = new UserDetailDto();
                detailDto.setName(jsonObject.getString("name"));
                detailDto.setUnionid(jsonObject.getString("unionid"));
                detailDto.setUserId(jsonObject.getString("userid"));
                detailDto.setIsLeaderInDepts(jsonObject.getString("isLeaderInDepts"));
                detailDto.setHiredDate(jsonObject.getTimestamp("hiredDate"));
                detailDto.setBoss(jsonObject.getBoolean("isBoss"));
//                detailDto.setDepartment(jsonObject.get("department"));
                detailDto.setSenior(jsonObject.getBoolean("isSenior"));
                detailDto.setActive(jsonObject.getBoolean("active"));
                return ServiceResult.success(detailDto);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("userid", userId),
                    LogFormatter.KeyValue.getNew("accessToken", accessToken)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }

    public ServiceResult<SuiteTokenVO> getSuiteToken(String suiteKey, String suiteSecret, String suiteTicket) {
        try {
            String url = "https://oapi.dingtalk.com/service/get_suite_token";
            JSONObject parmsObject = new JSONObject();
            parmsObject.put("suite_key", suiteKey);
            parmsObject.put("suite_secret", suiteSecret);
            parmsObject.put("suite_ticket", suiteTicket);

            String sr = HttpRequestHelper.httpPost(url, parmsObject.toJSONString());
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String accessToken = jsonObject.getString("suite_access_token");
                SuiteTokenVO suiteTokenVO = new SuiteTokenVO();
                suiteTokenVO.setSuiteToken(accessToken);
                suiteTokenVO.setExpiredTime(new Date(System.currentTimeMillis() + jsonObject.getLong("expires_in") * 1000));
                return ServiceResult.success(suiteTokenVO);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("suiteKey", suiteKey),
                    LogFormatter.KeyValue.getNew("suiteSecret", suiteSecret),
                    LogFormatter.KeyValue.getNew("suiteTicket", suiteTicket)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }


    public ServiceResult<CorpTokenVO> getCorpToken(String corpId,String pCode,String suitetoken) {
        try {
            String url = "https://oapi.dingtalk.com/service/get_corp_token?suite_access_token="+suitetoken;
            JSONObject parmsObject = new JSONObject();
            parmsObject.put("auth_corpid", corpId);
            parmsObject.put("permanent_code", pCode);
            String sr = HttpRequestHelper.httpPost(url,parmsObject.toJSONString());
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String accessToken = jsonObject.getString("access_token");
                CorpTokenVO corpTokenVO = new CorpTokenVO();
                corpTokenVO.setCorpToken(accessToken);
                corpTokenVO.setExpiredTime(new Date(System.currentTimeMillis()+jsonObject.getLong("expires_in")*1000));
                return ServiceResult.success(corpTokenVO);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("corpId", corpId),
                    LogFormatter.KeyValue.getNew("pCode", pCode),
                    LogFormatter.KeyValue.getNew("suitetoken", suitetoken)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }


    public ServiceResult<JSONObject> checkBookPermissions(String accessToken) {
        try {
            String url = "https://oapi.dingtalk.com/auth/scopes?access_token="+accessToken;
            String sr = HttpRequestHelper.httpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
//                UserDetailDto detailDto = new UserDetailDto();
//                detailDto.setName(jsonObject.getString("name"));
//                detailDto.setUnionid(jsonObject.getString("unionid"));
//                detailDto.setUserId(jsonObject.getString("userid"));
//                detailDto.setIsLeaderInDepts(jsonObject.getString("isLeaderInDepts"));
//                detailDto.setHiredDate(jsonObject.getTimestamp("hiredDate"));
//                detailDto.setBoss(jsonObject.getBoolean("isBoss"));
////                detailDto.setDepartment(jsonObject.get("department"));
//                detailDto.setSenior(jsonObject.getBoolean("isSenior"));
//                detailDto.setActive(jsonObject.getBoolean("active"));
                return ServiceResult.success(jsonObject);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("accessToken", accessToken)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }


    public ServiceResult<JSONObject> getAllDepartment(String accessToken, String pId) {
        try {
            String url = "https://oapi.dingtalk.com/department/list?access_token="+accessToken+ "&id=" + pId;
            String sr = HttpRequestHelper.httpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                return ServiceResult.success(jsonObject);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("accessToken", accessToken)
            );
            bizLogger.error(errLog, e);
            mainLogger.error(errLog, e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }
}
