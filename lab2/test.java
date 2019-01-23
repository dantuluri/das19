  /**
   * Sorts an array of objects (integers) using radix sort.
   * @param list  The unsorted list.
   * @return    The sorted list.
   */
  public static Object[] sort(Object[] list)
  {
    // Get the maximum number of digits.
    int maxDigits = getMaxDigits(list);
 
    // Iterate through the radix depending on max digits.
    for(int r=1; r <= maxDigits; r++){
 
      // Iterate through every number.
      int radix;
      for(int n=0; n < list.length; n++){
        // Figure out what queue to put it into.
        radix = getDigitAt(Integer.parseInt(list[n].toString()), r);
        // Put it into it's queue accordinmaxDigits = getMaxDigits(list);g to the radix.
        q[radix].offer(list[n]);
      }
 
      // Go through the queues and put the numbers back into the list.
      int a=0;
      for(int k=0; k < q.length; k++){
        // Go through every element in the queue.
        while(q[k].peek() != null){
          list[a++] = q[k].poll();
        }
      }
 
    }
 
    // Return the list, it is now sorted.
    return list;
 
  } 