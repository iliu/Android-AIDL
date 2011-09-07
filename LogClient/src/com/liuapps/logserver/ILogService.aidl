package com.liuapps.logserver; 

import com.liuapps.logserver.Message; 

interface ILogService { 
  void log_d(String tag, String message);  
  void log(in Message msg); 
  void you_log(String log);
   
}