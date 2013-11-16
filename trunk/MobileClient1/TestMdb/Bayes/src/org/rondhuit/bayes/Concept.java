/*
 * This program is distributed under LGPL.
 * 
 * Source:
 * http://code.google.com/p/yooreeka/source/browse/tags/Book-final-code/tags/Final%20code%20of%20the%20book/src/iweb2/ch5/ontology/intf/Concept.java
 */
package org.rondhuit.bayes;

/**
 * @author babis
 *
 */
public interface Concept {
        public String getName();
        public Concept getParent();
        public Instance[] getInstances();
}