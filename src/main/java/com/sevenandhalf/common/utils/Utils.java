package com.sevenandhalf.common.utils;

public class Utils {

  public static String timeConverter(long time) {
    long seconds = time / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;
    long days = hours / 24;
    long months = days / 30;
    long years = months / 12;
    if (years > 0) {
      return years + " years";
    } else if (months > 0) {
      return months + " months";
    } else if (days > 0) {
      return days + " days";
    } else if (hours > 0) {
      return hours + "h";
    } else if (minutes > 0) {
      return minutes + " min";
    } else {
      return seconds + " sec";
    }
  }
}

