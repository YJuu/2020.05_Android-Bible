package com.YJuu.fhl;

import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;


public class KakaoSDKAdapter extends KakaoAdapter{
    //로그인시 사용될 Session 옵션설정
    @Override
    public ISessionConfig getSessionConfig(){
        return new ISessionConfig(){

            //로그인 시 인증타입 지정
            @Override
            public AuthType[] getAuthTypes(){
                return new AuthType[]{AuthType.KAKAO_ACCOUNT};
            }

            @Override
            public boolean isUsingWebviewTimer() { return false; }

            //로그인시 토큰 암호화여부
            @Override
            public boolean isSecureMode(){
                return false;
            }

            @Override
            public ApprovalType getApprovalType(){
                return ApprovalType.INDIVIDUAL;
            }

            @Override
            public boolean isSaveFormData(){
                return true;
            }
        };
    }

    //앱 정보를 얻는 인터페이스
    @Override
    public IApplicationConfig getApplicationConfig() {
        return new IApplicationConfig() {
            @Override
            public Context getApplicationContext() {
                return GlobalApplication.getGlobalApplicationContext();
            }
        };
    }
}
