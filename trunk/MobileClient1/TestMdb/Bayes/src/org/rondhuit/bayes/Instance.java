/*
 * This program is distributed under LGPL.
 * 
 * Source:
 * http://code.google.com/p/yooreeka/source/browse/tags/Book-final-code/tags/Final+code+of+the+book/src/iweb2/ch5/ontology/intf/Instance.java
 */
package org.rondhuit.bayes;

/**
 * @author babis
 *
 */
public interface Instance {
        public Attribute[] getAtrributes();
        public Concept getConcept();
        public void print();
        public Attribute getAttributeByName(String attrName); 
}