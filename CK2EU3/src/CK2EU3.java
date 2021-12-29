import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.File;

import rules.CultureCvRules;
import rules.MergeRules;

import cv.*;
import ck.*;
import eu3.EU3Advisor;
import eu3.EU3Header;
import eu3.EU3LocalizedText;
import eu3.EU3Province;
import eu3.EU3Country;
import eu3.EU3Wars;
import utils.Log;
import utils.OptionSection;

public class CK2EU3 {
			
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		String save    = null;
		String CKpath  = null;
		String EU3path = null;
		String ModPath = null;
		String ModRoot = null;
		
		Log.init("ckmod.log");
		
		System.out.format("Crusader Kings to Europa Universalis III converter v %s\n\n",eu3.EU3mod.version);
			
		if (args.length<=1) {
			String CKreg = utils.RegRead.getCKpath();
			String EUreg = /* null; // */ utils.RegRead.getEUpath(); 
			File CK = (CKreg==null) ? utils.Selector.getCKDir()  : new File(CKreg);
			File EU = (EUreg==null) ? utils.Selector.getEU3Dir() : new File(EUreg);
			save    = utils.Selector.getSaveGame(CK).getAbsolutePath();
			if (args.length==1 && args[0].equals("+")) {
				File f  = utils.Selector.getModDir(EU);
				ModRoot   = (f!=null) ? f.getAbsolutePath() : null;
			}
			CKpath    = CK.getAbsolutePath() + File.separatorChar;
			EU3path   = EU.getAbsolutePath() + File.separatorChar;
		}
		else {
		    CKpath  = args[0]+"/";
		    EU3path = args[1]+"/";
		    ModRoot = (args.length>=4) ? EU3path+"mod/"+args[3] : null;
		    save = CKpath+"scenarios/save games/"+args[2];
		}
	    ModPath = (ModRoot!=null) ? ModRoot+"/" : null;
		
		PrintWriter out = new PrintWriter(System.out);
		EU3Province.setRootPath(EU3path,ModPath,"history/provinces");
		EU3Country.setRootPath(EU3path,ModPath,"history/countries");
		MergeRules.load(EU3path,ModPath,"cvdata.txt");
		CultureCvRules.EU3Culture.load(EU3path+"common/cultures.txt");
		System.out.println("************\nLOADING FILE\n************\n");
		System.out.flush();
		Analyzer.loadAnalyzer(CKpath,save);
		System.out.println("************************\nFILE LOADED... ANALYZING\n************************\n");
		CvCountry.setCountriesFile(EU3path+"common/countries.txt");
		CvCountry.makeCountries();
		CvCountry.purgeEmpty();
		CvProvince.load();
		CvProvince.assignAll();
		CvCountry.purgeNoProvince();
		CvCountry.checkCapitals();
		CvProvince.checkAllCulture();
		CvCountry.getAllTags();
		
		// now doing this in 2 passes, so that interrelating elements can be shared (relations need to know the diplomacy,
		// diplomacy needs to know the existing countries, etc.)
		EU3LocalizedText.init(EU3path);
		EU3Province.loadPorts(EU3path + "map/positions.txt");
		CvAdvisors.calcEU3Advisors(); // sets them up for use in province writing.
		EU3Advisor.loadDefaultAdvisors(EU3path + "history/advisors");
		CvCountry.calcTechGroups(); // sets them up for use in province writing.
		CvCountry.setOutputPath(EU3path + "save games");
		CvCountry.preWriteAll();
		CvProvince.setOutputPath(EU3path + "save games"); 
		CvProvince.preWriteAll();
		CvRelationships.setOutputPath(EU3path + "save games");
		CvRelationships.convertAlliances();
		CvRelationships.loadDefaultDiplomacy(EU3path + "/history/diplomacy");
		CvRelationships.preWriteAll();		
		CvWars.setOutputPath(EU3path+"save games");
		EU3Wars.loadDefaultWars(EU3path + "/history/wars");
		CvWars.preWriteAll();
		
		// pass 2...relations calculation.
		CvCountry.calcRelations();
		
		System.out.println("==> writing global header...");				
		EU3Header.eraseOutputFile(EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile());
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile(), true), "ISO-8859-1"));
		pw.println("date=\"" + OptionSection.getStartDate() + "\"");
		pw.close();		
		EU3Header.writeHeader("EU3GlobalHeader.txt", EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile());
		pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile(), true), "ISO-8859-1"));
		String hre = CvCountry.getEmperor();
		if (null != hre) {
			pw.println("emperor=\"" + hre + "\"");
		}
		pw.close();				
		System.out.println("==> writing provinces and their advisors...");		
		CvProvince.writeAll();
		System.out.println("==> writing countries");
		CvCountry.writeAll();
		System.out.println("==> writing active advisors");
		EU3Advisor.setOutputFile(EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile());
		EU3Advisor.writeActiveAdvisors();
		System.out.println("==> writing diplomacy");
		CvRelationships.writeAll();
		System.out.println("==> writing wars");
		CvWars.writeAll();
		EU3Header.writeHeader("EU3FinalHeader.txt", EU3path + "save games" + File.separatorChar + Analyzer.getSaveFile());  		
		System.out.println("\n********\nFINISHED\n********\n");
		for (CvCountry c : CvCountry.__list) c.print(out);
		out.format("%d countries left\n", CvCountry.__list.size());
		out.close();
	}
}
