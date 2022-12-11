package com.skypro.group11.algoritm1;

import javax.swing.*;
import java.util.Arrays;

public class StringListImpl implements StringList {
    private final Spring[] storage;
    private int size;

    public StringListImpl() {
        storage = new Spring[10];
    }

    public StringListImpl(int initSize) {
        storage = new Spring[initSize];
    }

    @Override
    public Spring add(Spring item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Spring add(int index, Spring item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            storage[size++] = item;
            return item;
        }
        System.arraycopy(storage, index, storage, index+1, size-1);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Spring set(int index, Spring item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Spring remove(Spring item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {
            System.arraycopy(storage, index+1, storage, index, size-index);
        }
        size--;
        return item;
    }

    @Override
    public Spring remove(int index) {
        validateIndex(index);
        Spring item = storage[index];
        if (index != size) {
            System.arraycopy(storage, index+1, storage, index, size-index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Spring item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Spring item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
                if (storage[i].equals(item)) {
                    return i;
                }
        }
        return -1;
    }

    @Override
    public Spring get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Spring[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Spring item) {//проверка элемента
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}
