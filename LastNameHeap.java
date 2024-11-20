public class LastNameHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    // Constructor to initialize the heap
    public LastNameHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize];
    }

    // Get the index of the parent node
    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    // Get the index of the left child node
    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    // Get the index of the right child node
    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    // Swap two nodes in the heap
    private void swap(int pos1, int pos2) {
        int temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    // Insert an element into the heap
    public void insert(int element) {
        if (size >= maxSize) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = element;
        int current = size;
        size++;

        // Reorder the heap to maintain max-heap property
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Extract the maximum element (root) from the heap
    public int extractMax() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return Integer.MIN_VALUE;
        }
        int max = heap[0];
        heap[0] = heap[--size];
        maxHeapify(0); // Re-heapify to maintain the heap structure
        return max;
    }

    // Heapify the subtree rooted at a given position
    private void maxHeapify(int pos) {
        int largest = pos;
        int left = leftChild(pos);
        int right = rightChild(pos);

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest != pos) {
            swap(pos, largest);
            maxHeapify(largest);
        }
    }

    // Print the elements of the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Main method to test the heap functionality
    public static void main(String[] args) {
        LastNameHeap heap = new LastNameHeap(10);
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);

        System.out.println("Heap after insertions:");
        heap.printHeap();

        System.out.println("Extracted Max: " + heap.extractMax());
        System.out.println("Heap after extraction:");
        heap.printHeap();
    }
}