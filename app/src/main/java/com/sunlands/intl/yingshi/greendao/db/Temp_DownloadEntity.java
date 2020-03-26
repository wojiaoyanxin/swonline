package com.sunlands.intl.yingshi.greendao.db;

import com.arialyy.aria.core.download.DownloadEntity;
import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author yxin
 * @date 2020-03-02 - 11:24
 * @des
 */
public class Temp_DownloadEntity  implements PropertyConverter<DownloadEntity, String> {


    @Override
    public DownloadEntity convertToEntityProperty(String databaseValue) {
        return new Gson().fromJson(databaseValue, DownloadEntity.class);
    }

    @Override
    public String convertToDatabaseValue(DownloadEntity entityProperty) {
        return new Gson().toJson(entityProperty);
    }


}
