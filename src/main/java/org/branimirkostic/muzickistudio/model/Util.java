package org.branimirkostic.muzickistudio.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {
//	public static Long generisanjeTerminId(List<Termin> termini){
//		long maxIndeks = 0;
//		if(termini != null && termini.size() > 0){
//			for(Termin t : termini){
//				if(t.getId() > maxIndeks){
//					maxIndeks = t.getId();
//				}
//			}
//		}
//		return maxIndeks + 1;
//	}
    public static boolean daLiJeKorisnikovTermin(List<Termin> termini, Long terminId){
        boolean ok = false;
        if(termini == null || termini.size() == 0){
            return ok;
        }
        else{
            for(Termin termin : termini){
                if(termin.getId().getId().equals(terminId)){
                    ok = true;
                    break;
                }
            }
        }
        return ok;
    }

    public static List<String> getDatum(){
        List<String> dates = new ArrayList<String>();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int _day = now.getDate();
        String _dan = (_day < 10 ) ? "0"+_day : ""+_day;
        int _month = now.getMonth() + 1;
        String _mesec = (_month < 10 ) ? "0"+_month : ""+_month;
        int _year = now.getYear() + 1900;
        String s1 = _dan+"."+_mesec+"."+_year;
        dates.add(s1);
        for(int i=0;i<7;i++){
            cal.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = cal.getTime();
            int _day1 = tomorrow.getDate();
            String _dan1 = (_day < 10 ) ? "0"+_day1 : ""+_day1;
            int _month1 = tomorrow.getMonth() + 1;
            String _mesec1 = (_month1 < 10 ) ? "0"+_month1 : ""+_month1;
            int _year1 = tomorrow.getYear() + 1900;
            String s = _dan1+"."+_mesec1+"."+_year1;
            dates.add(s);
        }
        return dates;
    }
	
}
