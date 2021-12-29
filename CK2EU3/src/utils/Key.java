/**
 * 
 */
package utils;

public interface Key<K extends Comparable<K>> {
	K getKey();
	void setKey(K k);
	void setSuccessor(K k);
}