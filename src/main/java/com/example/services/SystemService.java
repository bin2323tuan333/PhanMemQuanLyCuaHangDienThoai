package com.example.services;

import com.example.DTO.SystemSetting;
import com.example.repositories.SystemInfoRepository;

public class SystemService {
  public SystemSetting getSystemInfo() {
    SystemInfoRepository systemInfoRepository = new SystemInfoRepository();
    return systemInfoRepository.getSystemSetting();
  }
  
  public void updateSystemInfo(SystemSetting systemSetting) {
    SystemInfoRepository systemInfoRepository = new SystemInfoRepository();
    systemInfoRepository.updateSystemSetting(systemSetting);
  }
}
