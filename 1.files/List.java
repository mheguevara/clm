//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Collection<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object var1);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] var1);

    boolean add(E var1);

    boolean remove(Object var1);

    boolean containsAll(Collection<?> var1);

    boolean addAll(Collection<? extends E> var1);

    boolean addAll(int var1, Collection<? extends E> var2);

    boolean removeAll(Collection<?> var1);

    boolean retainAll(Collection<?> var1);

    void clear();

    boolean equals(Object var1);

    int hashCode();

    E get(int var1);

    E set(int var1, E var2);

    void add(int var1, E var2);

    E remove(int var1);

    int indexOf(Object var1);

    int lastIndexOf(Object var1);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int var1);

    List<E> subList(int var1, int var2);
}

