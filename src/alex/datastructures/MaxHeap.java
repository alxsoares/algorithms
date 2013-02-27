package alex.datastructures;

public class MaxHeap {
	private static  int SIZE = 1024;
	int[] heap = new int[SIZE];
	int n = 0;
	public void enqueue(int el)	{
		heap[n] = el;
		upHeap(n);
		n++;
		if(n > SIZE){
			int[] newHeap = new int[2*SIZE];
			System.arraycopy(heap, 0, newHeap, 0, heap.length);
			SIZE = 2*SIZE;
		}
	}

	public void upHeap(int pos){
		int child = pos;
		int parent = (child - 1) / 2;
		while (parent >= 0){
			if (heap[child] > heap[parent]){
				int aux = heap[parent];
				heap[parent] = heap[child];
				heap[child] = aux;
				child = parent;
				parent = (child - 1) / 2;
			}
			else{
				break;
			}
		}
	}

	public int dequeue(){
		if (n != 0){
			n--;
			int aux = heap[0];
			heap[0] = heap[n];
			heap[n] = aux;
			downHeap(0);
			return aux;
		}
		else{
			throw new RuntimeException("Heap vazio");
		}
	}
	public void downHeap(int pos)
	{
		int parent = pos;
		int aux = heap[pos];
		int child = 2 * parent + 1;
		while (child < n){
			if (child + 1 < n && heap[child + 1] > heap[child]){
				child++;
			}
			if (aux < heap[child]){
				heap[parent] = heap[child];
				parent = child;
				child = 2 * parent + 1;
			}
			else{
				break;
			}
		}
		heap[parent] = aux;
	}

	public boolean isEmpty() {
		if (n == 0)
			return true;
		return false;

	}
	public  void heapify(){
		for(int i=0; i <n/2;i++){
			downHeap(i);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap();
		int [] arr = {1,2,3,4,10,7,28}; 
		for (int i = 0; i < arr.length; i++) {
			heap.enqueue(arr[i]);
		}
		heap.heapify();
		String comma ="";
		while(!heap.isEmpty()){
			System.out.print(comma+heap.dequeue());
		}
		System.out.println();
	}

}
