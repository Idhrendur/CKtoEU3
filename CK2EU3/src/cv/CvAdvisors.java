package cv;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import rules.CharacterCvRules.Advisor;
import rules.CharacterCvRules.Leader;
import ck.Analyzer;
import ck.Characters;
import eu3.EU3Advisor;

public class CvAdvisors {
	//will create leaders too, so as to only make one pass on the (long) character list
	
	static private String  __destPath;
	
	static private LinkedList<Advisor> __list = new LinkedList<Advisor>();
	
	static private boolean __converted;

	static public void setOutputPath(String path) throws IOException {
		__destPath = path;
		File dir = new File(path);
		if (dir.exists()) {
//			for (File f: dir.listFiles())
//				f.delete();
		}
		else if (!dir.mkdirs())
			throw new IOException("Unable to create directory : "+dir);
	}
	
	static private void convertAdvisors() {
		for (Characters x : Characters.__list) {
			Advisor a = Advisor.getAdvisor(x);
			if (a==null || a._location==null) continue;
			if (a._type!=null)
				__list.add(a);
			if (a._stats._courtPos==4)  //4 is marshall ; he may be Advisor AND Leader
				a._location._leader = Leader.getLeader(x);
		}
	}

	static public void calcEU3Advisors() {
		if (!__converted) {
			convertAdvisors();
			for (Advisor a : __list) {
				Characters.Stats s=a._stats;
				EU3Advisor.addAdvisor(s._name,a._location._capital._id,(int)a._skill,a._type,s._birth,s._month,s._day,s._death);
			}
			__converted = true;
		}
	}

	static public void writeAll() throws IOException {
		calcEU3Advisors();
		EU3Advisor.write(__destPath + File.separatorChar + Analyzer.getSaveFile());
	}	
}
