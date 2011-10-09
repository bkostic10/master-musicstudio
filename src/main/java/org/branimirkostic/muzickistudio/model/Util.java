package org.branimirkostic.muzickistudio.model;

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
	
}
