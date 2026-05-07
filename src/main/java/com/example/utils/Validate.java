package com.example.utils;

public class Validate {
  public static boolean isEmptyOrNull(String str) {
    return str == null || str.trim().isEmpty();
  }
  
  public static boolean isValidPhoneNumber(String phone) {
    String phonePattern = "^(0[3|5|7|8|9])+([0-9]{8})$";
    return phone != null && phone.matches(phonePattern);
  }
  
  public static boolean isValidEmail(String email) {
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email != null && email.matches(emailPattern);
  }
  
  public static boolean isValidSalary(String salaryStr) {
    try {
      double salary = Double.parseDouble(salaryStr.replace(",", "").replace(".", ""));
      return salary > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
  
  public static boolean isValidLength(String str, int min, int max) {
    return str != null && str.length() >= min && str.length() <= max;
  }
}
