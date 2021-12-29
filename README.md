Crusader Kings Save Game Mod by Yves Prélot.


OBJECT
------

This utility has been created in order to let you create a Europa Universalis III starting position consistent with any Crusader Kings save game you may still have.

After converting a saved game, you will get a new CK_Converted.eu3 save game in your Europa Universalis III save game diretory. Start EUIII, select the CK_Converted save game and enjoy. Note that each new conversion will erase the previous one.

This save converts to EUIII with the Napoleon's Ambition expansion.

SPECIFICS
---------

This will convert:
* countries [owned provinces, sliders, accepted culture, capital, religion, government type]
* provinces [culture, religion, manpower, population size, buildings]
* political situation [alliances, wars, war score from battles]
* king
* advisors
* marshal as a general
* badboy
* stability
* relationship levels between countries (applying the standard EU3 logic to the relationships)
* treasury [within reason]

It will NOT convert:
* technology level [always the same in EU3 starting points]
* armies and locations [always 1/2 manpower in troops, 3/2 manpower in ships if you have a port]
* centers of trade
* curia control

CONVERTING A GAME
-----------------

Use the CKmod.bat.

If this fails for some reason, you can resort to the command line and directly launch
the java executable in the following way:

java -Xms500m -Xmx500m -jar CKmod.jar CKinstallDir EU3installDir savegame 

The -Xm parameters are required because the CK saves are usually quite large, and the java machine may require a lot of memory. In some special cases, you might even have to increase this.

The first parameter (CKinstallDir) is the full path to you installed Crusader Kings game.
The second parameter (EU3installDir) is the full path to you installed Europa Universalis III game.
The third parameter (savegame) is the name of the file to convert (with the .eug extension)

ADAPTING THE CONVERSION
-----------------------

Many of the choices for conversion are open. You may for example decide that all vassals belong to their owning kingdom, or that some of them may break free under a vassalisation contract... Or maybe all counties stick with their direct liege and all dukes become vassals... there are a lot of things you can tweek if you wish. Just have a look at the cvdata.txt file if you are interested in changing the way the converter work.

One of the things you may definitely have to tweak is the number of countries created by the converter: this converter never creates any new country : it makes it's best to use existing tags. This means that it can currently only support 156 countries. If the conversion fails because of a lack of european tags, you have two choices:
* change the country_convert_rules entry ; the easiest way would then be to decrease the values under the tier subentry (currently county=-2, duchy=0) ; this will make vassals more likely to stick with their lord. 

REQUIREMENTS
------------

You will require an installed Java 5 or higher JRE or JDK to use the library or the programs.
You can find a free one at www.sun.com for exemple.

This has been tested with EU3 version 1.3, but should work  for lesser versions.

Note that the way this converter works will likely make it compatible with later EU3 releases.


CONTENT
-------

This should contain the following files:

CKmod.jar        The java archive to execute
Readme.txt       this file
CKmod.bat        A script to easily launch the conversion program
gpl-3.0.txt      The license file (Gnu Public License version 3)
cvdata.txt       Data file for the converter
tee.exe			 For ease of logging.

INSTALLATION
------------

Just unzip everything in any directory you see fit.


CREDITS
-------

I admit to having stolen from Halsten his county to province conversion list (published in his thread) and adapted it to my own needs. Everything else I have done myself.


VERSIONS
--------

v 1.0    initial release
v 1.1    added five missing cultures in the cvdata.txt file
         added minor code for some of the most common errors encountered
         added compatibility for Deus Vult
         corrected major bug causing EU3 game to become stuck after some years
v 1.2    added file selectors / registry reading to search for the program parameters
         added control of provinces in existing wars or for rebels
         removed Timurid empire from acceptable county tag conversion ; it remains as a Kingdom of course
         corrected a major but harmless bug in province assignment
v 1.21   added message indicating current version at the program start
         added a new registry key to search the install directories
         relaxed the requirement that countries must have a capital
v 1.22   corrected bug related to a kingdom being vassal to another realm (Jerusalem)
v 1.23   corrected bug related to selecting initial install directories
v 1.24   added yet more registry keys to guess the install directories
v 1.25   resolved a rare bug related to province ownership
v 1.26   eliminated a stupid bug introduced in 1.25
v 1.27   eliminated a rare bug that prevented loading eu3 country data
         removed Germany and Italy as available tags in eu3 (as starting country) : if a title holder possessions warrant it, eu3 will grant the title again
v 1.28   eliminated a bug introduced in 1.27 (no diplomatic relationships at the start); reworked the whole ownership/control part of the code (ck counties/ eu3 provinces), eliminating an unclear and bugprone section
v 1.30   improved the identification scheme for characters to avoid rare conflicts with equal ids but different types
         added fields in the cvdata.txt file to give better control on jewish and pagan religions conversions
         added country_convert_rules.country_modifier field in the cvdata.txt file to fine tune countries conversions
         added support for shiite / sunni traits fropm Deus Vult
v 1.30.RUC now outputs a save file.
v 1.31   eliminated a bug that made the country_modifier entry in cvdata mostly irrelevant.
         eliminated a bug causing gold/prestige/piety not to be accounted for when checking for independence.
         added yet another sanity check to detect inconsistencies in the vassalization links in the CK save (e.g. a duchy being vassal to another duchy)
v 1.31.RUC corrected the table for the CK -> EU3 base tax conversion, and set "scale CK tax" as the default.
		 removed by default the "no overlord crown" rule.
		 removed NAJ and HED from convertable tags, and added SHR.
v 1.31.RUC-2 added a remerging step in the case that there are not enough tags for the number of desired independent countries.
         corrected a cvdata.txt missing entry that was causing games with knightly orders to not convert.
v 1.31.RUC-3 corrected a problem with shipyards, as well as overlords that didn't really exist.
v 1.32-RUC  added support for multi tag converstion from CK -> EU3 (multiple choices)
v 1.33-RUC resynch with Yvesp's version.
	 now takes the 1066 scenario province cultures as the baseline.
v 1.35-RUC resynch with Yvesp's version, also fix a bug with a typo TRY for TYR.
		 
