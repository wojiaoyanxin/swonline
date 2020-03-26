package com.sunlands.intl.yingshi.bean;

/**
 * Created by yanxin on 2019/3/1.
 */

public class IMBean {


    /**
     * userSig : eJxNjtFOg0AQRf9lXzVmZ2ELmPhASRtrWpOKrZaXDYEFZxsXXNaWYvz3AsHEeTxn7p35Ia-r*C7NsupbW2EvtST3hJLbEWMutcUCpekhODAD4G7geZNO6xpzkVrhmPxfqsmPYlRDyKUUmMuYP0nZ1mikSAs7lvqzfmFSJ2karHRPGQUOzKHDTNLi5-BZf99nANT-62uw7PFmsYtWYdwpqededuAtYnCTJZ088SRa7inl5eP2qQOpPmhwVocQF2H7lfLCKeHFXc-jZHXZHkOz2-vP*q2j71W8gahcZkqpJjo-kN8rwbNXbw__
     * accountType : 34599
     * sdkAppID : 1400124228
     * identifier : 13161154977
     */

    private String userSig;
    private String accountType;
    private String sdkAppID;
    private String identifier;

    public String getUserSig() {
        return userSig;
    }

    public void setUserSig(String userSig) {
        this.userSig = userSig;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSdkAppID() {
        return sdkAppID;
    }

    public void setSdkAppID(String sdkAppID) {
        this.sdkAppID = sdkAppID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "IMBean{" +
                "userSig='" + userSig + '\'' +
                ", accountType='" + accountType + '\'' +
                ", sdkAppID='" + sdkAppID + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
