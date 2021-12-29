package eu3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import pxAnalyzer.PXAdvancedAnalyzer;
import pxAnalyzer.PXTree.StructField;
import pxAnalyzer.PXTree.BaseField;

public class EU3mod {

	static public final String version = "1.41-RUC";

	final static String _myMod = "Crusader Kings Conversion v "+version;
	final static String _modMod = "CK Conversion v "+version;
	
	public static void setMod(String fname, String orig) throws IOException {
		StructField root;
		if (orig!=null) {
			root = new PXAdvancedAnalyzer(orig,true).analyze();
			boolean found=false;
			for (pxAnalyzer.PXTree.Field<?> f : root._data) {
				BaseField g = (BaseField)f;
				if (f.name().equals("name")) {
					g.set(String.format(Locale.US,"\"%s (%s)\"", _modMod, g.getUnquoted()));
					continue;
				}
				if (g.get().equals("history")) {
					found=true;
					break;
				}
			}
			if (!found) {
				root.addField(new BaseField("extend","\"history\""));
			}
		}
		else {
			root=new StructField("");
			root._layout = StructField.Layout.INDENT;
			root.addField(new BaseField("name",String.format(Locale.US,"\"%s \"", _myMod)));
			root.addField(new BaseField("extend","\"history\""));
		}
		PrintWriter pw = new PrintWriter(fname,"ISO-8859-1");
		root.write(pw,false);
		pw.close();
	}
	
}
