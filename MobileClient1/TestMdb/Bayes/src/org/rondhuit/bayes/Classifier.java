/*
 * This program is distributed under LGPL.
 * 
 * Source:
 * http://code.google.com/p/yooreeka/source/browse/tags/Book-final-code/tags/Final%20code%20of%20the%20book/src/iweb2/ch5/classification/core/intf/Classifier.java
 */
package org.rondhuit.bayes;

/** 
 * Every classifier must be:
 * <UL> 
 *   <LI> able to load a <CODE>TrainingSet</CODE>, and </LI>
 *   <LI> able to classify an <CODE>Instance</CODE></LI>
 * </UL>
 * 
 * This interface reflects these two elementary methods.
 * 
 * @author babis
 */
public interface Classifier {
  public String getName();
        public boolean train();
        public Concept classify(Instance instance);
}