/*
 * This program is distributed under LGPL.
 * 
 * Source:
 * http://code.google.com/p/yooreeka/source/browse/tags/Book-final-code/tags/Final+code+of+the+book/src/iweb2/ch5/ontology/core/DoubleAttribute.java
 */
package org.rondhuit.bayes;

public class DoubleAttribute implements Attribute {

        public static final Double DEFAULT_VALUE = 0.0;
        
        String name;
        Double value;
        
        public DoubleAttribute(String name, Double value) {
                this.name = name;
                this.value = value;
        }
        
        /* (non-Javadoc)
         * @see iweb2.ch2.data.Attribute#getName()
         */
    @Override
        public String getName() {
                return name;
        }

        /* (non-Javadoc)
         * @see iweb2.ch2.data.Attribute#getValue()
         */
        public Object getValue() {
                return value;
        }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final DoubleAttribute other = (DoubleAttribute) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }
}