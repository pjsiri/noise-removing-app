/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noiseremoving;

/**
 *
 * @author pj
 * @param <E>
 */
public class CollectionSort <E extends Comparable<E>>{
    
    private E[] array;

    public void setArray(E[] array) 
    {
        this.array = array;
    }
    
    public void quickSort()
    {
        if(getArray() == null)
        {
            return;
        }
        
        quickSort(0,getArray().length - 1);
    }
       
    private void quickSort(int h, int t)
    {
        if(h < t)
        {
            int fromHead = h;
            int fromTail = t;
            
            E pivot = getArray()[fromHead + (fromTail - fromHead) / 2];
            
            while(fromHead <= fromTail)
            {
                while(getArray()[fromHead].compareTo(pivot) < 0)
                {
                    fromHead++;
                }
                
                while(getArray()[fromTail].compareTo(pivot) > 0)
                {
                    fromTail--;
                }
                
                // If fromHead is less than or equal to fromTail. Then, Swap the elements fromHead, fromTail.
                // Then, increment fromHead and decrement fromTail.
                if(fromHead <= fromTail)
                {
                    swap(fromHead,fromTail);
                    fromHead++;
                    fromTail--;
                    
                }
            }
            
            if(h < fromTail)
            {
                // Recursively call quicksort for the elements before the pivot.
                quickSort(h,fromTail);
            }
            if(fromHead < t)
            {
                // Recursively call quicksort for the elements after the pivot.
                quickSort(fromHead,t);
            }
        }
    }
    
    private void swap(int x,int y)
    {
        E temp = getArray()[x];
        array[x] = getArray()[y];
        array[y] = temp;
    }

    public E[] getArray() 
    {
        return array;
    }
}
