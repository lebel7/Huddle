package com.lebel.huddle;

import android.content.Context;

import com.ianywhere.ultralitejni12.ConfigFileAndroid;
import com.ianywhere.ultralitejni12.Connection;
import com.ianywhere.ultralitejni12.DatabaseManager;
import com.ianywhere.ultralitejni12.ULjException;


class UltraDatabaseHelper {
    private static UltraDatabaseHelper _instance = null;
    private static Connection _dbconn;
//    private static final int defaultPort = 2639;
    private static final String defaultUsername = "dba";
    private static final String defaultPassword = "sql";
    private static final String db = "lebeldb.udb";
//    private static final String LOG_EXCEPTION = "Database Connection Error";
    Context _context;
    private UltraDatabaseHelper(Context context) throws ULjException {

        _context = context;
        if( _dbconn == null ){
            ConfigFileAndroid config;
            config = DatabaseManager.createConfigurationFileAndroid(db, _context);
            config.setUserName(defaultUsername);
            config.setPassword(defaultPassword);
            config.setPageSize(8192);
            try{
                _dbconn = DatabaseManager.connect(config);
            }
            catch(Exception e) {
                _dbconn = DatabaseManager.createDatabase(config);
            }
        }

    }
    protected static UltraDatabaseHelper getInstance(Context context) throws ULjException {
        if(_instance == null){
            _instance = new UltraDatabaseHelper(context);
        }
        return _instance;
    }
}
