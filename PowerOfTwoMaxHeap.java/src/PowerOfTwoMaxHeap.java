import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {

    private final int childrenPowerExponent;
    private final int branchingFactor;
    private final List<T> elements;

    public PowerOfTwoMaxHeap(int childrenPowerExponent) {
        if (childrenPowerExponent < 0) {
            throw new IllegalArgumentException("childrenPowerExponent must be >= 0");
        }
        this.childrenPowerExponent = childrenPowerExponent;
        this.branchingFactor = 1 << childrenPowerExponent;
        this.elements = new ArrayList<>();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void insert(T element) {
        elements.add(element);
        siftUp(elements.size() - 1);
    }

    public T popMax() {
        if (elements.isEmpty()) {
            return null;
        }
        T maxValue = elements.get(0);

        T last = elements.remove(elements.size() - 1);
        if (!elements.isEmpty()) {
            elements.set(0, last);
            siftDown(0);
        }
        return maxValue;
    }

    private void siftUp(int index) {
        T current = elements.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / branchingFactor;
            T parent = elements.get(parentIndex);
            if (current.compareTo(parent) <= 0) {
                break;
            }
            elements.set(index, parent);
            elements.set(parentIndex, current);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        final int size = elements.size();
        T current = elements.get(index);

        while (true) {
            int firstChildIndex = index * branchingFactor + 1;
            if (firstChildIndex >= size) {
                break;
            }

            int largestChildIndex = firstChildIndex;
            T largestChildValue = elements.get(firstChildIndex);

            for (int i = 1; i < branchingFactor; i++) {
                int childIndex = firstChildIndex + i;
                if (childIndex >= size) {
                    break;
                }
                T childValue = elements.get(childIndex);
                if (childValue.compareTo(largestChildValue) > 0) {
                    largestChildValue = childValue;
                    largestChildIndex = childIndex;
                }
            }

            if (largestChildValue.compareTo(current) <= 0) {
                break;
            }

            elements.set(index, largestChildValue);
            elements.set(largestChildIndex, current);
            index = largestChildIndex;
        }
    }

    public static void main(String[] args) {
        System.out.println("Branching factor = 2^1 = 2 children");
        PowerOfTwoMaxHeap<Integer> heap2 = new PowerOfTwoMaxHeap<>(1);
        heap2.insert(5);
        heap2.insert(10);
        heap2.insert(1);
        heap2.insert(7);

        while (!heap2.isEmpty()) {
            System.out.println(heap2.popMax());
        }

        System.out.println("\nBranching factor = 2^8 = 256 children");
        PowerOfTwoMaxHeap<Integer> heap256 = new PowerOfTwoMaxHeap<>(8);
        for (int i = 0; i < 1000; i++) {
            heap256.insert(i);
        }
        System.out.println("Max value (should be 999): " + heap256.popMax());
        System.out.println("Next value (should be 998): " + heap256.popMax());
    }
}