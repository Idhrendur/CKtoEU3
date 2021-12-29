Crusader Kings Save Game Mod by Yves Prélot.



------
OBJECT
------

This utility has been created in order to let you create a Europa Universalis III starting position
consistent with any Crusader Kings save game you may still have.

After converting a saved game, you will get a new mod installed in your Europa Universalis III game.
Start EUIII, select the mod and enjoy. Note that each new conversion will erase the previous one.

You may convert the save for vanilla EU3 ; you may also attempt to convert using your favorite mod.
In the latter case, I don't warrant anything as the conversion may mess things up.
Still, I have used it with no trouble on a MagnaMundi mod...
Of course, you will certainly lose all European historical information that the mod may have been using.
You may also encounter some troubles if the used mod makes significant changes such as map alterations
(in my magnamundi exemple, there were three available provinces to colonize in Europe, because they
were added by the mod).

The converter works for Crusader Kings or Deus Vult, and will convert to Europa Universalis 3,
Napoleon's Ampbitions or In Nomine. The recognition of which conversion is to be done should be
automatic.

You should not convert early saves unless you're ready for some unsavory stuff, like having no
buildings, no fortifications and backward sliders Europe wide : the converter makes heavy use of
the buildings and advances you have in your counties to generate EU3 province content and country
settings.

Consider ending your Crusader Kings game as near 1453 as possible (without In Nomine) or
somewhere between 1399 and 1453 if using In Nomine : the converted game will start at the date
you ended the previous game (In Nomine) or in 1453 (other versions).



---------
SPECIFICS
---------

This will convert:
* countries (owned/controlled provinces, sliders, accepted culture, capital, religion, government type)
* provinces (culture, religion, manpower, population size, buildings)
* political situation (alliances, wars, unions, vassalisation)
* king
* advisors
* marshall as a general

It will NOT convert:
* badboy
* stability
* technology level
* armies and more generally the way an ongoing war is going (except province occupation)
* treasury
* centers of trade
* curia control
* relationship levels between countries



-----------------
CONVERTING A GAME
-----------------

Use the CKmod.bat or the CKmod+.bat script (if you want to use a mod).

You will have to answer to some popups, at least the one requesting your saved game,
and a second one asking you generalities about how you would like the converter to
behave. Additional popups may ask for specific answers.


If this fails for some reason, you can resort to the command line and directly launch
the java executable in the following way:

java -Xms500m -Xmx500m -jar CKmod.jar CKinstallDir EU3installDir savegame EU3modToAdd

The -Xm parameters are required because the CK saves are usually quite large, and the java machine may require a lot of memory. In some special cases, you might even have to increase this.

The first parameter (CKinstallDir) is the full path to you installed Crusader Kings game.
The second parameter (EU3installDir) is the full path to you installed Europa Universalis III game.
The third parameter (savegame) is the name of the file to convert (with the .eug extension)
The last parameter (EU3modToAdd) is optional : if used, it is the name of the directory (in the mod directory under EU3installDir) containing the mod you want to use.

In that case, only the popup about the generalities about how you would like the converter to
behave should appear.



-----------------------
ADAPTING THE CONVERSION
-----------------------

Many of the choices for conversion are open. You may for exemple decide that all vassals belong
to their owning kingdom, or that some of them may break free under a vassalisation contract...
Or maybe all counties stick with their direct liege and all dukes become vassals...
There are a lot of things you can tweek if you wish. Just have a look at the cvdata.txt file if you
are interested in changing the way the converter work.

The last of the opening pop-up lets you choose from among the most important settings,
but there is a lot more that you can do if you tweak the cvdata file.



------------
REQUIREMENTS
------------

You will require an installed Java 5 or higher JRE or JDK to use the library or the programs.
You can find a free one at www.sun.com for example.


-------
CONTENT
-------

This should contain the following files:

CKmod.jar        The java archive to execute
Readme.txt       this file
CKmod.bat        A script to easily launch the conversion program
CKmod+.bat       A script to easily launch the conversion program using a mod
gpl-3.0.txt      The license file (Gnu Public License version 3)
cvdata.txt       Data file for the converter




------------
INSTALLATION
------------

Just unzip everything in any directory you see fit.




-------
CREDITS
-------

I admit to having stolen from Halsten his county to province conversion list (published in his thread) 
        and adapted it to my own needs. Everything else I have done myself.
I would also like to thank Richard Campbell (Ulmont) for his help in some parts of the code and some
        parts of the data file.
I would alos thank "rzhev from Aquarium of Lie" (as quoted from Ulmont message), whom I don't really know,
        for his help in adapting the county to province conversion list for In Nomine (which boasts
        new provinces).




---------------
VERSION HISTORY
---------------

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
         removed Germany and Italy as available tags in eu3 (as starting country) : if a title 
         holder possessions warrant it, eu3 will grant the title again
v 1.28   eliminated a bug introduced in 1.27 (no diplomatic relationships at the start); reworked
         the whole ownership/control part of the code (ck counties/ eu3 provinces), eliminating an
         unclear and bugprone section
v 1.30   improved the identification scheme for characters to avoid rare conflicts with equal ids
         but different types
         added fields in the cvdata.txt file to give better control on jewish and pagan religions
         conversions
         added country_convert_rules.country_modifier field in the cvdata.txt file to fine tune
         countries conversions
         added support for shiite / sunni traits fropm Deus Vult
v 1.31   eliminated a bug that made the country_modifier entry in cvdata mostly irrelevant.
         eliminated a bug causing gold/prestige/piety not to be accounted for when checking for
         independence.
         added yet another sanity check to detect inconsistencies in the vassalization links in
         the CK save (e.g. a duchy being vassal to another duchy) 
v 1.32   many thanks to Ulmont for his (quite large) co ntribution to this release.
         implemented a workaround to correctly convert non consistent saves with errors in the
         title list (a common occurence)
         implemented an automatic way to remerge countries when not enough tags available
         corrected a problem with shipyards and another with knight orders
         added support for multi tags entries in the tags section of the cvdata file in order
         to better select appropriate tags when converting
v 1.33   refactored the remerging of countries ; now the algorithm dynamically restricts
         the access to independence in a way consistent with the data file.
         data file now contains alternate tags to better direct tag conversion.
         culture conversion slightly modified. 
v 1.34   change to the logging system and elimination of the tee utility.
         inclusion of a secondary cvdata file for those who want italy/germany
         change in the algorithm to remerge countries to better account for special cases where
         this is not possible.
v 1.35   corrected typo in cvdata.txt file (TRY instead of TYR).
v 1.36   a release for almost nothing ; something got mess-up in the 1.35 upload, so a new, fresh
         release was in order.
v 1.37   corrected another typo (HAP instead of HAB). Added a check useful for those using mods
         (culture conversion table ok).
         corrected very minor issues in the logging system (exceptions not always showing).
         added a delay to terminate the program to give some time to read the result...
         slightly (oh so slight that you won't see a difference!) improved performances for those
         using JRE 1.6.
v 1.50   this is a major release, with many changes, the major ones beeing:
         support for In Nomine ; in particular
         - starting date depending on the CK save date (between 1399 and 1453)
         - new provinces/tags introduced in IN supported
         - support for the new IN advisor types
         autodetection of the kind of conversion to do (In Nomine, Napoleon Ambition or Vanilla EU3)
         ability to add new conversions and to force a pop-up to choose how to convert
         much faster than before (not that this is really usefull but it feels good!)
         better handling for The Azores
         monarchs/advisors stats better computed
         many other small improvements
v 1.51   corrects the manpower and buildings bug (no manpower, no buildings, eh...).
         In addition, provides an updated an tweaked datacv.txt file which generates provinces which
         should have a similar statistical profile than the historical setup in terms of population,
         manpower, taxes and buildings.
         Statistical tools can also be activated to show the profile of the generated mod and compare
         it with the historical setup.
v 1.52   corrects a tag assignation bug, and should probably handle tags somewhat better.
         corrects a bug making the unavailable tag list inoperant.
         lists the used and still available tags after the conversion in the statistical data for
         those willing to change the final assignation by hand picking better choices and modifying
         the mod directly.
v 1.53   Corrects an exception that sometimes happened.
v 1.54   Aggressive tag use : you can now get more countries converted than before.
v 1.55   Popup at start to unable the player to set up the most important settings for the conversion
         in terms of available tags and number of converted countries.
v 1.56   More settings in the popup (you can safely use default values).
         Some modifications to compute the values with the eu3base settings as the previous
         way of doing things did not give an acceptable statistical output. 
         Severe tweaks for several values, including EU3 slider positions for generated countries :
         the result should now be much closer to the original historical setting. The cvdata.txt
         file can now give good results for most values after conversion.
         Modifications to the way countries relationships were decided ; in particular, values affecting
         this in the data file are now floats rather than integer giving more freedom and modifier.
         possibilities ; in that regard, added a bunch of liege traits modifiers.
         In Nomine advisor types are now generated by the conversion.
         Unless bugs are found (a likely proposition), this should be the final version.  
v 1.57   Some bugs corrected, at least one of them caused the converter to crash silently.
v 1.58   Corrects a severe bug that has been there udetected for very long, which caused some muslim
         provinces to be erroneously tagged as being chrstian. This bug affected only provinces which
         where muslim at the end of the CK game, but which were supposed to be christian in the historical
         EU3 settup.
v 1.59   Eliminated a small bug when dealing with inconsistencies.
         