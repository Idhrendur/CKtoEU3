/**
 * 
 */
package utils;

import pxAnalyzer.PXTree.Field;

public interface FieldLoadable {
	boolean load(Field<?> f);  //loads data from Field f
}